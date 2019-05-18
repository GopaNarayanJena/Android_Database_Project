package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EnrollAdapter extends RecyclerView.Adapter<EnrollAdapter.EnrollViewHolder> {
    private Context context;
    private List<Enroll> enrolls;

    public EnrollAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EnrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EnrollViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.enroll_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollViewHolder holder, int position) {
        Enroll enroll=enrolls.get(position);
        if(enroll!=null){
            holder.regno.setText(enroll.getRegno());
            holder.course.setText(String.valueOf(enroll.getCourse()));
            holder.sem.setText(String.valueOf(enroll.getSem()));
            holder.marks.setText(String.valueOf(enroll.getMarks()));
        }

    }
    public void setEnrolls(List<Enroll> list){
        this.enrolls=list;
        notifyDataSetChanged();
    }
    public Enroll getItemAt(int position){
        return enrolls.get(position);
    }

    @Override
    public int getItemCount() {
        if (enrolls!=null){
            return enrolls.size();
        }
        else {
        return 0;
        }
    }

    class EnrollViewHolder extends RecyclerView.ViewHolder{
        private TextView regno,course,sem,marks;

        public EnrollViewHolder(@NonNull View itemView) {
            super(itemView);
            regno=itemView.findViewById(R.id.Enroll_regno);
            course=itemView.findViewById(R.id.Enroll_course);
            sem=itemView.findViewById(R.id.Enroll_sem);
            marks=itemView.findViewById(R.id.Enroll_Marks);
        }
    }
}
