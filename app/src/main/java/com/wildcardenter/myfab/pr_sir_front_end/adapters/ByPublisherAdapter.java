package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ByPublisherAdapter extends RecyclerView.Adapter<ByPublisherAdapter.ByPublisherViewHolder> {
    private Context context;
    private List<String> allDept;

    public ByPublisherAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ByPublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ByPublisherViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.by_dept_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ByPublisherViewHolder holder, int position) {
        if (allDept != null) {
            holder.by_dept.setText(allDept.get(position));
        }

    }

    public void setAllDept(List<String> depts) {
        this.allDept = depts;
    }


    @Override
    public int getItemCount() {
        if (allDept != null) {
            return allDept.size();
        } else {
            return 0;
        }
    }

    class ByPublisherViewHolder extends RecyclerView.ViewHolder {
        private TextView by_dept;

        ByPublisherViewHolder(@NonNull View itemView) {
            super(itemView);
            by_dept = itemView.findViewById(R.id.by_dept_dept);
        }
    }
}
