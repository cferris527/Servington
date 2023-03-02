package com.example.experiment1.User;


import com.example.experiment1.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;


    @RestController
    public class UserController {

        @Autowired
        UserRepository userRepository;

        //Returns List of all USERS
        @GetMapping(path = "/users")
        List<User> getAllUsers(){ return userRepository.findAll(); }

        //Gets USER by ID
        @GetMapping(path = "/users/id/{id}")
        User getUserById( @PathVariable int id){
            return userRepository.findById(id);
        }

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

        //Takes a list of USERS and creates them (used for postman testing mostly)
        @PostMapping(path = "/users/multiple")
        String createMultipleUsers(@RequestBody List<User> userList) {
            if(userList == null)
                return "Invalid Input Format";
            for (User user : userList) {
                userRepository.save(user);
            }
            return "Success";
        }

        //WIP working on allowing update user to happen
        /*
        @PutMapping("/users/{id}")
        User updateUser(@PathVariable int id, @RequestBody User request){
            User user = userRepository.findById(id);
            if(user == null) {
                return null;
            }
            userRepository.deleteById(id);
            return userRepository.findById(id);
        }*/

        //Intended to create a post for a user but not doing what intended
        @PutMapping("/users/{userId}/post")
        String createPost(@PathVariable int userId, @RequestBody Post post) {
            User user = userRepository.findById(userId);
            if (user == null)
                return "failure";
            //post.setUser(user);
            //user.addPosts(post);
            return "success";
        }




        //THIS METHOD WORKS FOR DELETING USER
        @PostMapping(path = "/usersDelete")
        Message deleteUser(@RequestBody User user){
            int id = user.getId();
            userRepository.deleteById(id);

            Message m = new Message();
            m.message = "success";
            return m;
        }




        //Gets all accounts of the type specified by keyword
        @GetMapping(path = "/users/account/{keyword}")
        public List<User> getUsersByAccountTypeContaining(@PathVariable String keyword){
            return userRepository.findByAccountTypeContaining(keyword);
        }

        //Gets all accounts containing username specified by keyword
        @GetMapping(path = "/users/username/{keyword}")
        public List<User> getUsersByUsernameContaining(@PathVariable String keyword){
            return userRepository.findByUsernameContaining(keyword);
        }

        //Used for login return user if username and pa
        @GetMapping(path = "/users/{username}/{password}")
        public User getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
            List<User> listByUsername = userRepository.findByUsernameContaining(username);
            for(User user: listByUsername){
                if(user.getPassword().equals(password)){
                    if(user.getUsername().equals(username)){
                        return user;
                    }
                }
            }
            return null;
        }
    }





