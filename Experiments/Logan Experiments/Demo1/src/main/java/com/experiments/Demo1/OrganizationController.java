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

@RestController
public class OrganizationController {
    public List<Organization> organizationList = new ArrayList<Organization>();
    private boolean isInit = false;

    @GetMapping("/Organization/init")
    public List<Organization> initOrganizations() {
        organizationList.add(new Organization("Care4All", "Charity",35, 45679));
        organizationList.add(new Organization("VETS","Veterans Service", 120, 34726));
        organizationList.add(new Organization("Cancer Research", "Research Charity", 1200, 29300));
        isInit = true;
        return organizationList;
    }

    @GetMapping("/Organization/list")
    public List<Organization> getOrganizations() {
        if(organizationList.size() > 0 && isInit){
            return organizationList;
        }
        return null;
    }

    @GetMapping("/Organization/{orgID}")
    public Organization getOrganization(@PathVariable("orgID") int ID) {
        for(int i = 0; i < organizationList.toArray().length; i++ ) {
            if(organizationList.get(i).getOrganizationID() == ID){
                return organizationList.get(i);
            }
        }
        return null;
    }

    @PostMapping("/Organization/add")
    public @ResponseBody Organization addOrganization(@RequestBody Organization org) {
        organizationList.add(new Organization(org.getOrganizationName(), org.getOrganizationType(), org.getOrganizationNumMembers(), org.getOrganizationID()));
        return org;
    }

    @PutMapping("/Organization/update/{orgID}")
    public Organization updateOrganization(@RequestBody Organization org, @PathVariable("orgID") int updateID) {
        updateOrganizationWithID(updateID, org);
        return org;
    }

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
