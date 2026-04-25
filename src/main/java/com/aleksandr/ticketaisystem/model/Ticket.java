package com.aleksandr.ticketaisystem.model;

import jakarta.persistence.*;

@Entity // entity for ticket
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto id
    private Long id;

    private String title;
    private String category;  // bug,acc,billing,etc
    private String priority;  // low,medium,high
    private String summary;

    private Long commentId;   // link to comment

    public Ticket() {
    }


    public Ticket(Long id, String title, String category, String priority, String summary) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.summary = summary;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public Long getCommentId() {
        return commentId;
    }


    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}