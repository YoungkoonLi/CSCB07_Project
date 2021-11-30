package com.example.utfresh;

import java.io.Serializable;

public class User implements Serializable {
    public String email;
    public String name;

    public User(String email,String name){
        this.email = email;
        this.name = name;
    }

}
