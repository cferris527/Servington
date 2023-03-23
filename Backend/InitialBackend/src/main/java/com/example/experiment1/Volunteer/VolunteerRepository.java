package com.example.experiment1.Volunteer;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findById(int id);

    List<Volunteer> findByUsernameContaining(String username);

    @Transactional
    void deleteById(int id);



}

