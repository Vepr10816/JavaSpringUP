package com.example.up_04_01_vepritkiy.controllers;

import com.example.up_04_01_vepritkiy.models.Groups;
import com.example.up_04_01_vepritkiy.models.PrivateData;
import com.example.up_04_01_vepritkiy.models.Role;
import com.example.up_04_01_vepritkiy.models.User;
import com.example.up_04_01_vepritkiy.repo.GroupUserRepository;
import com.example.up_04_01_vepritkiy.repo.GroupsRepository;
import com.example.up_04_01_vepritkiy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupsController {

//    @Autowired
//    GroupsRepository groupsRepository;
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    GroupUserRepository groupUserRepository;
//
//    @GetMapping("/groups")
//    public String groupsView(@RequestParam(defaultValue = "") String searchString, Model model)
//    {
//        if(searchString.equals(""))
//            model.addAttribute("groups_list", groupsRepository.findAll());
//        model.addAttribute("groupsUser_list", groupUserRepository.findAll());
//        return "indexGroups";
//    }
//
//    @PostMapping("/groups/filter")
//    public String usersResult(@RequestParam(defaultValue = "") String searchString, Model model)
//    {
//        List<Groups> result = groupsRepository.searchByRatingStartsWith(searchString);
//        model.addAttribute("groups_list", result);
//        return groupsView(searchString,model);
//    }
//
//    @GetMapping("/groups/add")
//    public String userAdd(@ModelAttribute("groups") Groups groups)
//    {
//        return "groups-add";
//    }
//
//    @PostMapping("/admin/add")
//    public String userPostAdd(@ModelAttribute("groups") @Valid Groups groups, BindingResult bindingResult)
//    {
////        if(bindingResult.hasErrors()) {
////            return "groups-add";
////        }
////        userRepository.save(user);
//    return "redirect:/admin";
//    }

}
