package com.example.experiment1.Organization;


import com.example.experiment1.Post.Post;
import com.example.experiment1.Volunteer.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;


@RestController
public class OrganizationController {

    @Autowired
    OrganizationRepository organizationRepository;



    //Returns List of all Organizations
    @GetMapping(path = "/listOrganizations")
    List<Organization> getAllOrgs(){
        return organizationRepository.findAll();
    }



    //Creates Organization account
    @PostMapping(path = "/createOrg")
    Message createOrg(@RequestBody Organization org) {
        if (org == null) {
            Message m = new Message();
            m.message = "failed";
            return m;
        }
        organizationRepository.save(org);
        Message m = new Message();
        m.message = "success";
        return m;
    }




    //THIS METHOD WORKS FOR DELETING USER. Works on frontend.
    @PostMapping(path = "/deleteOrg")
    Message deleteUser(@RequestBody Organization org){
        int id = org.getId();
        organizationRepository.deleteById(id);

        Message m = new Message();
        m.message = "success";
        return m;
    }





    //Used for login return user if username and password is valid. This is working on frontend.
    @GetMapping(path = "/orgLogin/{username}/{password}")
    public Organization login(@PathVariable String username, @PathVariable String password){
        List<Organization> listByUsername = organizationRepository.findByUsernameContaining(username);
        for(Organization org: listByUsername){
            if(org.getPassword().equals(password)){
                if(org.getUsername().equals(username)){
                    return org;
                }
            }
        }
        Organization nullUser = new Organization(null,null,null,null,null,null);
        return nullUser;
    }




    @PostMapping(path = "/orgEditFields")
    public Message editFields(@RequestBody Organization organization){
        Message m = new Message();
        if(organization == null){
            m.message = "failed";
            return m;
        }
        Organization o = organizationRepository.findById(organization.getId());

        if(organization.getPassword() != null){
            o.setPassword(organization.getPassword());
        }
        if(organization.getDisplayName() != null){
            o.setDisplayName(organization.getDisplayName());
        }
        if(organization.getPhone_number() != null){
            o.setPhone_number(organization.getPhone_number());
        }
        if(organization.getEmail() != null){
            o.setEmail(organization.getEmail());
        }

        organizationRepository.save(o);

        m.message = "success";
        return m;

    }

    @PostMapping(path = "/banOrganization/{orgUsername}")
    public Message banVolunteer(@PathVariable String orgUsername){
        Message m = new Message();
        List<Organization> o = organizationRepository.findAll();
        for(int i = 0; i < o.toArray().length; i++){
            if(o.get(i).getUsername().equals(orgUsername)){
                o.get(i).setIsBanned(true);
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }

    @PostMapping(path = "/unbanVolunteer/{orgUsername}")
    public Message unbanVolunteer(@PathVariable String orgUsername){
        Message m = new Message();
        List<Organization> o = organizationRepository.findAll();
        for(int i = 0; i < o.toArray().length; i++){
            if(o.get(i).getUsername().equals(orgUsername)){
                o.get(i).setIsBanned(false);
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }
}


