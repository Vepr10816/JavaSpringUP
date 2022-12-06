package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не заполнено")
    private String nameGroup;

    @OneToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<GroupUser> groupUserCollection;

    @OneToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Tasks> tasksCollection;

    @OneToMany(mappedBy = "groups", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Meetings> meetingsCollection;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Collection<GroupUser> getGroupUserCollection() {
        return groupUserCollection;
    }

    public void setGroupUserCollection(Collection<GroupUser> groupUserCollection) {
        this.groupUserCollection = groupUserCollection;
    }

    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Collection<Meetings> getMeetingsCollection() {
        return meetingsCollection;
    }

    public void setMeetingsCollection(Collection<Meetings> meetingsCollection) {
        this.meetingsCollection = meetingsCollection;
    }
}
