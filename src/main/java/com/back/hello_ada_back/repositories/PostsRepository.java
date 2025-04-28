package com.back.hello_ada_back.repositories;

import com.back.hello_ada_back.Models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByUser_Id(Long userId);
}
