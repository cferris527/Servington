package com.example.experiment1.Post;

import com.example.experiment1.Message;
import com.example.experiment1.Organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Organization.OrganizationRepository;


import java.util.List;
import java.util.ArrayList;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    OrganizationRepository organizationRepository;



    //This function returns a list of all posts in the database. This works on frontend.
    @GetMapping(path = "/post")
    List<Post> getAllPosts(){
        return postRepository.findAll();
    }


    //Returns the post with the given title. Has not been implemented on frontend.
    @GetMapping(path = "/post/{title}")
    Post getPostByTitle( @PathVariable String title){
        return postRepository.findByTitle(title);
    }


    //Returns a list of posts containing the given keyword in the description. Has not been implemented on frontend.
    @GetMapping(path = "/postDescriptionKeyword/{keyword}")
    public List<Post> getPostsByKeywordInDescription(@PathVariable String keyword){
        return postRepository.findByDescriptionContaining(keyword);
    }


    //Returns a list of posts containing the given keyword in the Title. Has not been implemented on frontend.
    @GetMapping(path = "/postTitleKeyword/{keyword}")
    public List<Post> getPostsByKeywordinTitle(@PathVariable String keyword){
        return postRepository.findByTitleContaining(keyword);
    }








    //Creates a new post and adds it to the database. Not used on frontend yet.
    @PostMapping(path = "/post/{id}")
    String createPost(@RequestBody Post post, @PathVariable int id){
        if (post == null)
            return "failure";
        Organization o = organizationRepository.findById(id);
        post.setOrg(o);
        //u.setPost(post);
        postRepository.save(post);
        return "success";
    }



    //Deletes a post with the given title. Is not used right now with frontend.
    @DeleteMapping(path = "/post")
    String delete(@RequestBody Post post){
        postRepository.deleteByTitle(post.getTitle());
        return "success";
    }



    //This function uses the post mapping method to delete a post. Works with front end.
    @PostMapping(path = "/postDelete")
    Message deletePost(@RequestBody Post post){
        String title = post.getTitle();
        postRepository.deleteByTitle(title);

        Message m = new Message();
        m.message = "success";
        return m;
    }


}



