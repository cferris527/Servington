package com.example.experiment1.Admin;

import com.example.experiment1.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;


    //Return List of all admins
    @GetMapping(path = "/listAdmins")
    List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    //Creates an Admin
    @PostMapping(path = "/createAdmin")
    Message createAdmin(@RequestBody Admin admin) {
        if (admin == null) {
            Message m = new Message();
            m.message = "failed";
            return m;
        }
        adminRepository.save(admin);
        Message m = new Message();
        m.message = "success";
        return m;
    }



    //Deletes an Admin by ID
    @PostMapping(path = "/deleteAdmin")
    Message deleteAdmin(@RequestBody Admin admin){
        int id = admin.getId();
        adminRepository.deleteById(id);

        Message m = new Message();
        m.message = "success";
        return m;
    }

}
