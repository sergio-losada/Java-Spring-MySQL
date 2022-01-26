package com.example.javaspringmysql.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.javaspringmysql.util.Status;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Id;

@Entity
@Table(name = "profile")
public class Profile {

    // Id of the user (PK)
    @JsonView(Views.ParsedProfile.class)
    @Id
    private String userId;

    // Name of the user
    @JsonView(Views.ParsedProfile.class)
    private String name;
    
    // Status of the user
    @JsonView(Views.ParsedProfile.class)
    private Status status;

    // Description of the user
    @Column(length = 1024)
    private String description;

    // Profile's creation date
    private Date initDate;

    // Profile's last modification date 
    private Date lastModDate;

    // Default constructor
    public Profile(){

    }

    // Parameterized constructors
    public Profile(String userId, String name, Status status) {
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public Profile(String userId, String name, Status status, String description, Date initDate, Date lastModDate) {
        this.userId = userId;
        this.name = name;
        this.status = status;
        this.description = description;
        this.initDate = initDate;
        this.lastModDate = lastModDate;
    }
    
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getInitDate() {
        return this.initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getLastModDate() {
        return this.lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(userId, profile.userId) && Objects.equals(name, profile.name) && Objects.equals(description, profile.description) && Objects.equals(status, profile.status) && Objects.equals(initDate, profile.initDate) && Objects.equals(lastModDate, profile.lastModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, description, status, initDate, lastModDate);
    }


    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", initDate='" + getInitDate() + "'" +
            ", lastModDate='" + getLastModDate() + "'" +
            "}";
    }
        
}
