package com.example.auth.security;

import com.example.auth.user.AppUser;
import com.example.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public SignInResponse login(@RequestBody SignInRequest signInRequest){

        final Authentication  auth=authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getpassword())
          );
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserDetails userDetails=userDetailService.loadUserByUsername(signInRequest.getEmail());
        String token =jwtUtil.generateToken(userDetails);
        SignInResponse signInResponse=new SignInResponse(token);
        return signInResponse;
    }
}
