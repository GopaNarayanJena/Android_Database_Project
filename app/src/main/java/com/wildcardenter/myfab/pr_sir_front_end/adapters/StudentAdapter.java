package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.models.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private Context context;
    private List<Student> students;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentHolder(LayoutInflater.from(context).inflate(R.layout.student_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student=students.get(position);
        if(student!=null) {
            holder.name.setText(student.getName());
            holder.regNo.setText(student.getRegno());
            holder.major.setText(student.getMajor());
            holder.bday.setText(String.valueOf(student.getBdate()));
        }

    }
    public void setlist(List<Student> list){
        this.students=list;
        notifyDataSetChanged();
    }

    public Student getItemAt(int position){
        return students.get(position);
    }

    @Override
    public int getItemCount() {
        if (students!=null){
        return students.size();
        }else
        {
            return 0;
        }
    }

    public class StudentHolder extends RecyclerView.ViewHolder{

        TextView name,regNo,major,bday;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.student_name_student);
            regNo=itemView.findViewById(R.id.student_regno_student);
            major=itemView.findViewById(R.id.student_major_student);
            bday=itemView.findViewById(R.id.student_bday_student);
        }
    }
}
