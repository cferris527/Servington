package com.experiments.Demo1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.experiments.Demo1.Organization;

//Class used to control requests regarding the Organization class
@RestController
public class OrganizationController {
    public List<Organization> organizationList = new ArrayList<Organization>();
    private boolean isInit = false;

    //Must be run first, initializes the organizationList
    @GetMapping("/Organization/init")
    public List<Organization> initOrganizations() {
        organizationList.add(new Organization("Care4All", "Charity",35, 45679));
        organizationList.add(new Organization("VETS","Veterans Service", 120, 34726));
        organizationList.add(new Organization("Cancer Research", "Research Charity", 1200, 29300));
        isInit = true;
        return organizationList;
    }

    //Retrieves a list of all the organizations currently in the system
    @GetMapping("/Organization/list")
    public List<Organization> getOrganizations() {
        if(organizationList.size() > 0 && isInit){
            return organizationList;
        }
        return null;
    }

    //Retrieves organization with ID of the given orgID
    @GetMapping("/Organization/{orgID}")
    public Organization getOrganization(@PathVariable("orgID") int ID) {
        for(int i = 0; i < organizationList.toArray().length; i++ ) {
            if(organizationList.get(i).getOrganizationID() == ID){
                return organizationList.get(i);
            }
        }
        return null;
    }

    //Adds an organization given JSON data
    @PostMapping("/Organization/add")
    public @ResponseBody Organization addOrganization(@RequestBody Organization org) {
        organizationList.add(new Organization(org.getOrganizationName(), org.getOrganizationType(), org.getOrganizationNumMembers(), org.getOrganizationID()));
        return org;
    }

    //Removes organization with the given orgID
    @DeleteMapping("/Organization/remove/{orgID}")
    public String removeOrganization(@PathVariable("orgID") int updateID) {
        for(int i = 0; i < organizationList.toArray().length; i++) {
            if (organizationList.get(i).getOrganizationID() == updateID) {
                organizationList.remove(i);
            }
        }
        return "Organization with ID: " + updateID + " has been removed.";
    }

    //Updates the Organization with the given orgID using JSON from postman
    @PutMapping("/Organization/update/{orgID}")
    public Organization updateOrganization(@RequestBody Organization org, @PathVariable("orgID") int updateID) {
        updateOrganizationWithID(updateID, org);
        return org;
    }

    //Copies org into organizationList at index where the ID matches
    private Organization updateOrganizationWithID(int ID, Organization org) {
        for(int i = 0; i < organizationList.toArray().length; i++) {
            if(organizationList.get(i).getOrganizationID() == ID){
                organizationList.get(i).setOrganizationID(org.getOrganizationID());
                organizationList.get(i).setOrganizationAddress(org.getOrganizationAddress());
                organizationList.get(i).setOrganizationName(org.getOrganizationName());
                organizationList.get(i).setOrganizationStatus(org.getOrganizationStatus());
                organizationList.get(i).setOrganizationType(org.getOrganizationType());
                organizationList.get(i).setOrganizationNumMembers(org.getOrganizationNumMembers());
                organizationList.get(i).setOrganizationPhoneNumber(org.getOrganizationNumMembers());
                organizationList.get(i).setOrganizationID(org.getOrganizationID());
            }
        }
        return null;
    }
}
