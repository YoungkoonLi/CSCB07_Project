package com.example.utfresh;

import java.util.function.Consumer;

public interface Contract {
    public interface Model{
        public void LogIn(String email, String password, Consumer<User> callback);
    }
    public interface View{
        public void Remember();
        public void CustomerSignUp(android.view.View view);
        public void StoreSignUp(android.view.View view);
        public void setLogin();
        public void toCustomer();
        public void toStore();
        public void OnError();
        public void NotFound();
        public void displayMessage(String message);
    }
    public interface Presenter{
        public void SignIn(String email, String password, boolean isCus, boolean isStore);
    }
}