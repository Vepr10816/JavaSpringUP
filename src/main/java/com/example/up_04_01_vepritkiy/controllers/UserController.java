package com.example.up_04_01_vepritkiy.controllers;

import com.example.up_04_01_vepritkiy.models.PrivateData;
import com.example.up_04_01_vepritkiy.models.Role;
import com.example.up_04_01_vepritkiy.models.User;
import com.example.up_04_01_vepritkiy.repo.PrivateDataRepository;
import com.example.up_04_01_vepritkiy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('Администратор')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivateDataRepository privateDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/dashboardUser")
    public String dashboardPageList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println(currentUserName);
        User user = userRepository.findUserByUsername(currentUserName);
        model.addAttribute("currentStudent", user);

        return "dashboardUser";
    }

    @GetMapping("/admin")
    public String userView(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        if(searchString.equals(""))
            model.addAttribute("user_list", userRepository.findAll());
        return "index";
    }

    @GetMapping("/admin/add")
    public String userAdd(@ModelAttribute("users") User user, Model modelRoles, @ModelAttribute("privatedats") PrivateData privateData)
    {
        modelRoles.addAttribute("modelRoles", Role.values());
        return "user-add";
    }


    @PostMapping("/admin/add")
    public String userPostAdd(@ModelAttribute("users") @Valid User user, BindingResult bindingResult, Model modelRoles,
                               @RequestParam(name="roles[]", required = false) String[] roles,
                               @ModelAttribute("privatedats") @Valid PrivateData privateData, BindingResult bindingResult2)
    {
        if(bindingResult.hasErrors() || bindingResult2.hasErrors()) {
            modelRoles.addAttribute("modelRoles", Role.values());
            return "user-add";
        }
        user.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        privateDataRepository.save(privateData);
        user.setPrivateData(privateData);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/privateData")
    public  String userPrivateData(@PathVariable(value = "id") long id, Model modelPrivateData)
    {

        modelPrivateData.addAttribute("modelPrivateData",privateDataRepository.findById(id).orElseThrow());
        return "user-privatedata";
    }


    @PostMapping ("/admin/{id}/privateData")
    public  String privateDataUpdate(@ModelAttribute("modelPrivateData") @Valid PrivateData privateData,
                               BindingResult bindingResult,
                               @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "user-privatedata";
        privateDataRepository.save(privateData);
        return "redirect:/admin";
    }


    @GetMapping("/admin/{id}/userUpdate")
    public  String userUpdate(@PathVariable(value = "id") long id, Model modelUser, Model modelRoles)
    {

        modelUser.addAttribute("modelUser",userRepository.findById(id).orElseThrow());
        modelRoles.addAttribute("modelRoles", Role.values());
        return "user-update";
    }


    @PostMapping ("/admin/{id}/userUpdate")
    public  String userPostUpdate(@ModelAttribute("modelUser") @Valid User user,
                                     BindingResult bindingResult,
                                    Model modelRoles,
                                    @RequestParam(name="roles[]", required = false) String[] roles,
                                     @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors()) {
            modelRoles.addAttribute("modelRoles", Role.values());
            return "user-update";
        }
        user.getRoles().clear();
        if(roles == null)
            return "user-update";
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        user.setActive(true);
        User user1 = userRepository.findById(id).orElseThrow();
        if(!user1.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setPrivateData(user1.getPrivateData());
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/userRemove")
    public  String userDelete(@PathVariable(value = "id") long id)
    {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/filter")
    public String usersResult(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        List<User> result = userRepository.searchByRatingStartsWith(searchString);
        model.addAttribute("user_list", result);
        return userView(searchString,model);
    }

}
