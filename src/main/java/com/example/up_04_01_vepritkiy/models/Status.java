package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не заполнено")
    private String nameStatus;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private Collection<Tasks> tasksCollection;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Collection<UserTaskStatus> userTaskStatusCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Collection<UserTaskStatus> getUserTaskStatusCollection() {
        return userTaskStatusCollection;
    }

    public void setUserTaskStatusCollection(Collection<UserTaskStatus> userTaskStatusCollection) {
        this.userTaskStatusCollection = userTaskStatusCollection;
    }
}
