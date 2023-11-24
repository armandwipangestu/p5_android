package com.latihan.rentalmobil;

public class UserHelperClass {

    String nama_lengkap, username, email, no_telepon, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String nama_lengkap, String username, String email, String no_telepon, String password) {
        this.nama_lengkap = nama_lengkap;
        this.username = username;
        this.email = email;
        this.no_telepon = no_telepon;
        this.password = password;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
