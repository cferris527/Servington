package com.example.experiment1.User;


import com.example.experiment1.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(int id);
    List<User> findByUsernameContaining(String username);
    List<User> findByAccountTypeContaining(String keyword);
    @Transactional
    void deleteById(int id);



}
