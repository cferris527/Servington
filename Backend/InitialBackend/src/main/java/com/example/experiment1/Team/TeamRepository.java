package com.example.experiment1.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findById(int id);

    Team findByName(String name);

    List<Team> findAll();

    @Transactional
    void deleteById(int id);

}

