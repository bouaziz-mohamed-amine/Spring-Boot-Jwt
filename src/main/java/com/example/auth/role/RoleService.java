package com.example.auth.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository= roleRepository;
    }

    public List<AppRole>getRoles(){
        return roleRepository.findAll();
    }
    public  AppRole getRole(Long id){

        return  roleRepository.findById(id).get();
    }

    public void addRole(AppRole role){

        roleRepository.save(role);
    }

}
