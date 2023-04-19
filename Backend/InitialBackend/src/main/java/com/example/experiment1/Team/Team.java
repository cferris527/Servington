package com.example.experiment1.Team;

import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Volunteer.Volunteer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    @JsonIgnore
    private Organization organization;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "volunteer_to_team",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "volunteer_id") })
    @JsonIgnore
    private List<Volunteer> volunteers;

    public Team(Organization org, String name){
        organization = org;
        this.name = name;
    }

    public Team() {

    }
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Organization getOrganization(){ return organization; }

    public void setOrganization(Organization o){ organization = o; }

    public String getName(){ return name; }

    public void setName(String name) { this.name = name; }
}
