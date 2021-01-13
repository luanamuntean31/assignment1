package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {


    private String username;

    @NotBlank
    @Size(min = 1, max = 50,message = "Size should be between 1 and 50 characters!")
    private String password;

    private UserType category;


    public User(String username, String password,UserType category){

        this.username=username;
        this.password=password;
        this.category=category;

    }

    public User(){}


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

    public UserType getCategory() {
        return category;
    }

    public void setCategory(UserType category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return username;
    }
}
