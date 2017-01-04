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
    int thoughtId;

    @Column
    public String description;

    @Column(nullable = false)
    LocalDateTime dateTime;

    @Column(nullable = false)
    String category;

    @ManyToOne
    User user;

    public Thought() {
    }

    public Thought(String description, LocalDateTime dateTime, String category, User user) {
        this.description = description;
        this.dateTime = dateTime;
        this.category = category;
        this.user = user;
    }

    public int getThoughtId() {
        return thoughtId;
    }

    public void setThoughtId(int thoughtId) {
        this.thoughtId = thoughtId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
