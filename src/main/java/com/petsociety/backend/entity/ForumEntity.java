package com.petsociety.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblforum")
public class ForumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int forumID;
    private String post;
    private String reply;
    private LocalDateTime timestamp;
    private boolean isDeleted;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_userID")
    private UserEntity user;

    public ForumEntity() {
        super();
    }

    public ForumEntity(int forumID, String post, String reply, LocalDateTime timestamp, boolean isDeleted) {
        this.forumID = forumID;
        this.post = post;
        this.reply = reply;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
    }

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
