package com.example.helloworld.sqlitedemo.room;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.example.helloworld.sqlitedemo.ViewContactActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    private List<Contact>contactList;
    private Context context;

    public ContactAdapter(Context context, List<Contact>list){
        this.contactList = list;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhoneNumber());
        holder.rootLayout.setOnClickListener(view -> {
            //Open View Contact Activity
            Intent i = new Intent(context, ViewContactActivity.class);
            i.putExtra(ViewContactActivity.KEY_ID, contact.getId());
            i.putExtra(ViewContactActivity.KEY_NAME, contact.getName());
            i.putExtra(ViewContactActivity.KEY_NUMBER, contact.getPhoneNumber());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateData(List<Contact> newContactList){
        contactList.clear();
        contactList = newContactList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvPhoneNumber;
        public LinearLayout rootLayout;

        public ViewHolder(View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.name);
            tvPhoneNumber = itemView.findViewById(R.id.phoneNumber);
            rootLayout = itemView.findViewById(R.id.single_row_root);
        }
    }
}