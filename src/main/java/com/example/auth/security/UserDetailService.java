package com.example.auth.security;


import com.example.auth.role.AppRole;
import com.example.auth.user.AppUser;
import com.example.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=userService.getUserByEmail(username);
        if (user==null) {
            throw new UsernameNotFoundException("USER not found ");
        }
     //   Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
    //    user.getRoles().forEach(appRole -> {
    //        authorities.add(new SimpleGrantedAuthority(appRole.getRole()));
     //  });
       // System.out.println(authorities);
      //  return new User(user.getEmail(),user.getPassword(),authorities);
         else return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
        //return new User("amine",passwordEncoder().encode("amine0000"),new ArrayList<>());
    }

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
