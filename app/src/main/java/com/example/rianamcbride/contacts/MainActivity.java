package com.example.rianamcbride.contacts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.Manifest.permission.READ_CONTACTS;

public class MainActivity extends AppCompatActivity {
    private static List<Bitmap> images;
    private List<Contact> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;
    Cursor cursor;
    int counter;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateBarHandler = new Handler();
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Reading contacts...");
        pDialog.setCancelable(false);
        pDialog.show();
        images = new ArrayList<>();
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.dog1));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.dog2));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.dog3));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.dog4));
        mAdapter = new MyAdapter(contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Contact contact = contactList.get(position);
                editContact(contact);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... voids) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getContacts();
                    }
                });
                return 0;
            }
            @Override
            protected void onPostExecute(Integer integer) {
                recyclerView.setAdapter(mAdapter);
            }
        }.execute();
    }

    public static List<Bitmap> getImages(){
        return images;
    }
    public boolean mayRequestContacts(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            requestPermissions(new String[]{READ_CONTACTS}, 1);
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, 1);
        }
        return false;
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            }
        }
    }
    private void editContact(Contact contact) {
        Intent intent = new Intent(this, EditContact.class);
        intent.putExtra("id", contact.getId());
        intent.putExtra("firstName", contact.getfName());
        intent.putExtra("lastName", contact.getlName());
        intent.putExtra("phone", contact.getPhone());
        intent.putExtra("address", contact.getAddress());
        intent.putExtra("email", contact.getEmail());
        Bitmap image = contact.getImage();
        intent.putExtra("image", image);
        startActivityForResult(intent, 1);
    }

    public void newContact(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivityForResult(intent, 1);
    }

    public void getContacts(){
        Contact contact;
        if (!mayRequestContacts()) {
            return;
        }
        String phone = null;
        String email = null;
        String address = null;
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.ADDRESS;

        Uri AddressCONTENT_URI = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
        String AddressCONTACT_ID = ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID;
        String FORMATTED_ADDRESS = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS;

        ContentResolver contentResolver = getContentResolver();

        cursor = contentResolver.query(CONTENT_URI,null,null,null,null);
        if(cursor.getCount()>0){
            counter=0;
            while(cursor.moveToNext()){
                updateBarHandler.post(new Runnable(){
                    public void run(){
                        pDialog.setMessage("Reading contacts: "+counter++ +"/"+cursor.getCount());
                    }
                });
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if(hasPhoneNumber>0){
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id},null);
                    while(phoneCursor.moveToNext()) {
                        phone = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));

                    }
                    phoneCursor.close();
                }
                Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
                while(emailCursor.moveToNext()){
                    email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                }
                emailCursor.close();
                Cursor addressCursor = contentResolver.query(AddressCONTENT_URI, null, AddressCONTACT_ID + " = ?", new String[]{contact_id}, null);
                while(addressCursor.moveToNext()){
                    address = addressCursor.getString(addressCursor.getColumnIndex(FORMATTED_ADDRESS));
                }
                addressCursor.close();
                if(name==null){
                    name=" ";
                }
                if(address==null){
                    address=" ";
                }
                if(phone==null){
                    phone=" ";
                }
                Random random = new Random();
                int n = random.nextInt(3);
                contact = new Contact(Bitmap.createScaledBitmap(images.get(n),120,120,false), name, null, address, phone, email);
                Log.d("TEST", "Adding contact: name: "+name+", email: "+email+", phone: "+phone+", address: "+address);
                contactList.add(contact);
            }
        }
        mAdapter.notifyDataSetChanged();
        updateBarHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                pDialog.cancel();
            }
        }, 500);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                if(data.getStringExtra("new").equals("false")){
                    for(int i=0; i < contactList.size(); i++){
                        if(contactList.get(i).getId()==data.getIntExtra("id",0)){
                            contactList.get(i).setValues((Bitmap) data.getParcelableExtra("image"), data.getStringExtra("FirstName"), data.getStringExtra("LastName"), data.getStringExtra("address"), data.getStringExtra("phone"), data.getStringExtra("email"));
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
                else{
                    Contact contact = new Contact((Bitmap) data.getParcelableExtra("image"), data.getStringExtra("FirstName"), data.getStringExtra("LastName"), data.getStringExtra("address"), data.getStringExtra("phone"), data.getStringExtra("email"));
                    contactList.add(contact);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}