package com.example.auth.user;

import com.example.auth.role.AppRole;
import com.example.auth.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public AppUser saveUser(AppUser user) {
        String hashPW= passwordEncoder().encode(user.getPassword());
        user.setPassword(hashPW);
        userRepository.save(user) ;
        return user ;

    }

    public List<AppUser> getUsers() {
        List<AppUser> users=userRepository.findAll();
        return users;
    }

    public AppUser getUser(Long id) {
        AppUser user=userRepository.findById(id).get();
        //User s= userRepository.findOne().get();
        return user;
    }

    public AppUser addRole(Long userId, Long rolesId) {
        AppUser user=userRepository.findById(userId).get();
        AppRole role=roleRepository.findById(rolesId).get();
        user.getRoles().add(role);
        userRepository.save(user);
        return  user;
    }
    public AppUser getUserByEmail(String email){
        AppUser user=userRepository.findByEmail(email);
        return user;
    }
}
