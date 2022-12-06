package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;

@Entity
public class UserTaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private User user;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Tasks tasks;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Status status;

    public UserTaskStatus(User user, Tasks tasks, Status status) {
        this.user = user;
        this.tasks = tasks;
        this.status = status;
    }

    public UserTaskStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
