package com.tugas.uas_rentalmobil;

public class UserHelperClass {

    String namaLengkap, username, email, noTelepon, password;

    public UserHelperClass() {

    }

    public UserHelperClass(String namaLengkap, String username, String email, String noTelepon, String password) {
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.email = email;
        this.noTelepon = noTelepon;
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
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

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
