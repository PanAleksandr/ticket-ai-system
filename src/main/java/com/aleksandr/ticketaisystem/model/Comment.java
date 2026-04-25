package com.aleksandr.ticketaisystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // entity for database
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto id
    private Long id;

    private String text;

    public Comment() {
    }

    // constructor
    public Comment(Long id, String text) {
        this.id = id;
        this.text = text;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }
}