package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Meetings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле название встречи не заполнено")
    private String meetingsName;

    private Date meetingsDate;

    private Time meetingsTime;

    @NotBlank(message = "Поле оптсание встречи не заполнено")
    private String meetingsDescription;

    private String meetingsUrl;

    @ManyToOne(optional = true)
    private TypeMeetings typemeetings;

    @ManyToOne(optional = true)
    private User user;

    @ManyToOne(optional = true)
    private Groups groups;

    public Meetings() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingsName() {
        return meetingsName;
    }

    public void setMeetingsName(String meetingsName) {
        this.meetingsName = meetingsName;
    }

    public Date getMeetingsDate() {
        return meetingsDate;
    }

    public void setMeetingsDate(Date meetingsDate) {
        this.meetingsDate = meetingsDate;
    }

    public Time getMeetingsTime() {
        return meetingsTime;
    }

    public void setMeetingsTime(Time meetingsTime) {
        this.meetingsTime = meetingsTime;
    }

    public String getMeetingsDescription() {
        return meetingsDescription;
    }

    public void setMeetingsDescription(String meetingsDescription) {
        this.meetingsDescription = meetingsDescription;
    }

    public String getMeetingsUrl() {
        return meetingsUrl;
    }

    public void setMeetingsUrl(String meetingsUrl) {
        this.meetingsUrl = meetingsUrl;
    }

    public TypeMeetings getTypemeetings() {
        return typemeetings;
    }

    public void setTypemeetings(TypeMeetings typemeetings) {
        this.typemeetings = typemeetings;
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
}
