package fr.test.hibernate.app.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String userName;

    private String password;

    public void setProfiles(Set<UserProfile> profiles) {
        this.profiles = profiles;
    }

    @ElementCollection(targetClass=UserProfile.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="user_profile")
    @Column(name="user_profile")
    private Set<UserProfile> profiles;

    public Set<UserProfile> getProfiles() {
        return profiles;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }







}
