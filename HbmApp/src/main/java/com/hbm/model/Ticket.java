package com.hbm.model;

import com.hbm.enums.Priority;
import com.hbm.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity //<-- tells DB to create a table with default name as 'ticket'
@Table(name = "tickets") //<-- Now the table name would be 'tickets'
public class Ticket {
    @Id //<-- this makes id a Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //<-- this auto generates the id
    private int id;

    @Column(nullable = false) //<-- this adds a NOT NULL constraint to the field
    private String subject;

    @Column(length = 1000)  //<-- this creates the field of length 1000 as max chars
    private String description;

    @Enumerated
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
/*
Since we have Hibernate & JPA (Jakarta Persistence API)

JPA has annotations that help create tables in the DB
* */