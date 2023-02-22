package com.example.experiment1.User;


import com.example.experiment1.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    public class UserController {

        @Autowired
        UserRepository userRepository;

        @GetMapping(path = "/users")
        List<User> getAllUsers(){
            return userRepository.findAll();
        }

        @GetMapping(path = "/users/{id}")
        User getUserById( @PathVariable int id){
            return userRepository.findById(id);
        }

        @GetMapping(path = "/users/{username}")
        User getUserByUsername( @PathVariable String username) { return userRepository.findByUsername(username); }

        @PostMapping(path = "/users")
        String createUser(@RequestBody User user) {
            if (user == null) {
                return "User not found.";
            }
            userRepository.save(user);
            return "success";
        }

        /*@PutMapping("/users/{id}")
        User updateUser(@PathVariable int id, @RequestBody User request){
            User user = userRepository.findById(id);
            if(user == null) {
                return null;
            }
            userRepository.save(request);
            return userRepository.findById(id);
        }*/

        //Intended to create a post for a user but not doing what intended
        @PutMapping("/users/{userId}")
        String createPost(@PathVariable int userId, @RequestBody Post post){
            User user = userRepository.findById(userId);
            if(user == null)
                return "failure";
            post.setUser(user);
            user.addPosts(post);
            return "success";
        }


        @DeleteMapping(path = "/users/{id}")
        String deleteUser(@PathVariable int id){
            userRepository.deleteById(id);
            return "success";
        }
    }




