package com.example.auth.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailService userDetailService;

    //public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
    //    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    //}
    // get users

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication().withUser("admin").password("amine").roles("ADMIN","USER");
        auth.userDetailsService(userDetailService);

    }
    //basic auth from web browser
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf disable get acces to post and put method disabled csrf
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // antmatchers  ---> get access to some url without auth
        http.authorizeRequests().antMatchers("/api/posts/**").permitAll();
        http.authorizeRequests().antMatchers("/api/users/**").permitAll();
        http.authorizeRequests().antMatchers("/api/login").permitAll();
        // only admin can use method get
        http.authorizeRequests().antMatchers(HttpMethod.GET, "api/user/**").hasAuthority("ADMIN");
        //
        http.authorizeRequests().anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

}
