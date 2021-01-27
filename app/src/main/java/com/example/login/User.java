package com.example.login;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String kelamin;
    @NotNull
    private String telepon;

    @Generated(hash = 1688215093)
    public User(Long id, @NotNull String name, @NotNull String address,
            @NotNull String kelamin, @NotNull String telepon) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.kelamin = kelamin;
        this.telepon = telepon;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getKelamin() { return this.kelamin; }
    public void setKelamin(String kelamin) { this.kelamin = kelamin; }
    public String getTelepon() { return this.telepon; }
    public void setTelepon(String telepon) { this.telepon = telepon; }
}