package com.back.hello_ada_back.Models;

import java.math.BigInteger;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "likes")
    private int likes;

    @Column(nullable = false, name = "post_title", length = 255)
    private String postTitle;

    @Column(nullable = true, name = "post_picture", length = 255)
    private String postPicture;

    @Column(nullable = false, name = "content", length = 1000)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"posts"})
    private Users user;

    public Posts() {}

    public Posts(Users user, BigInteger likes, String postTitle, String postPicture, String content, LocalDateTime createdAt) {
        this.user = user;
        this.likes = likes;
        this.postTitle = postTitle;
        this.postPicture = postPicture;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getLikes() {

    }
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostPicture() {
        return this.postPicture;
    }

    public void setPostPicture(String postPicture) {
        this.postPicture = postPicture;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
