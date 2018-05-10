package com.example.rianamcbride.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class DisplayMessageActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText phoneNum;
    EditText add;
    EditText mail;
    Button button;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        firstName = findViewById(R.id.editText);
        lastName = findViewById(R.id.editText2);
        phoneNum = findViewById(R.id.editText4);
        add = findViewById(R.id.editText3);
        mail = findViewById(R.id.editText6);
        button = findViewById(R.id.button);
        imageButton = findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createContact();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try{
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageButton.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 120, 120, false));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void createContact(){
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String phone = phoneNum.getText().toString();
        String address = add.getText().toString();
        String email = mail.getText().toString();
        Bitmap image = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
        if(fName.equals("")&&lName.equals("")&&phone.equals("")&&email.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(DisplayMessageActivity.this).create();
            alertDialog.setTitle("Incomplete Contact");
            alertDialog.setMessage("Please fill in more information");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }else{
            // Get the Intent that started this activity and extract the string
            Intent returnIntent = new Intent();
            returnIntent.putExtra("FirstName", fName);
            returnIntent.putExtra("LastName", lName);
            returnIntent.putExtra("phone", phone);
            returnIntent.putExtra("address", address);
            returnIntent.putExtra("email", email);
            returnIntent.putExtra("image", image);
            returnIntent.putExtra("new", "true");
            setResult(Activity.RESULT_OK, returnIntent);
            Toast.makeText(getApplicationContext(), fName + " " + lName + " has been saved as a contact.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}
