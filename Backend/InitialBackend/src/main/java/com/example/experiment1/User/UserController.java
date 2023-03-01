package com.example.experiment1.User;


import com.example.experiment1.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    public class UserController {

        @Autowired
        UserRepository userRepository;

        //Returns List of USERS
        @GetMapping(path = "/users")
        List<User> getAllUsers(){ return userRepository.findAll(); }

        //Gets USER by ID
        @GetMapping(path = "/users/id/{id}")
        User getUserById( @PathVariable int id){
            return userRepository.findById(id);
        }

        //gets user by username (NOT WORKING YET)
        @GetMapping(path = "/users/username/{username}")
        User getUserByUsername( @PathVariable String username) { return userRepository.findByUsername(username); }

        //Creates USER
        @PostMapping(path = "/users")
        Message createUser(@RequestBody User user) {
            if (user == null) {
                Message m = new Message();
                m.message = "failed";
                return m;
            }
            userRepository.save(user);
            Message m = new Message();
            m.message = "success";
            return m;
        }

        //Takes a list of USERS and creates them
        @PostMapping(path = "/users/multiple")
        String createMultipleUsers(@RequestBody List<User> userList) {
            if(userList == null)
                return "Invalid Input Format";
            for(int i = 0; i < userList.size(); i++) {
                userRepository.save(userList.get(i));
            }
            return "Success";
        }

        //Used to update User Information by id (NOT WORKING YET) returns by ID??
        @PutMapping("/users/{id}")
        User updateUser(@PathVariable int id, @RequestBody User request){
            User user = userRepository.findById(id);
            if(user == null) {
                return null;
            }
            userRepository.save(request);
            return userRepository.findById(id);
        }

        //Intended to create a post for a user but not doing what intended
        @PutMapping("/users/{userId}/post")
        String createPost(@PathVariable int userId, @RequestBody Post post){
            User user = userRepository.findById(userId);
            if(user == null)
                return "failure";
            //post.setUser(user);
            //user.addPosts(post);
            return "success";
        }

        //changes password (NOT WORKING YET)
        @PutMapping("/users/{username}/changepassword")
        String changePassword(@PathVariable String username, @RequestBody User user){
            User userEdit = userRepository.findByUsername(username);
            if(user == null)
                return "failure";
            userEdit.setPassword(userEdit.getPassword());
            return "success";
        }

        //Deletes user by ID
        @DeleteMapping(path = "/users/{id}")
        String deleteUser(@PathVariable int id){
            userRepository.deleteById(id);
            return "success";
        }
    }

    class Message {
        String message;
    }



