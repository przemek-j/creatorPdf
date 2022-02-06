package com.pjada.GeneratorPdf.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String userName;
    private String password;
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Watermark> watermarks;

    public User(String name, String password, String roles) {
        this.userName = name;
        this.password = password;
        this.roles = roles;
        this.watermarks = new ArrayList<Watermark>();
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Watermark> getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(List<Watermark> watermarks) {
        this.watermarks = watermarks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", watermarks='" + watermarks.toString() + '\'' +
                '}';
    }
}
