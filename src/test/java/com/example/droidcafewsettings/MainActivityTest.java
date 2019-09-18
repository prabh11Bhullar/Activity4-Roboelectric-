package com.example.droidcafewsettings;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.widget.Button;

import static org.hamcrest.CoreMatchers.equalTo;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.robolectric.shadows.ShadowToast;

import android.widget.Toast;

import android.widget.ImageView;

@RunWith(RobolectricTestRunner.class)
//TC1(requirements):R1. Get Froyo ImageView button using id
//                  R2. Check if the ImageView exists
//                  R3. Click the Froyo Image to add it to Orders
//                  R4. Get the Toast using ShadowToast
//                  R5. Check if the Toast is not null
//                  R6. Check if the toast text matches "You ordered a FroYo."
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

@Test
    public void TestForFroyoOrdering(){

        //R1. Get Froyo ImageView button using id
        ImageView button = activity.findViewById(R.id.froyo);
        //R2. Check if the ImageView exists
        assertNotNull("Froyo Button cannot be found",button);
        //R3. Click the Froyo Image to add it to Orders
        button.performClick();
        //R4. Get the Toast using ShadowToast
        Toast toastForOrder = ShadowToast.getLatestToast();
        //R5. Check if the Toast is not null
        assertNotNull("Toast is not null",toastForOrder);
        //R6. Check if the toast text matches "You ordered a FroYo."
        Assert.assertEquals("You ordered a FroYo.", ShadowToast.getTextOfLatestToast());

    }





}
