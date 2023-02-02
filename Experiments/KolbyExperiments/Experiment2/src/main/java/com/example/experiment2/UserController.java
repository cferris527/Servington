package com.example.experiment2;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * User Controller Class using the CRUDL method
 * A very basic implementation of how we could add users
 * Works with Postman
 * @author Kolby Kucera
 */
@RestController
public class UserController {

    public ArrayList<User> userList = new ArrayList<User>();

    //returns a list of all the users
    @GetMapping({"/UserList"})
    @ResponseBody
    public String printList(){
        String s = "";
        int i;
        for (i = 0; i < userList.size() - 1; i++){
            User u = userList.get(i);
            s = s + u.getUsername() + ", ";
        }
        User u = userList.get(i);
        s = s + u.getUsername() + ", ";

        return s;
    }

    @PostMapping({"/createAccount"})
    @ResponseBody
    public String createAccount(@RequestBody User user) {
        userList.add(user);
        return "New user " + user.getUsername() + " Saved";
    }

    @GetMapping({"/search/{username}"})
    @ResponseBody
    public User getUser(@PathVariable String username) {
        User u;
        for(int i = 0; i < userList.size(); i++){
            u = userList.get(i);
            if(username == u.getUsername()){
                System.out.println(u.getUsername());
                return u;
            }
        }
        return null;
    }


    @DeleteMapping({"/remove/{username}"})
    @ResponseBody
    public String removeUser(@PathVariable String username) {

        for(int i = 0; i < userList.size(); i++){
            User u = userList.get(i);
            if(username == u.getUsername()){
                userList.remove(i);
                return "User successfully removed";
            }
        }
        return "User not found";
    }


}
