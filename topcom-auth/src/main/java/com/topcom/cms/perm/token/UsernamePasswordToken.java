package com.topcom.cms.perm.token;

/**
 * Created by lism on 17-6-13.
 */
public class UsernamePasswordToken {


    private String username;
    private String password;
    private Boolean admin = false;

    public UsernamePasswordToken() {
    }

    public UsernamePasswordToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
