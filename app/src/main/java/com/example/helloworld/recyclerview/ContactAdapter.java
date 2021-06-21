package com.example.helloworld.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList <Contact> contactList;
    private Context context;

    public ContactAdapter(Context context, ArrayList<Contact> list){
        this.contactList=list;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhonenumber());
    }

    @Override
    public int getItemCount(){
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvPhoneNumber;

        public ViewHolder(View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.John);
            tvPhoneNumber = itemView.findViewById(R.id.phonenumber);


        }
    }
}
