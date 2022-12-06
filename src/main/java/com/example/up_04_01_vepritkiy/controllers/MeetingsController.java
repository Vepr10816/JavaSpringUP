package com.example.up_04_01_vepritkiy.controllers;

import com.example.up_04_01_vepritkiy.models.*;
import com.example.up_04_01_vepritkiy.repo.GroupsRepository;
import com.example.up_04_01_vepritkiy.repo.MeetingsRepository;
import com.example.up_04_01_vepritkiy.repo.TypeMeetingsRepository;
import com.example.up_04_01_vepritkiy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('глОтдел')")
public class MeetingsController {

    @Autowired
    MeetingsRepository meetingsRepository;

    @Autowired
    TypeMeetingsRepository typeMeetingsRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/meetings")
    public String meetingsView(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        if(searchString.equals(""))
            model.addAttribute("meetings_list", meetingsRepository.findAll());
        return "indexMeetings";
    }

    @PostMapping("/meetings/filter")
    public String meetingsResult(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        List<Meetings> result = meetingsRepository.searchByRatingStartsWith(searchString);
        model.addAttribute("meetings_list", result);
        return meetingsView(searchString,model);
    }


    @GetMapping("/meetings/add")
    public String meetingsAdd(@ModelAttribute("meetings") Meetings meetings, Model model)
    {
        model.addAttribute("groups", groupsRepository.findAll());
        model.addAttribute("typemeetings", typeMeetingsRepository.findAll());
        return "meetings-add";
    }

    @PostMapping("/meetings/add")
    public String meetingsPostAdd(@ModelAttribute("meetings") @Valid Meetings meetings,
                                  BindingResult bindingResult,
                                  @RequestParam(defaultValue = " ") String meetingsType,
                                  @RequestParam(defaultValue = " ") String nameGroup)
    {
        if(bindingResult.hasErrors())
            return  "meetings-add";
        meetings.setGroups(groupsRepository.findGroupsByNameGroup(nameGroup));
        meetings.setTypemeetings(typeMeetingsRepository.findTypeMeetingsByMeetingsType(meetingsType));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        meetings.setUser(userRepository.findUserByUsername(currentUserName));
        meetingsRepository.save(meetings);
        return "redirect:/meetings";
    }

    @GetMapping("/meetings/{id}")
    public  String meetingsDelete(@PathVariable(value = "id") long id)
    {
        meetingsRepository.delete(meetingsRepository.findMeetingsById(id));
        return "redirect:/meetings";
    }
}
