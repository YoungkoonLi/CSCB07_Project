package com.example.utfresh;

import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Test
    public void testSignin() {

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

}