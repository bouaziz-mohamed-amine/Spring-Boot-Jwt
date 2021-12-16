package com.example.auth.security;

public class SignInResponse {
    private String jwt;

    public SignInResponse() {
    }

    public SignInResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
