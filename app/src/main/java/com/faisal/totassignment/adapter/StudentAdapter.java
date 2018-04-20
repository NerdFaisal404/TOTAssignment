package com.faisal.totassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faisal.totassignment.R;
import com.faisal.totassignment.data.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private Context context;
    private List<Student> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvInstitution, tvMobileNo, tvEmailAddress;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvInstitution = view.findViewById(R.id.tv_institution);
            tvMobileNo = view.findViewById(R.id.tv_mobile_no);
            tvEmailAddress = view.findViewById(R.id.tv_email_address);

        }
    }


    public StudentAdapter(Context context, List<Student> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student student = notesList.get(position);

        holder.tvName.setText(student.getName());
        holder.tvInstitution.setText(student.getName());
        holder.tvMobileNo.setText(student.getName());
        holder.tvEmailAddress.setText(student.getName());

        // Displaying dot from HTML character code
        //holder.dot.setText(Html.fromHtml("&#8226;"));

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
