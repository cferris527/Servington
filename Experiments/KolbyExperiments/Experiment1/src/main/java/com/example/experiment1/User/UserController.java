package com.example.experiment1.User;


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

        @PostMapping(path = "/users")
        String createUser(@RequestBody User user){
            if (user == null){
                return "User not found.";
            }
            userRepository.save(user);
            return "success";
        }

        @PutMapping("/users/{id}")
        User updateUser(@PathVariable int id, @RequestBody User request){
            User user = userRepository.findById(id);
            if(user == null) {
                return null;
            }
            userRepository.save(request);
            return userRepository.findById(id);
        }



        @DeleteMapping(path = "/users/{id}")
        String deleteUser(@PathVariable int id){
            userRepository.deleteById(id);
            return "success";
        }
    }




