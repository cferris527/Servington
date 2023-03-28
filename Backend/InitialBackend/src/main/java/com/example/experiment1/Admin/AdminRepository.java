package com.example.experiment1.Admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findById(int id);

    @Transactional
    void deleteById(int id);

}
