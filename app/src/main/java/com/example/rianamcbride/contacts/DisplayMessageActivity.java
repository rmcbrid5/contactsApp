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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class DisplayMessageActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText phoneNum;
    EditText add;
    EditText mail;
    Button button;
    ImageButton imageButton;
    List<Bitmap> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        firstName = findViewById(R.id.editText);
        lastName = findViewById(R.id.editText2);
        phoneNum = findViewById(R.id.editText4);
        phoneNum.addTextChangedListener(new TextWatcher() {
            int keyDel;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNum.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    int len = phoneNum.getText().length();
                    if(len == 3||len == 7) {
                        phoneNum.setText(phoneNum.getText() + "-");
                        phoneNum.setSelection(phoneNum.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        add = findViewById(R.id.editText3);
        mail = findViewById(R.id.editText6);
        button = findViewById(R.id.button);
        imageButton = findViewById(R.id.imageButton);
        Random random = new Random();
        int n = random.nextInt(3);
        images = MainActivity.getImages();
        imageButton.setImageBitmap(Bitmap.createScaledBitmap(images.get(n),300,300,false));
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
                imageButton.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 300, 300, false));
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
        if(!isValidContact(fName, lName, phone, email)){
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
        }
        else if(!isValidPhone(phone)){
            AlertDialog alertDialog = new AlertDialog.Builder(DisplayMessageActivity.this).create();
            alertDialog.setTitle("Phone number is invalid");
            alertDialog.setMessage("Please enter a valid phone number");
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
    public boolean isValidContact(String fName, String lName, String phone, String email){
        return !(fName.equals("")&&lName.equals("")&&phone.equals("")&&email.equals(""));
    }
    public boolean isValidPhone(String phone){
        return !Pattern.matches("[a-zA-Z]+", phone);
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
