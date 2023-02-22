package com.example.experiment1;

import com.example.experiment1.User.User;
import com.example.experiment1.User.UserController;
import com.example.experiment1.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class Experiment1Application {

    public static void main(String[] args) {
        SpringApplication.run(Experiment1Application.class, args);

    }

    /*@Bean
    CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            User user1 = new User("kolbykuc", "password", "volunteer" );
            User user2 = new User("Jane", "123", "ORG");
            User user3 = new User("Justin", "hello", "volunteer");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }*/

}
