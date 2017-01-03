package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by scofieldservices on 1/2/17.
 */

@Entity
@Table(name = "thoughts")
public class Thought {
    @Id
    @GeneratedValue
    int id;

    @Column
    String description;

    @Column(nullable = false)
    LocalDateTime dateTime;

    @Column(nullable = false)
    String subject;

    @ManyToOne
    User user;

    public Thought() {
    }

    public Thought(String description, LocalDateTime dateTime, String subject, User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.subject = subject;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
