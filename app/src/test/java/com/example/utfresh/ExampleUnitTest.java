package com.example.utfresh;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.util.Patterns;
import android.widget.TextView;

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
public class testPresenter(){
    @Mock
    MainActivity view;

    @Mock
    Model model;

    @Captor
    ArgumentCaptor<Consumer<User>> captor;


    @Test
    public void testSignIn_empty_email(){

        String email = "";
        String password = "password";
        boolean Cus = true;
        boolean Store = true;
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
        presenter.SignIn(email,password, Cus, Store);

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
    public void testSignin_empty_password(){

        String email = "user@user.com";
        String password = "";
        boolean Cus = true;
        boolean Store = true;
        String message = "password is required";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(view, view);
        order.verify(view).TextUtils.isEmpty(pass);
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_invalid_email(){

        String email = "user";
        String password = "password";
        boolean Cus = true;
        boolean Store = true;
        String message = "Please provide valid email!";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

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
    public void testSignin_invalid_password(){

        String email = "user@user.com";
        String password = "0";
        boolean Cus = true;
        boolean Store = true;
        String message = "Password should have at least 6 characters!";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

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
    public void testSignin_user_NotFound(){

        String email = "user@user.com";
        String password = "password";
        boolean Cus = true;
        boolean Store = true;
        String message = "Error!";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(null);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

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
    public void testSignin_ToCustomer(){

        String email = "user@user.com";
        String password = "password";
        boolean Cus = true;
        boolean Store = true;
        String message = "customer logged successfully!";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view, view);
        order.verify(model).LogIn(email,password,Consumer<User> callback );
        order.verify(view).toCustomer();
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_ToStore(){

        String email = "user@user.com";
        String password = "password";
        boolean Cus = false;
        boolean Store = true;
        String message = "store logged successfully!";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view, view);
        order.verify(model).LogIn(email,password,Consumer<User> callback );
        order.verify(view).toStore();
        order.verify(view).displayMessage(message);

    }

    @Test
    public void testSignin_onError(){

        String email = "user@user.com";
        String password = "password";
        boolean Cus = false;
        boolean Store = false;
        String message1 = "Please select login as Customer or StoreOwner!";
        String message2 = "Please select correct user type!";

        /** stubbing */
        when(view.editEmail.getText().toString().trim()).thenReturn(email);
        when(view.editPassword.getText().toString().trim()).thenReturn(password);
        when(model.LogIn(email,password,Consumer<User> callback )).thenCallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        presenter.SignIn(email,password,Cus,Store);

        /** verifying displayMessage with specific string value */
        verify(view).displayMessage(message1);

        /*** Argument captors ***/
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).displayMessage(captor.capture());
        assertEquals(captor.getValue(), message1);

        /*** Verifying order ***/
        InOrder order = inOrder(model, view);
        order.verify(model).LogIn(email,password,Consumer<User> callback );
        order.verify(view).displayMessage(message1);

    }



        //mockito
        when(model.LogIn("a@a.com","123",Consumer<User> callback)).thenCallback(null);
        when(model.LogIn("b@b.com","123456", Consumer<User> callback)).thencallback.accept(customer);

        Presenter presenter = new Presenter(model, view);
        //1st case
        presenter.SignIn(null,null,null,null);

        verify(view).NotFound();
        onView(withText(R."Error")).inRoot(new ToastMatcher()) .check(matches(isDisplayed()));

        //case 1.5
        presenter.SignIn("a@a.com","123",null,null);
        verify(view).NotFound();
        onView(withText(R."Error")).inRoot(new ToastMatcher()) .check(matches(isDisplayed()));

        //2nd case
        presenter.SignIn("b@b.com","123456",false,false);
        verify(view).OnError();
        onView(withText(R."Please select correct user type!")).inRoot(new ToastMatcher()) .check(matches(isDisplayed()));

        //3rd case
        presenter.SignIn("b@b.com","123456",true,false);
        verify(view).toCustomer();
        onView(withText(R."customer logged successfully!")).inRoot(new ToastMatcher()) .check(matches(isDisplayed()));

        //4th case
        presenter.SignIn("b@b.com","123456",true,false);
        verify(view).toStore();
        onView(withText(R."store logged successfully!")).inRoot(new ToastMatcher()) .check(matches(isDisplayed()));

        //
        onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));





    }

    }
    @RunWith(RobolectricTestRunner.class)
    public class MyActivityTest {

        @Before
        public void setup() {
            Robolectric.buildActivity(MyActivity.class).create();
        }

        @Test
        public void testToast() {
            assertTrue(ShadowToast.showedCustomToast("Some message", R.string.some_message));
            assertThat(ShadowToast.getTextOfLatestToast().toString(), equalTo("Some message"));
        }
    }


    //这个放到view里面
public void displayMessage(String message){
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        }