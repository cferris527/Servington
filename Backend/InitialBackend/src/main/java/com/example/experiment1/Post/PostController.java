package com.example.experiment1.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    //Lists all Posts
    @GetMapping(path = "/post")
    List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    //Finds a post by title
    @GetMapping(path = "/post/{title}")
    Post getPostByTitle( @PathVariable String title){
        return postRepository.findByTitle(title);
    }

    //Adds a new post
    @PostMapping(path = "/post")
    String createPost(@RequestBody Post post){
        if (post == null)
            return "failure";
        postRepository.save(post);
        return "success";
    }

    //deletes post with given title
    @DeleteMapping(path = "/post/{title}")
    String deleteUser(@PathVariable String title){
        postRepository.deleteByTitle(title);
        return "success";
    }

    //I spent 3 hours trying to connect this backend to the remote server with no luck


}
