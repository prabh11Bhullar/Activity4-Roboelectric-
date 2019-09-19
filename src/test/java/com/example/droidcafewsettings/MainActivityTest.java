//Student Name:Satwinder Kaur
//Student ID:C0748256


package com.example.droidcafewsettings;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
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

import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowToast;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import android.widget.ImageView;

@RunWith(RobolectricTestRunner.class)
//TC1(requirements):R1. Get Froyo ImageView button using id
//                  R2. Check if the ImageView exists
//                  R3. Click the Froyo Image to add it to Orders
//                  R4. Get the Toast using ShadowToast
//                  R5. Check if the Toast is not null
//                  R6. Check if the toast text matches "You ordered a FroYo."


//TC2(requirements):R1. Get Floating Shopping Cart button
//                  R2. Click the Cart Button
//                  R3. Get the EditText/Input Views
//                  R4. Get the Address Edit Text,Phone Edit Text,Note Edit text
//                  R5. Give input to the Text fields
//                  R6. Get the Delivery Speed Radio Buttons
//                  R7. Set same Day Delivery Option to selected
//                  R8. Get the 'save Customer Info' Button
//                  R9. Click the save button
//                 R10. Press the System back button to go to previous activity.
//                 R11. Click the Shopping cart Button Again
//                 R12. Check if Order activity is started
//                 R13. Get the text from Text edit fields.
//                 R14. Check if the User Info. in Edit Text fields matches the info. input gave earlier.
public class MainActivityTest {

    private MainActivity activity;
    private View view;
     private Dialog dialog;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
        view = LayoutInflater.from(activity).inflate(R.layout.activity_order, null);
    }

    //TC1
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

    //TC2

    @Test
    public void CartRememberInfoTest() {

        FloatingActionButton crtbtn = activity.findViewById(R.id.fab);
        crtbtn.performClick();
        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(OrderActivity.class.getCanonicalName(), intent.getComponent().CartRememberInfoTest());


        EditText edttxt = (EditText) view.findViewById(R.id.name_text);
        System.out.println(edttxt.getText());
        edttxt.setText("Kotkapura");

        EditText edtaddress = view.findViewById(R.id.address_text);

        EditText edtphn = view.findViewById(R.id.phone_text);

        EditText edtmsg = view.findViewById(R.id.note_text);


        edttxt.setText("Satwinder Bhullar");

        edtaddress.setText("Toronto");

        edtphn.setText("567898698");

        edtmsg.setText("Express delivery");

        RadioButton rb1 = view.findViewById(R.id.sameday);
        RadioButton rb2 = view.findViewById(R.id.nextday);
        RadioButton rb3 = view.findViewById(R.id.pickup);

        rb1.setChecked(true);

        dialog.setContentView(R.layout.activity_order);
        final Button b = (Button) dialog.findViewById(R.id.saveButton);
        crtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assertNotNull("Button is null",b);
                b.performClick();

            }
        });

        activity.onBackPressed();
        activity.finish();

        Intent mainActivityIntent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertEquals(MainActivity.class.getCanonicalName(), mainActivityIntent.getComponent().CartRememberInfoTest());

        crtbtn.performClick();

        Intent orderActivityIntent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(OrderActivity.class.getCanonicalName(), orderActivityIntent.getComponent().CartRememberInfoTest());

        String name = edttxt.getText().toString();
        String address = edtaddress.getText().toString();
        String phone = edtphn.getText().toString();


        assertEquals("Satwinder Bhullar",name);
        assertEquals("Toronto", address);
        assertEquals("567898698", phone);

    }
}
