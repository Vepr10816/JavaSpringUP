package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле электронной почты не заполнено")
    @Email(message = "Некорректный ввод электронной почты")
    private String username;
    @NotBlank(message = "Поле пароля не зполнено")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "Пароль должен соответствовать следующим требованиям: Не менее 8 символов\n" +
            "Содержит хотя бы одну цифру\n" +
            "Содержит по крайней мере один нижний альфа-символ и один верхний альфа-символ\n" +
            "Содержит хотя бы один символ в наборе специальных символов (и т.д.)@#%$^\n" +
            "Не содержит пробела, табуляции и т.д.")
    private String password;
    private boolean active;
    @NotBlank(message = "Поле фамилии не зполнено")
    private String lastName;
    @NotBlank(message = "Поле имени не зполнено")
    private String firstName;
    @NotBlank(message = "Поле отчества не зполнено")
    private String middleName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="privatedata_id")
    private PrivateData privateData;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Tasks> tasksCollection;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<GroupUser> groupUserCollection;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<UserTaskStatus> userTaskStatusCollection;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Messanger> messangerCollection;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Meetings> meetingsCollection;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Collection<Notes> notesCollection;

    public User(String username, String password, boolean active, String lastName, String firstName, String middleName, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.roles = roles;
    }

    public User(String username, String password, boolean active, String lastName, String firstName, String middleName, Set<Role> roles, PrivateData privateData) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.roles = roles;
        this.privateData = privateData;
    }

    public User(String username, String password, boolean active, String lastName, String firstName, String middleName, Set<Role> roles, PrivateData privateData, Collection<Tasks> tasksCollection) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.roles = roles;
        this.privateData = privateData;
        this.tasksCollection = tasksCollection;
    }



    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public PrivateData getPrivateData() {
        return privateData;
    }

    public void setPrivateData(PrivateData privateData) {
        this.privateData = privateData;
    }

    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Collection<GroupUser> getGroupUserCollection() {
        return groupUserCollection;
    }

    public void setGroupUserCollection(Collection<GroupUser> groupUserCollection) {
        this.groupUserCollection = groupUserCollection;
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

    public Collection<Meetings> getMeetingsCollection() {
        return meetingsCollection;
    }

    public void setMeetingsCollection(Collection<Meetings> meetingsCollection) {
        this.meetingsCollection = meetingsCollection;
    }

    public Collection<Notes> getNotesCollection() {
        return notesCollection;
    }

    public void setNotesCollection(Collection<Notes> notesCollection) {
        this.notesCollection = notesCollection;
    }
}
