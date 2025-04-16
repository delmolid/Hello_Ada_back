package com.back.hello_ada_back.services;

import com.back.hello_ada_back.Models.Posts;
import com.back.hello_ada_back.Models.Users;
import com.back.hello_ada_back.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    

    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    public Posts findById(Long id) {
        return postsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Posts> findByUserId(Long userId) {
        return postsRepository.findByUser_Id(userId);
    }

    public void deletePost(Long id) {
        postsRepository.deleteById(id);
    }

    public void deleteAllPostsOfUser(Long userId) {
        List<Posts> userPosts = findByUserId(userId);
        postsRepository.deleteAll(userPosts);

    }

    public Posts createPost(Posts post) {
        return postsRepository.save(post);
    }

//     @Bean
//     CommandLineRunner initDatabase(UsersService usersService, PostsService postsService) {
//         return args -> {
//             // Créer un utilisateur de test
//             Users user = new Users();
//             user.setUsername("Ada");
//             user.setEmail("ada@example.com");
//             user.setPassword("password10");
//             usersService.createUser(user);

//             // Créer un post de test
//             Posts post = new Posts();
//             post.setPostTitle("Journée raclette avec la promo fatoumata ");
//             post.setContent("La journée raclette avec la promo fatoumata a été un succès !");
//             post.setLikes(BigInteger.ZERO);
//             post.setUser(user);
//             postsService.createPost(post);

            
//         };
//     }
}