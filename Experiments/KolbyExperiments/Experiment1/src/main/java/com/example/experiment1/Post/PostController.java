package com.example.experiment1.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping(path = "/posts")
    List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping(path = "/posts/{id}")
    Post getPostById( @PathVariable int id){
        return postRepository.findById(id);
    }

    @PostMapping(path = "/post")
    String createPost(Post post){
        if (post == null)
            return "failure";
        postRepository.save(post);
        return "success";
    }

    @PutMapping("/post/{id}")
    Post updatePost(@PathVariable int id, @RequestBody Post request){
        Post phone = postRepository.findById(id);
        if(phone == null)
            return null;
        postRepository.save(request);
        return postRepository.findById(id);
    }


}
