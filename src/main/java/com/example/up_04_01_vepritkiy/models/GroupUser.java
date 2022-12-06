package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = true)
    private Groups groups;

    @ManyToOne(optional = true)
    private User user;



    public GroupUser(Groups groups, User user) {
        this.groups = groups;
        this.user = user;
    }

    public GroupUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
