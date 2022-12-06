package com.example.up_04_01_vepritkiy.controllers;

import com.example.up_04_01_vepritkiy.models.Notes;
import com.example.up_04_01_vepritkiy.models.Tasks;
import com.example.up_04_01_vepritkiy.models.User;
import com.example.up_04_01_vepritkiy.repo.NotesRepository;
import com.example.up_04_01_vepritkiy.repo.UserRepository;
import com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('млОТдел')")
public class NotesController {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/myNotes")
    public String myNotesView(@RequestParam(defaultValue = "") String searchString,Model model)
    {
        if(searchString.equals("")) {
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findUserByUsername(currentUserName);
            List<Notes> notes = notesRepository.findNotesByUser(user);
            model.addAttribute("notes_list", notes);
        }

        return "my-notes";
    }

    @PostMapping("/myNotes/filter")
    public String tasksResult(@RequestParam(defaultValue = "") String searchString, Model model)
    {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(currentUserName);
        List<Notes> result = notesRepository.searchByRatingStartsWith(searchString,user);
        model.addAttribute("notes_list", result);
        return "my-notes";
    }

    @GetMapping("/myNotes/add")
    public String myNotesAdd(@ModelAttribute("notes") Notes notes)
    {
        return "my-notes-add";
    }

    @PostMapping("/myNotes/add")
    public String myNotesAddPost(@ModelAttribute("notes") @Valid Notes notes, BindingResult bindingResult)
    {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(currentUserName);
        notes.setUser(user);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        notes.setDateNotes(java.sql.Date.valueOf(date));
        notes.setTimeNotes(Time.valueOf(time));
        if(bindingResult.hasErrors())
            return "my-notes-add";
        notesRepository.save(notes);
        return "redirect:/myNotes";
    }

    @GetMapping("/myNotes/{id}/detail")
    public String myNotesDetail(@PathVariable(value = "id") long id,Model model)
    {
        model.addAttribute("notes", notesRepository.findNotesById(id));
        return "my-notes-detail";
    }

    @PostMapping("/myNotes/{id}/detail")
    public String myNotesDetailPost(@ModelAttribute("notes") @Valid Notes notes, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "my-notes-detail";
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(currentUserName);
        notes.setUser(user);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        notes.setDateNotes(java.sql.Date.valueOf(date));
        notes.setTimeNotes(Time.valueOf(time));
        notesRepository.save(notes);
        return "redirect:/myNotes";
    }

    @GetMapping("/myNotes/{id}/delete")
    public String myNotesDelete(@PathVariable(value = "id") long id)
    {
        notesRepository.delete(notesRepository.findNotesById(id));
        return "redirect:/myNotes";
    }
}
