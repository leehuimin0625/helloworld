package com.example.helloworld.studentrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

    class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
        private ArrayList<Student> studentList;
        private Context context;

    public StudentAdapter(Context context, ArrayList<Student> list){
        this.studentList =list;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_single_row_student, parent, false);

        return new StudentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentAdapter.ViewHolder holder, int position) {
        Student student = studentList.get(position);

        holder.tvMatric.setText(student.getMatric());
        holder.tvName.setText(student.getName());
        holder.tvCourseName.setText(student.getCourseName());
        Picasso.get().load(student.getPhoto()).into(holder.imStudent);
    }

    @Override
    public int getItemCount(){
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imStudent;
        public TextView tvMatric,tvName,tvCourseName;

        public ViewHolder(View itemView){
            super(itemView);

            tvMatric = itemView.findViewById(R.id.tvMacricNo);
            tvName = itemView.findViewById(R.id.tvName);
            tvCourseName = itemView.findViewById(R.id.tvCoursename);
            imStudent= itemView.findViewById(R.id.imStudent);
        }
    }
}