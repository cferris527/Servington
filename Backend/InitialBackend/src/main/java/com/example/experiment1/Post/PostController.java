package com.example.experiment1.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.experiment1.User.User;
import com.example.experiment1.User.UserRepository;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;



    //This function returns a list of all posts in the database
    @GetMapping(path = "/post")
    List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    //Returns the post with the given title
    @GetMapping(path = "/post/{title}")
    Post getPostByTitle( @PathVariable String title){
        return postRepository.findByTitle(title);
    }

    //Returns a list of posts containing the given keyword in the description
    @GetMapping(path = "/postDescriptionKeyword/{keyword}")
    public List<Post> getPostsByKeywordInDescription(@PathVariable String keyword){
        return postRepository.findByDescriptionContaining(keyword);
    }

    //Returns a list of posts containing the given keyword in the Title
    @GetMapping(path = "/postTitleKeyword/{keyword}")
    public List<Post> getPostsByKeywordinTitle(@PathVariable String keyword){
        return postRepository.findByTitleContaining(keyword);
    }








    //Creates a new post and adds it to the database
    @PostMapping(path = "/post")
    String createPost(@RequestBody Post post){
        if (post == null)
            return "failure";
        postRepository.save(post);
        return "success";
    }





    /*@PutMapping(path = "/post/{id}/{title}")
    String addUserToPost(@RequestBody int id, @RequestBody String title){
        Post p = postRepository.findByTitle(title);
        User u = userRepository.findById(id);
        if(u == null || p == null) {
            return "failure";
        }
        p.setUser(u);
        return "success";
    }*/












    //Deletes a post with the given title
    @DeleteMapping(path = "/post")
    String deleteUser(@RequestBody Post post){
        postRepository.deleteByTitle(post.getTitle());
        return "success";
    }


}
