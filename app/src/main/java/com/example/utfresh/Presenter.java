package com.example.utfresh;

import android.content.Intent;

public class Presenter implements Contract.Presenter{
    private Model model;
    private MainActivity view;

    public Presenter(Model model, MainActivity view){
        this.model = model;
        this.view = view;
    }
//    public void signIn(String email, String password, boolean isCus, boolean isStore){
//        model.LogIn(email, password, (User user) -> {
//            String cus = "cus";
//            String s = "store";
//            if(user == null){
//                view.NotFound();
//            }else if(user.type.equals(cus) && isCus) {
//                view.toCustomer();
//            }else if(user.type.equals(s) && isStore){
//                view.toStore();
//            }else{
//                view.OnError();
//            }
//        });
//    }

    @Override
    public void SignIn(String email, String password, boolean isCus, boolean isStore) {
        model.LogIn(email, password, (User user) -> {
            String cus = "cus";
            String s = "store";
//            String name = user.name;
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

