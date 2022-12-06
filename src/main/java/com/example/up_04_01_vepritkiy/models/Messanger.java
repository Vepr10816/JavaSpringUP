package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Messanger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String messageContent;

    @ManyToOne(optional = true)
    private User user;

    @ManyToOne(optional = true)
    private Tasks tasks;

    public Messanger(String messageContent, User user, Tasks tasks) {
        this.messageContent = messageContent;
        this.user = user;
        this.tasks = tasks;
    }

    public Messanger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }
}
