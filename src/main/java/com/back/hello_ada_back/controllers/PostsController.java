package com.back.hello_ada_back.controllers;

import com.back.hello_ada_back.Models.Posts;
import com.back.hello_ada_back.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
    @Autowired
    private PostsService postsService;

    @GetMapping
    public List<Posts> getAllPosts() {
        return postsService.findAll();
    }

    @GetMapping("/{id}")
    public Posts getPostById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @PostMapping("/createPost")
    public Posts createPost(@RequestBody Posts post) {
        return postsService.createPost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postsService.deletePost(id);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteAllPostsOfUser(@PathVariable Long userId) {
        postsService.deleteAllPostsOfUser(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Posts> getPostsByUserId(@PathVariable Long userId) {
        return postsService.findByUserId(userId);
    }
} 