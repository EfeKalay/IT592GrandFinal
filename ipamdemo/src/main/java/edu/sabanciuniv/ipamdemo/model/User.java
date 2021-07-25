package edu.sabanciuniv.ipamdemo.model;

import java.util.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private Division div;
    private String firstName;
    private String lastName;
    private boolean status;
    private Date lastLogin;
    private String role;

    public User() {
    }

    public User(String username, String password, Division div, String firstName, String lastName, boolean status, Date lastLogin, String role) {
        this.username = username;
        this.password = password;
        this.div = div;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.lastLogin = lastLogin;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Division getDiv() {
        return div;
    }

    public void setDiv(Division div) {
        this.div = div;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", div=" + div +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", lastLogin=" + lastLogin +
                ", role='" + role + '\'' +
                '}';
    }
}
