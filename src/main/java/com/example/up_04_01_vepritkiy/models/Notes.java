package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название не заполенено")
    private String nameNotes;

    private Date dateNotes;

    private Time timeNotes;

    private String descriptionNotes;

    private Date deadLineDate;

    private Time deadLineTime;

    @ManyToOne(optional = true)
    private User user;

    public Notes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameNotes() {
        return nameNotes;
    }

    public void setNameNotes(String nameNotes) {
        this.nameNotes = nameNotes;
    }

    public Date getDateNotes() {
        return dateNotes;
    }

    public void setDateNotes(Date dateNotes) {
        this.dateNotes = dateNotes;
    }

    public String getDescriptionNotes() {
        return descriptionNotes;
    }

    public void setDescriptionNotes(String descriptionNotes) {
        this.descriptionNotes = descriptionNotes;
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

    public Time getTimeNotes() {
        return timeNotes;
    }

    public void setTimeNotes(Time timeNotes) {
        this.timeNotes = timeNotes;
    }
}
