package com.example.up_04_01_vepritkiy.controllers;

import com.example.up_04_01_vepritkiy.models.*;
import com.example.up_04_01_vepritkiy.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('млОТдел')")
public class MyTasksAndMeetingsController {

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

    @GetMapping("/myTasks")
    public String myTasksView(Model model)
    {

        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(currentUserName);
        model.addAttribute("userTaskStatus_list", userTaskStatusRepository.findUserTaskStatusByUser(user));

        return "my-tasks";
    }

    @GetMapping("/myTasks/{id}/canselTask")
    public String cancelMyTask(@PathVariable(value = "id") Long id)
    {
        UserTaskStatus userTaskStatus = userTaskStatusRepository.findUserTaskStatusById(id);
        userTaskStatus.setStatus(statusRepository.findStatusById(Long.parseLong("5")));
        userTaskStatusRepository.save(userTaskStatus);
        return "redirect:/myTasks";
    }

    @GetMapping("/myTasks/{id}/doneTask")
    public String doneMyTask(@PathVariable(value = "id") Long id)
    {
        UserTaskStatus userTaskStatus = userTaskStatusRepository.findUserTaskStatusById(id);
        userTaskStatus.setStatus(statusRepository.findStatusById(Long.parseLong("6")));
        userTaskStatusRepository.save(userTaskStatus);
        return "redirect:/myTasks";
    }

    @GetMapping("/myTasks/{id}/messanger")
    public String taskMessanger(@PathVariable(value = "id") Long id, Model model)
    {
        UserTaskStatus userTaskStatus = userTaskStatusRepository.findUserTaskStatusById(id);
        //Tasks tasks = userTaskStatus.getTasks();
        Collection<Messanger> messanger = userTaskStatus.getTasks().getMessangerCollection();
        model.addAttribute("userTaskStatus_list",userTaskStatus);
        model.addAttribute("messanger",messanger);
        model.addAttribute("currentUser",
                userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        return "myChat";
    }

    @GetMapping("/myTasks/{id}/send")
    public String myTasksSendMessage(@PathVariable(value = "id") long id,
                                   @ModelAttribute("messanger") @Valid Messanger messanger, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return "myChat";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        messanger.setUser(userRepository.findUserByUsername(currentUserName));
        UserTaskStatus userTaskStatus = userTaskStatusRepository.findUserTaskStatusById(id);
        messanger.setTasks(userTaskStatus.getTasks());
        messangerRepository.save(messanger);
        String url = "redirect:/myTasks/"+id+"/messanger";
        return url;
    }

    @GetMapping("/myMeetings")
    public String myMeetings(Model model)
    {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(currentUserName);
        model.addAttribute("groupUser_list",groupUserRepository.findGroupUserByUser(user));
        return "my-meetings";
    }

}
