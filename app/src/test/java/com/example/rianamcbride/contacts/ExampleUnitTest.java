package com.example.rianamcbride.contacts;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {
    private MainActivity mainActivity;
    @Before
    public void setup(){
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }
    @Test
    public void checkNewView() {
        FloatingActionButton button = mainActivity.findViewById(R.id.fab);
        button.performClick();
        Intent intent = shadowOf(mainActivity).peekNextStartedActivity();
        assertEquals(DisplayMessageActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }
    @Test
    public void checkToastMessage(){
        DisplayMessageActivity activity = Robolectric.setupActivity(DisplayMessageActivity.class);
        EditText fName = activity.findViewById(R.id.editText);
        fName.setText("John");
        EditText phone = activity.findViewById(R.id.editText4);
        phone.setText("555-555-5555");
        Button button = activity.findViewById(R.id.button);
        button.performClick();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo(fName.getText().toString() + "  has been saved as a contact."));
    }
    @Test
    public void checkAlertDialogMessage(){
        DisplayMessageActivity activity = Robolectric.setupActivity(DisplayMessageActivity.class);
        Button button = activity.findViewById(R.id.button);
        button.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(dialog);
        ShadowAlertDialog alertDialog = shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        String expectedText = "Incomplete Contact";
        assertEquals(alertDialog.getTitle(), expectedText);
    }
    @Test
    public void testRecyclerViewClick(){
        RecyclerView recyclerView = mainActivity.findViewById(R.id.recycler_view);
        recyclerView.measure(0,0);
        recyclerView.layout(0,0,100,1000);
        recyclerView.findViewHolderForAdapterPosition(1).itemView.performClick();
        Intent intent = shadowOf(mainActivity).peekNextStartedActivity();
        assertEquals(EditContact.class.getCanonicalName(), intent.getComponent().getClassName());
    }
}