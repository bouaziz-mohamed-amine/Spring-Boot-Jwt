package com.example.auth.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users").toUriString());
        AppUser newUser=  userService.saveUser(user);
        return ResponseEntity.created(uri).body(newUser) ;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        List<AppUser> users=userService.getUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable("id") Long id){
        AppUser user=userService.getUser( id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/users/{userId}/roles/{rolesId}")
    public AppUser addRole(@PathVariable("userId") Long userId,@PathVariable("rolesId") Long rolesId){
       AppUser user=  userService.addRole(userId,rolesId);
       return user;
    }
//    @PostMapping("/login/{email}")
//    public AppUser login(@PathVariable("email") String email){
//        AppUser user=userService.getUserByEmail(email);
//        return user;
//    }

}
