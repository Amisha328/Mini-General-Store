package com.amisha.generalstore;

public class UserRec {
    String username;
    String password;
    String regname;
    String phone;
    String email;

    public UserRec(String username, String password, String regname, String phone, String email) {
        this.username = username;
        this.password = password;
        this.regname = regname;
        this.phone = phone;
        this.email = email;
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

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public UserRec()
    {

    }
}


