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
        for (i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            s = s + u.getUsername() + ", ";
        }

        return s;
    }

    //create an account
    @PostMapping({"/createAccount"})
    @ResponseBody
    public String createAccount(@RequestBody User user) {
        userList.add(user);
        return "New user " + user.getUsername() + " Saved";
    }

    //search for an account
    @GetMapping({"/search/{username}"})
    @ResponseBody
    public User getUser(@PathVariable String username) {

        for(int i = 0; i < userList.size(); i++){
            User u = userList.get(i);
            if(username.compareTo(u.getUsername()) == 0){
                return u;
            }
        }
        return null;
    }

    //delete an account
    @DeleteMapping({"/remove/{username}"})
    @ResponseBody
    public String removeUser(@PathVariable String username) {

        for(int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            if (username.compareTo(u.getUsername()) == 0) {
                userList.remove(i);
                return "User removed successfully";
            }
        }

        return "User not found";
    }

    //Edit an account
    @PutMapping({"/edit/{entry}"})
    @ResponseBody
    public String updatePerson(@PathVariable String entry, @RequestBody User u) {

        for(int i = 0; i < userList.size(); i++) {
            User curr = userList.get(i);
            if (entry.compareTo(curr.getUsername()) == 0) {
                userList.set(i, u);
                return "Success";
            }
        }
        return "user not found.";
    }


}
