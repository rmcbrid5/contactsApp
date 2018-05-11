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
    public MyAdapter(List<Contact> contactsList){
        contactList = contactsList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.image.setImageBitmap(Bitmap.createScaledBitmap(contact.getImage(), 120, 120, false));
        if(contact.getlName()==null){
            contact.setlName(" ");
            holder.lName.setText(" ");
        }
        if(contact.getfName()==null){
            holder.fName.setText(" ");
        }
        else{
            holder.fName.setText(contact.getfName() + " "+contact.getlName());
        }
        if(contact.getAddress()==null){
            holder.address.setText(" ");
        }
        else{
            holder.address.setText(contact.getAddress());
        }
        if(contact.getPhone()==null){
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
