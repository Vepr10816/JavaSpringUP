package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не заполнено")
    private String descriptionTask;

    private Date deadLineDate;

    private Time deadLineTime;

    @ManyToOne(optional = true)
    private User user;

    @ManyToOne(optional = true)
    private Groups groups;

    @ManyToOne(optional = true)
    private Status status;

    @OneToMany(mappedBy = "tasks", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<UserTaskStatus> userTaskStatusCollection;

    @OneToMany(mappedBy = "tasks", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Messanger> messangerCollection;

    public Tasks() {
    }

    public Tasks(String descriptionTask, Date deadLineDate, Time deadLineTime, User user, Groups groups, Status status) {
        this.descriptionTask = descriptionTask;
        this.deadLineDate = deadLineDate;
        this.deadLineTime = deadLineTime;
        this.user = user;
        this.groups = groups;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public Date getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public Time getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(Time deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Collection<UserTaskStatus> getUserTaskStatusCollection() {
        return userTaskStatusCollection;
    }

    public void setUserTaskStatusCollection(Collection<UserTaskStatus> userTaskStatusCollection) {
        this.userTaskStatusCollection = userTaskStatusCollection;
    }

    public Collection<Messanger> getMessangerCollection() {
        return messangerCollection;
    }

    public void setMessangerCollection(Collection<Messanger> messangerCollection) {
        this.messangerCollection = messangerCollection;
    }
}
