package com.example.experiment1.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(int id);

    @Transactional
    void deleteById(int id);

}
