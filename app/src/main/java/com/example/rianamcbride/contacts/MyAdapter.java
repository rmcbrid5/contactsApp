package com.example.rianamcbride.contacts;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Contact> contactList;
    //create the adapter so that the recyclerview can communicate with the main activity
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fName, lName, phone, address;
        public ImageView image;
        public MyViewHolder(View v){
            super(v);
            image = v.findViewById(R.id.image);
            fName = (TextView) v.findViewById(R.id.fName);
            lName = (TextView) v.findViewById(R.id.lName);
            phone = (TextView) v.findViewById(R.id.phone);
            address = (TextView) v.findViewById(R.id.address);
        }
    }
    //make the adapter hold the list of contacts
    public MyAdapter(List<Contact> contactsList){
        contactList = contactsList;
    }
    @NonNull
    @Override
    //when you create a view holder, get the context from the parent and set it into layout type of the contact_list_row
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //when the new data is binded to the view holder
        Contact contact = contactList.get(position);
        //where the image is, set the bitmap to whatever the contact image is
        holder.image.setImageBitmap(Bitmap.createScaledBitmap(contact.getImage(), 120, 120, false));
        //set all of the other data holders
        if(contact.getlName()==null){
            contact.setlName("");
            holder.lName.setText(" ");
        }
        if(contact.getfName()==null){
            contact.setfName("");
            holder.fName.setText(" ");
        }
        else{
            holder.fName.setText(contact.getfName() + " "+contact.getlName());
        }
        if(contact.getAddress()==null){
            contact.setAddress("");
            holder.address.setText(" ");
        }
        else{
            holder.address.setText(contact.getAddress());
        }
        if(contact.getPhone()==null){
            contact.setPhone("");
            holder.address.setText(" ");
        }
        else{
            holder.phone.setText(contact.getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return contactList == null ? 0 : contactList.size();
    }
}
