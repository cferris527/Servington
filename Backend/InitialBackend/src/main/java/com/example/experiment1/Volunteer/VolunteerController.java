package com.example.experiment1.Volunteer;


import com.example.experiment1.Admin.Admin;
import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Post.Post;
import com.example.experiment1.Post.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;


@RestController
public class VolunteerController {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    PostRepository postRepository;


    @Operation(summary = "List all Volunteers", description = "Return a JSON array of all of the volunteer accounts in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all volunteers.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
    @GetMapping(path = "/listVolunteers")
    List<Volunteer> getAllVolunteers(){
        return volunteerRepository.findAll();
    }



    @Operation(summary = "Create a Volunteer", description = "A volunteer is created based on the JSON object of the request body. " +
            "Example url: /createVolunteer --> A volunteer containing the attributes from the response body is added to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created a volunteer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
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



    @Operation(summary = "Delete a Volunteer", description = "The volunteer whose ID matches the ID in the response body is removed from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a volunteer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
    @PostMapping(path = "/deleteVolunteer")
    Message deleteUser(@RequestBody Volunteer v){
        int id = v.getId();
        volunteerRepository.deleteById(id);

        Message m = new Message();
        m.message = "success";
        return m;
    }


    @Operation(summary = "Volunteer login", description = "A user attempting to login in with their volunteer credentials. If there is a credentials match in the database, the Volunteer object is sent to the frontend and they are logged in." +
            "If there is not a match, an empty volunteer object is sent to the frontend and the user is denied login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in as volunteer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
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


    @Operation(summary = "Volunteer edit fields", description = "A volunteer user can edit their descriptive fields: email, displayName, etc. The fields that are present in the response body JSON object will be written to the fields of the current volunteer in the database,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the volunteer's fields", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
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
        if(volunteer.getProfilePictureUrl() != null){
            v.setProfilePictureUrl(volunteer.getProfilePictureUrl());
        }

        volunteerRepository.save(v);

        m.message = "success";
        return m;
    }

    @Operation(summary = "Ban Volunteer", description = "An volunteer with the username from the URL path is banned." +
            "Example url: /banVolunteer/{volUsername} --> An volunteer with username from the path is not allowed to login again.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully banned volunteer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
    @PostMapping(path = "/banVolunteer/{volUsername}")
    public Message banVolunteer(@PathVariable String volUsername){
        Message m = new Message();
        boolean b = true;
        List<Volunteer> v = volunteerRepository.findAll();
        for(int i = 0; i < v.toArray().length; i++){
            if(v.get(i).getUsername().equals(volUsername)){
                v.get(i).setIsBanned(b);
                volunteerRepository.save(v.get(i));
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }

    @Operation(summary = "Unban Volunteer", description = "An volunteer with the username from the URL path is unbanned." +
            "Example url: /unbanVolunteer/{volUsername} --> An volunteer with username from the path is allowed to login again.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully unbanned a volunteer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Volunteer.class)))
    })
    @PostMapping(path = "/unbanVolunteer/{volUsername}")
    public Message unbanVolunteer(@PathVariable String volUsername){
        Message m = new Message();
        List<Volunteer> v = volunteerRepository.findAll();
        for(int i = 0; i < v.toArray().length; i++){
            if(v.get(i).getUsername().equals(volUsername)){
                v.get(i).setIsBanned(false);
                volunteerRepository.save(v.get(i));
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }



    @Operation(summary = "List volunteer's events", description = "All of the events the given volunteer have signed up for are listed. " +
            "Example url: /listEvents/5  --> All posts volunteer with ID 5 has signed up for will be returned in a JSON array.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all events")
    })
    @GetMapping(path = "/listEvents/{volID}")
    public List<Post> listEvents(@PathVariable int volID){

        /*List<Post> allposts = postRepository.findAll();

        ArrayList<Post> events;

        for (Post p : allposts){
            List<Volunteer> signedUp
            p.getVolunteers()
        }*/

        Volunteer v = volunteerRepository.findById(volID);

        return v.getEvents();



    }
}

