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
    @GetMapping("/init")
    public List<Organization> initOrganizations() {
        organizationList.add(new Organization("Care4All", "Charity",35, 45679));
        organizationList.add(new Organization("VETS","Veterans Service", 120, 34726));
        organizationList.add(new Organization("Cancer Research", "Research Charity", 1200, 29300));
        isInit = true;
        return organizationList;
    }

    @GetMapping("/Organization")
    public List<Organization> getOrganizations() {
        if(organizationList.size() > 0){
            return organizationList;
        }
        return null;
    }
    @GetMapping("/")
    public String getWelcomeString() {
        return "Welcome to my springboot experimentation!";
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
}
