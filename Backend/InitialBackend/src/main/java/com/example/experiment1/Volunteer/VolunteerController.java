package com.example.experiment1.Volunteer;


import com.example.experiment1.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;


@RestController
public class VolunteerController {

    @Autowired
    VolunteerRepository volunteerRepository;



    //Returns List of all Organizations
    @GetMapping(path = "/listVolunteers")
    List<Volunteer> getAllVolunteers(){
        return volunteerRepository.findAll();
    }



    //Creates Organization account
    @PostMapping(path = "/createVolunteer")
    Message createVolunteer(@RequestBody Volunteer v) {
        if (v == null) {
            Message m = new Message();
            m.message = "failed";
            return m;
        }
        volunteerRepository.save(v);
        Message m = new Message();
        m.message = "success";
        return m;
    }




    //THIS METHOD WORKS FOR DELETING USER. Works on frontend.
    @PostMapping(path = "/deleteVolunteer")
    Message deleteUser(@RequestBody Volunteer v){
        int id = v.getId();
        volunteerRepository.deleteById(id);

        Message m = new Message();
        m.message = "success";
        return m;
    }





    //Used for login return user if username and password is valid. This is working on frontend.
    @GetMapping(path = "/volunteerLogin/{username}/{password}")
    public Volunteer login(@PathVariable String username, @PathVariable String password){
        List<Volunteer> listByUsername = volunteerRepository.findByUsernameContaining(username);
        for(Volunteer v: listByUsername){
            if(v.getPassword().equals(password)){
                if(v.getUsername().equals(username)){
                    return v;
                }
            }
        }
        Volunteer nullUser = new Volunteer(null,null,null,null,null,null);
        return nullUser;
    }





}

