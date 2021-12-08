package com.example.utfresh;

import java.io.Serializable;

public class User implements Serializable {
    public String email;
    public String name;
    public String type;

    public User(){
    }

    public User(String email,String name, String type){
        this.email = email;
        this.name = name;
        this.type = type;
    }

}
