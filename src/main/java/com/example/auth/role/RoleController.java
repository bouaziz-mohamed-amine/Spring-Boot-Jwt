package com.example.auth.role;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public List<AppRole> getRoles(){
        List<AppRole> roles=roleService.getRoles();
        return roles;
    }
    @PostMapping()
    public AppRole addRole( @RequestBody AppRole role){
        roleService.addRole(role);
        return role;
    }
}
