package com.example.utfresh;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;

import java.util.function.Consumer;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class testPresenter() {
    MainActivity view;

    Model model;

    @Captor
    ArgumentCaptor<Consumer<User>> captor;


    @Test
    public void testSignIn_empty_email() {

        String email = "";
        String password = "password";
        boolean Cus = true;
        boolean Store = false;
        User user = new User();
        String message1 = "Error!";
        String message2 = "email is required!";
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message1);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message2);

        /*** Verifying order ***/
        InOrder order = inOrder(view, view);
        order.verify(view).displayMessage(message2);

    }

    @Test
    public void testSignin_empty_password() {

        String email = "user@user.com";
        String password = "";
        boolean Cus = true;
        boolean Store = false;
        String message = "password is required";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(view, view);
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_invalid_email() {

        String email = "user";
        String password = "password";
        boolean Cus = true;
        boolean Store = false;
        String message = "Please provide valid email!";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(view, view);
        order.verify(view).Patterns.EMAIL_ADDRESS.matcher(email).matches();
        order.verify(view).displayMessage(message);

    }


    @Test
    public void testSignin_invalid_password() {

        String email = "user@user.com";
        String password = "0";
        boolean Cus = true;
        boolean Store = false;
        String message = "Password should have at least 6 characters!";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(view, view);
        order.verify(view).pass.length();
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_user_NotFound() {

        String email = "user@user.com";
        String password = "password";
        boolean Cus = true;
        boolean Store = false;
        String message = "Error!";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view, view);
        order.verify(model).LogIn(email,
                password,
                captor.capture());
        order.verify(view).NotFound();
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_ToCustomer() {

        String email = "user@user.com";
        String password = "password";
        boolean Cus = true;
        boolean Store = false;
        String message = "customer logged successfully!";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view, view);
        order.verify(model).LogIn(email, password, captor.capture());
        order.verify(view).toCustomer();
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_ToStore() {

        String email = "user@user.com";
        String password = "password";
        boolean Cus = false;
        boolean Store = true;
        String message = "store logged successfully!";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view, view);
        order.verify(model).LogIn(email, password, captor.capture());
        order.verify(view).toStore();
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_no_selected_type() {

        String email = "user@user.com";
        String password = "password";
        boolean Cus = false;
        boolean Store = false;
        String message1 = "Please select login as Customer or StoreOwner!";
        String message2 = "Please select correct user type!";
        User user = new User();
        Consumer<User> callback = captor.getValue();

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        verify(model).LogIn(email, password, captor.capture());
        callback.accept(user);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email, password, Cus, Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message1);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message1);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view);
        order.verify(model).LogIn(email, password, captor.capture());
        order.verify(view).displayMessage(message1);

    }
}