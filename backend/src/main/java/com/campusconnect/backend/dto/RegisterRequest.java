package com.campusconnect.backend.dto;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String major;
    private String bio;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}