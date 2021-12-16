package com.example.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<AppUser,Long> {

    @Query("select e from AppUser e where e.email=:email")
    public AppUser findByEmail(String email);


}
