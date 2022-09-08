package com.artemkononov.webapplication.services.security;

import com.artemkononov.webapplication.entities.security.Role;
import com.artemkononov.webapplication.entities.security.User;
import com.artemkononov.webapplication.repositories.security.RoleRepository;
import com.artemkononov.webapplication.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


    @Service
    public class UserService implements UserDetailsService {
        @PersistenceContext
        private EntityManager em;

        UserRepository userRepository;
        RoleRepository roleRepository;

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        @Autowired
        public UserService(UserRepository userRepository, RoleRepository roleRepository) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return user;
        }

        public User findUserById(Long userId) {
            Optional<User> userFromDb = userRepository.findById(userId);
            return userFromDb.orElse(new User());
        }

        public List<User> allUsers()
        {
            return userRepository.findAll();
        }

        public List<Role> allRoles()
        {
            return roleRepository.findAll();
        }


        public boolean saveUser(User user) {
            User userFromDB = userRepository.findByUsername(user.getUsername());

            if (userFromDB != null)
                return false;

            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }

        public boolean editUser(User user) {
            Optional<User> row = userRepository.findById(user.getId());
            if (row.isPresent()) {
                User updatedUser = row.get();

                if (!user.getUsername().isEmpty())
                    updatedUser.setUsername(user.getUsername());

                if (user.getPassword() != null)
                    updatedUser.setPassword(user.getPassword());

                if (user.getRoles() != null)
                    updatedUser.setRoles(user.getRoles());


                userRepository.save(updatedUser);
            }
            return true;
        }

        public boolean deleteUser(Long userId) {
            if (userRepository.findById(userId).isPresent()) {
                userRepository.deleteById(userId);
                return true;
            }
            return false;
        }

    }

