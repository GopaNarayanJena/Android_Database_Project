package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private Context context;
    private List<Course> courseList;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.course_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if (courseList!=null){
            Course course=courseList.get(position);
            holder.course.setText(String.valueOf(course.getCourse()));
            holder.cname.setText(course.getCname());
            holder.cdept.setText(course.getDept());
        }

    }
    public void setCourseList(List<Course> list){
        this.courseList=list;
        notifyDataSetChanged();
    }
    public Course getItemAt(int position){
        return courseList.get(position);
    }

    @Override
    public int getItemCount() {
        if (courseList!=null){
            return courseList.size();
        }
        else {
        return 0;
        }
    }

    class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView course,cname,cdept;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            course=itemView.findViewById(R.id.course_course_info);
            cname=itemView.findViewById(R.id.course_cname_info);
            cdept=itemView.findViewById(R.id.course_dept_info);
        }
    }
}
