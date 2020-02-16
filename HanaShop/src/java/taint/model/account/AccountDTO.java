/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.account;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class AccountDTO implements Serializable{
    private String userID;
    private String fullName;
    private String password;
    private boolean role = false;       //1:admin, 0:member

    public AccountDTO() {
    }

    public AccountDTO(String email, String name, String password) {
        this.userID = email;
        this.fullName = name;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
    
}
