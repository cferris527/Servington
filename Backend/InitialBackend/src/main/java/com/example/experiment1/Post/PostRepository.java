package com.example.experiment1.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    Post findByTitle(String title);

    @Transactional
    void deleteByTitle(String title);


    List<Post> findByDescriptionContaining(String keyword);

    List<Post> findByTitleContaining(String keyword);

    List<Post> findByUserContaining(int id);

}
