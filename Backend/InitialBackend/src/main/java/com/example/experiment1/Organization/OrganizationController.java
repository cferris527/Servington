package com.example.experiment1.Organization;

import com.example.experiment1.Admin.Admin;
import com.example.experiment1.Team.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lists all Organizations", description = "Return a JSON array of all of the organizations in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all organizatoins.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
    @GetMapping(path = "/listOrganizations")
    List<Organization> getAllOrgs(){
        return organizationRepository.findAll();
    }



    //Creates Organization account
    @Operation(summary = "Create an Organization", description = "An organization is created based on the JSON object of the request body." +
            "Example url: /createOrg --> An organization containing the attributes from the response body is added to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created an organization", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
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
    @Operation(summary = "Delete an Organization", description = "The organization whose ID matches the ID in the response body is removed from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted an organization", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
    @PostMapping(path = "/deleteOrg")
    Message deleteUser(@RequestBody Organization org){
        int id = org.getId();
        organizationRepository.deleteById(id);

        Message m = new Message();
        m.message = "success";
        return m;
    }





    //Used for login return user if username and password is valid. This is working on frontend.
    @Operation(summary = "Organization login", description = "A organization attempting to login in with their credentials. If there is a credentials match in the database, the Admin object is sent to the frontend and they are logged in." +
            "If there is not a match, an empty organization object is sent to the frontend and the user is denied login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in as organization", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
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



    @Operation(summary = "Edit Fields of an Organization", description = "An organization is edited to match the JSON object of the request body." +
            "Example url: /orgEditFields --> An organization with an ID that matches the ID from the response body is edited to match the JSON response body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created an organization", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
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
        if(organization.getProfilePictureUrl() != null){
            o.setProfilePictureUrl(organization.getProfilePictureUrl());
        }

        organizationRepository.save(o);

        m.message = "success";
        return m;

    }

    @Operation(summary = "Ban Organization", description = "An organization with the username from the URL path is banned." +
            "Example url: /banOrganization/{orgUsername} --> An organization with username from the path is not allowed to login again.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created an organization", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
    @PostMapping(path = "/banOrganization/{orgUsername}")
    public Message banOrganization(@PathVariable String orgUsername){
        Message m = new Message();
        List<Organization> o = organizationRepository.findAll();
        for(int i = 0; i < o.toArray().length; i++){
            if(o.get(i).getUsername().equals(orgUsername)){
                o.get(i).setIsBanned(true);
                organizationRepository.save(o.get(i));
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }

    @Operation(summary = "Unban Organization", description = "An organization with the username from the URL path is unbanned." +
            "Example url: /unbanOrganization/{orgUsername} --> An organization with username from the path is allowed to login again.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created an organization", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)))
    })
    @PostMapping(path = "/unbanOrganization/{orgUsername}")
    public Message unbanOrganization(@PathVariable String orgUsername){
        Message m = new Message();
        List<Organization> o = organizationRepository.findAll();
        for(int i = 0; i < o.toArray().length; i++){
            if(o.get(i).getUsername().equals(orgUsername)){
                o.get(i).setIsBanned(false);
                organizationRepository.save(o.get(i));
                m.message = "success";
                return m;
            }
        }
        m.message = "failed";
        return m;
    }

    @GetMapping(path = "/getTeamFromOrgID/{ordID}")
    public Team getTeamFromOrgID(@PathVariable int orgID){
        Organization o = organizationRepository.findById(orgID);
        Team t = o.getOrgTeam();
        return t;
    }
}


