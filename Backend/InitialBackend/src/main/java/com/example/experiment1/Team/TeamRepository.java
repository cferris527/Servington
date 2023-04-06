package com.example.experiment1.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findById(int id);

    List<Team> findAll();

    @Transactional
    void deleteById(int id);

}

