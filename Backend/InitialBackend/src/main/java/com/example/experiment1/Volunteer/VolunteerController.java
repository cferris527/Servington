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

    @PostMapping(path = "/volunteerEditFields")
    public Message editFields(@RequestBody Volunteer volunteer){
        Message m = new Message();
        if(volunteer == null){
            m.message = "failed";
            return m;
        }
        Volunteer v = volunteerRepository.findById(volunteer.getId());

        if(volunteer.getPassword() != null){
            v.setPassword(volunteer.getPassword());
        }
        if(volunteer.getDisplayName() != null){
            v.setDisplayName(volunteer.getDisplayName());
        }
        if(volunteer.getPhone_number() != null){
            v.setPhone_number(volunteer.getPhone_number());
        }
        if(volunteer.getEmail() != null){
            v.setEmail(volunteer.getEmail());
        }

        volunteerRepository.save(v);

        m.message = "success";
        return m;
    }

    @PostMapping(path = "/banVolunteer/{volUsername}")
    public Message banVolunteer(@PathVariable String volUsername){
        Message m = new Message();
        List<Volunteer> v = volunteerRepository.findAll();
        for(int i = 0; i < v.toArray().length; i++){
            if(v.get(i).getUsername().equals(volUsername)){
                v.get(i).setIsBanned(true);
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }

    @PostMapping(path = "/unbanVolunteer/{volUsername}")
    public Message unbanVolunteer(@PathVariable String volUsername){
        Message m = new Message();
        List<Volunteer> v = volunteerRepository.findAll();
        for(int i = 0; i < v.toArray().length; i++){
            if(v.get(i).getUsername().equals(volUsername)){
                v.get(i).setIsBanned(false);
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }
}

