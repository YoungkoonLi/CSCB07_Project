package com.example.utfresh;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class testLogin {
    @Mock
    MainActivity view;

    @Mock
    MyModel model;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}