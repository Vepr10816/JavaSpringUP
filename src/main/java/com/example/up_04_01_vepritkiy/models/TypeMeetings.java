package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class TypeMeetings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meetingsType;

    @OneToMany(mappedBy = "typemeetings", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Meetings> meetingsCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingsType() {
        return meetingsType;
    }

    public void setMeetingsType(String meetingsType) {
        this.meetingsType = meetingsType;
    }

    public Collection<Meetings> getMeetingsCollection() {
        return meetingsCollection;
    }

    public void setMeetingsCollection(Collection<Meetings> meetingsCollection) {
        this.meetingsCollection = meetingsCollection;
    }
}
