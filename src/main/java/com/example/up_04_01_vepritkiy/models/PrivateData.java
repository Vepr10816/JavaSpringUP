package com.example.up_04_01_vepritkiy.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "privatedata")
public class PrivateData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не заполнено")
    @Pattern(regexp = "^(?=.*[0-9]).{4,}$", message = "Серия паспорта имеет длину 4 символа")
    private String series;
    @NotBlank(message = "Полене не заполнено")
    @Pattern(regexp = "^(?=.*[0-9]).{6,}$", message = "Номер паспорта имеет длину 6 символов")
    private String number;
    @NotBlank(message = "Поле не заполнено")
    @Pattern(regexp = "^(?=.*[0-9]).{11,}$", message = "Номер телефона имеет длину 11 символов")
    private String telephoneNumber;
    @NotBlank(message = "Поле адреса не заполнено")
    private String employeeAddress;

    @OneToOne(optional = true, mappedBy = "privateData")
    private User owner;

    public PrivateData(String series, String number, String telephoneNumber, String employeeAddress, User owner) {
        this.series = series;
        this.number = number;
        this.telephoneNumber = telephoneNumber;
        this.employeeAddress = employeeAddress;
        this.owner = owner;
    }

    public PrivateData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
