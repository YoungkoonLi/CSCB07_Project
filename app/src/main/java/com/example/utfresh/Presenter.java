package com.example.utfresh;

import android.content.Intent;
import com.example.utfresh.MainActivity;
import com.example.utfresh.Model;
import com.example.utfresh.User;

public class Presenter{
    private Model model;
    private MainActivity view;

    public Presenter(Model model, MainActivity view){
        this.model = model;
        this.view = view;
    }
    public void signIn(String email, String password, boolean isCus, boolean isStore){
        model.LogIn(email, password, (User user) -> {
            String cus = "cus";
            String s = "store";
            if(user == null){
                view.NotFound();
            }else if(user.type.equals(cus) && isCus) {
                view.toCustomer();
            }else if(user.type.equals(s) && isStore){
                view.toStore();
            }else{
                view.OnError();
            }
        });
    }
}

