package com.example.experiment1.Organization;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findById(int id);

    List<Organization> findByUsernameContaining(String username);

    @Transactional
    void deleteById(int id);



}
