package com.artemkononov.webapplication.configurations;

import com.artemkononov.webapplication.entities.security.Role;
import com.artemkononov.webapplication.entities.security.User;
import com.artemkononov.webapplication.repositories.security.RoleRepository;
import com.artemkononov.webapplication.repositories.security.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class StartConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository){


        List<User> users = new ArrayList<>();

        Role user_role= new Role(1L,"ROLE_USER");
        Role admin_role=new Role(2L,"ROLE_ADMIN");

        roleRepository.save(user_role);
        roleRepository.save(admin_role);

        Set<Role> set1=new HashSet<>();
        Set<Role> set2=new HashSet<>();

        set1.add(user_role);
        set2.add(user_role);
        set2.add(admin_role);

        User user=new User(null,"Petr","$2a$12$yJq/35VKaBMQT.JMoaznpueJxCvgGvkziieT6sAG2EUCieJab.qU6","Petr@mail.ru",set1);
        User admin=new User(null,"Stepan","$2a$12$yJq/35VKaBMQT.JMoaznpueJxCvgGvkziieT6sAG2EUCieJab.qU6","Stepan@mail.ru",set2);

        users.add(user);
        users.add(admin);

        userRepository.saveAll(users);
        return null;

    }
}
