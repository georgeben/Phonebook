package com.kurobarabenjamingeorge.phonebook.Data;

/**
 * Created by George Benjamin on 10/21/2018.
 */

public class Contact {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String bio;

    public Contact(String name, String phone, String email, String address, String bio) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
