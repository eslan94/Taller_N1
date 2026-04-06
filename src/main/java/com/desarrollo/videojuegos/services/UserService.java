package com.desarrollo.videojuegos.services;

import com.desarrollo.videojuegos.models.Role;
import com.desarrollo.videojuegos.models.User;
import com.desarrollo.videojuegos.repository.RoleRepository;
import com.desarrollo.videojuegos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public User save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(()->{
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });
        user.getRoles().add(userRole);
        return this.userRepository.save(user);
    }
}
