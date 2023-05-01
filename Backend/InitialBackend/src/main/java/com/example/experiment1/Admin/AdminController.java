package com.example.experiment1.Admin;

import com.example.experiment1.Message;

import com.example.experiment1.Post.Post;
import com.example.experiment1.Volunteer.Volunteer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;


    //Return List of all admins
    @Operation(summary = "List all Admins", description = "Return a JSON array of all of the admin accounts in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all admins.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)))
    })
    @GetMapping(path = "/listAdmins")
    List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    //Creates an Admin
    @Operation(summary = "Create an Admin", description = "An admin is created based on the JSON object of the request body." +
            "Example url: /createAdmin --> An admin containing the attributes from the response body is added to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created an admin", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)))
    })
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
    @Operation(summary = "Delete an Admin", description = "The admin whose ID matches the ID in the response body is removed from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted an admin", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)))
    })
    @PostMapping(path = "/deleteAdmin")
    Message deleteAdmin(@RequestBody Admin admin){
        int id = admin.getId();
        adminRepository.deleteById(id);

        Message m = new Message();
        m.message = "success";
        return m;
    }


    @Operation(summary = "Admin login", description = "A user attempting to login in with admin credentials. If there is a credentials match in the database, the Admin object is sent to the frontend and they are logged in." +
            "If there is not a match, an empty admin object is sent to the frontend and the user is denied login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in as admin", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class)))
    })
    @GetMapping(path = "/adminLogin/{username}/{password}")
    public Admin login(@PathVariable String username, @PathVariable String password){
        List<Admin> listByUsername = adminRepository.findByUsernameContaining(username);
        for(Admin a: listByUsername){
            if(a.getPassword().equals(password)){
                if(a.getUsername().equals(username)){
                    return a;
                }
            }
        }
        Admin nullUser = new Admin(null,null);
        return nullUser;
    }


}
