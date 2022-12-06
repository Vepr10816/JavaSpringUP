package com.example.up_04_01_vepritkiy.controllers;

import com.example.up_04_01_vepritkiy.models.*;
import com.example.up_04_01_vepritkiy.repo.*;
import com.sun.jdi.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('глОтдел')")
public class TasksController {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    GroupUserRepository groupUserRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserTaskStatusRepository userTaskStatusRepository;

    @Autowired
    MessangerRepository messangerRepository;

    @GetMapping("/tasks")
    public String tasksView(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        if(searchString.equals(""))
            model.addAttribute("tasks_list", tasksRepository.findAll());
        model.addAttribute("users", userRepository.findAll()) ;
        return "indexTasks";
    }

    @PostMapping("/tasks/filter")
    public String tasksResult(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        List<Tasks> result = tasksRepository.searchByRatingStartsWith(searchString);
        model.addAttribute("tasks_list", result);
        return tasksView(searchString,model);
    }

    @GetMapping("/tasks/add")
    public String tasksAdd(@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("groups") Groups groups, /*@ModelAttribute("users")*/ Model modelUser)
    {
        modelUser.addAttribute("users", userRepository.findUsersByRole("млОТдел"));
        return "tasks-add";
    }

    @PostMapping("/tasks/add")
    public String tasksPostAdd(@ModelAttribute("tasks") @Valid Tasks tasks, BindingResult bindingResult,
                               @ModelAttribute("groups") @Valid Groups groups, BindingResult bindingResult2,
                              Model modelUser,
                              @RequestParam(name="users[]", required = false) String[] users)
    {
        if(bindingResult.hasErrors() || bindingResult2.hasErrors()) {
            modelUser.addAttribute("users", userRepository.findUsersByRole("млОТдел"));
            return "tasks-add";
        }
        groupsRepository.save(groups);
        if(users == null)
            return "tasks-add";
        if(users != null)
        {
            for(String user: users)
            {
                GroupUser groupUser = new GroupUser();
                groupUser.setGroups(groups);
                groupUser.setUser(userRepository.findUsersById(Long.parseLong(user)));
                groupUserRepository.save(groupUser);
            }
        }
        tasks.setStatus(statusRepository.findStatusById(Long.parseLong("1")));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        tasks.setUser(userRepository.findUserByUsername(currentUserName));
        tasks.setGroups(groups);
        tasksRepository.save(tasks);
        if(users != null)
        {
            for(String user: users)
            {
                UserTaskStatus userTaskStatus = new UserTaskStatus();
                userTaskStatus.setTasks(tasks);
                userTaskStatus.setStatus(statusRepository.findStatusById(Long.parseLong("1")));
                userTaskStatus.setUser(userRepository.findUsersById(Long.parseLong(user)));
                userTaskStatusRepository.save(userTaskStatus);
            }
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/Remove")
    public  String tasksDelete(@PathVariable(value = "id") long id)
    {
        Tasks tasks = tasksRepository.findById(id).orElseThrow();
        for(GroupUser groupUser: tasks.getGroups().getGroupUserCollection())
        {
            groupUserRepository.delete(groupUserRepository.findGroupUserById(groupUser.getId()));
        }
        for (UserTaskStatus userTaskStatus:
                tasks.getUserTaskStatusCollection()) {
            userTaskStatusRepository.delete(userTaskStatusRepository.findUserTaskStatusById(userTaskStatus.getId()));
        }
        tasksRepository.delete(tasks);
        groupsRepository.delete(tasks.getGroups());
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String tasksDetails(@PathVariable(value = "id") long id,Model model)
    {
        Tasks tasks = tasksRepository.findById(id).orElseThrow();
        model.addAttribute("tasks",tasks);
        model.addAttribute("userTaskStatus", tasks.getUserTaskStatusCollection());
        model.addAttribute("messangers",tasks.getMessangerCollection());
        model.addAttribute("currentUser",
                userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "tasks-detail";
    }

    @GetMapping("/tasks/{id}/send")
    public String tasksSendMessage(@PathVariable(value = "id") long id,
                               @ModelAttribute("messangers") @Valid Messanger messanger, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return "tasks-detail";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        messanger.setUser(userRepository.findUserByUsername(currentUserName));
        messanger.setTasks(tasksRepository.findTasksById(id));
        messangerRepository.save(messanger);
        return "redirect:/tasks/"+id;
    }

    @GetMapping("/tasks/{id_tasks}/returnTask/{id_usertaskstatus}")
    public String returnTaskForUser(@PathVariable(value = "id_tasks") long id_tasks, @PathVariable(value = "id_usertaskstatus") long id_usertaskstatus)
    {
        UserTaskStatus userTaskStatus = userTaskStatusRepository.findUserTaskStatusById(id_usertaskstatus);
        userTaskStatus.setStatus(statusRepository.findStatusById(Long.parseLong("2")));
        userTaskStatusRepository.save(userTaskStatus);
        return "redirect:/tasks/"+id_tasks;
    }

    @GetMapping("/tasks/{id_tasks}/doneTask/{id_usertaskstatus}")
    public String doneTaskForUser(@PathVariable(value = "id_tasks") long id_tasks, @PathVariable(value = "id_usertaskstatus") long id_usertaskstatus)
    {
        UserTaskStatus userTaskStatus = userTaskStatusRepository.findUserTaskStatusById(id_usertaskstatus);
        userTaskStatus.setStatus(statusRepository.findStatusById(Long.parseLong("3")));
        userTaskStatusRepository.save(userTaskStatus);
        return "redirect:/tasks/"+id_tasks;
    }

    @GetMapping("/tasks/{id}/doneTask")
    public String doneTask(@PathVariable(value = "id") long id)
    {
        Tasks tasks = tasksRepository.findTasksById(id);
        tasks.setStatus(statusRepository.findStatusById(Long.parseLong("4")));
        tasksRepository.save(tasks);
        return "redirect:/tasks/";
    }

}
