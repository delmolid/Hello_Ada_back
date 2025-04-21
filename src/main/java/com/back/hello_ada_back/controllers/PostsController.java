package com.back.hello_ada_back.controllers;

import com.back.hello_ada_back.Models.Posts;
import com.back.hello_ada_back.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}/updatePost")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Posts updatePost) {
        try {
            Posts post = postsService.findById(id);

            Posts updatedPost = postsService.updatePost(id, 
            updatePost.getPostTitle(),
            updatePost.getPostPicture(),
            updatePost.getContent());
            return ResponseEntity.ok().body(updatedPost);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur de la mise à jour du post : " + e.getMessage());
        }
    }
} 