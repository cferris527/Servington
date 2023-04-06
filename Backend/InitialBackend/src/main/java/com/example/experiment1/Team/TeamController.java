package com.example.experiment1.Team;

import org.springframework.web.bind.annotation.RestController;
import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.experiment1.Message;

import java.util.List;


@RestController
public class TeamController {

    @Autowired TeamRepository teamRepository;

    @Autowired OrganizationRepository organizationRepository;

    @PostMapping(path = "/createTeam/{orgID}/{teamName}")
    Message createTeam(@PathVariable int orgID, @PathVariable String teamName){
        Message m = new Message();
        if (!organizationRepository.existsById((long)orgID)) {
            m.message = "failed";
            return m;
        }
        Organization o = organizationRepository.findById(orgID);
        Team t = new Team(o, teamName);
        o.setOrgTeam(t);
        t.setOrganization(o);
        teamRepository.save(t);
        m.message = "success";
        return m;
    }

    @GetMapping(path = "/listTeams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping(path = "/getTeam/{teamID}")
    Team getTeamByOrg(@PathVariable int teamID){
        Team t = teamRepository.findById(teamID);
        return t;
    }

    @PostMapping(path = "/deleteTeam/{teamID}")
    Message deleteTeamById(@PathVariable int teamID){
        Message m = new Message();
        if(teamRepository.existsById((long)teamID)){
            teamRepository.deleteById(teamID);
            m.message = "success";
            return m;
        }
        m.message = "Deletion failed, Check if teamID exists";
        return m;
    }
}
