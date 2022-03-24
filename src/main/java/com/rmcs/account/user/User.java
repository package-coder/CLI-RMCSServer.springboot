package com.rmcs.account.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class User {

    @Id
    private UUID id = UUID.randomUUID();
    //TODO password

    @Column(nullable = false)
    private String userName;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String email;
    private Boolean isSuperuser;
    private Boolean isStaff = true;
    private Boolean isActive = true;

    public User(String userName, String firstName, String lastName, String email, Boolean isSuperuser, Boolean isStaff, Boolean isActive) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isSuperuser = isSuperuser;
        this.isStaff = isStaff;
        this.isActive = isActive;
    }

    public User(String userName, String email, Boolean isSuperuser) {
        this.userName = userName;
        this.email = email;
        this.isSuperuser = isSuperuser;
    }

    public User(String userName, String firstName, String lastName, String email, Boolean isSuperuser) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isSuperuser = isSuperuser;
    }

    public User() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuperuser() {
        return isSuperuser;
    }

    public void setSuperuser(Boolean superuser) {
        isSuperuser = superuser;
    }

    public Boolean getStaff() {
        return isStaff;
    }

    public void setStaff(Boolean staff) {
        isStaff = staff;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
