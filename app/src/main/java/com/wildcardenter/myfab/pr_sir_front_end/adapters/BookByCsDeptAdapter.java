package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.models.TextByCs;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookByCsDeptAdapter extends RecyclerView.Adapter<BookByCsDeptAdapter.ByCsDeptViewHolder> {
    private Context context;
    private List<TextByCs> allTextByCs;

    public BookByCsDeptAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ByCsDeptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ByCsDeptViewHolder(LayoutInflater.from(context).inflate(R.layout.book_by_cs_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ByCsDeptViewHolder holder, int position) {
        TextByCs textByCs=allTextByCs.get(position);
        if(textByCs!=null){
            holder.bookByCsCourse.setText(String.valueOf(textByCs.getCourse()));
            holder.bookByCsIsbn.setText(String.valueOf(textByCs.getBook_isbn()));
            holder.bookByCsTitle.setText(textByCs.getBook_title());
        }

    }

    @Override
    public int getItemCount() {
        if (allTextByCs==null) {
            return 0;
        }
        else {
            return allTextByCs.size();
        }
    }
    public void setAllTextByCs(List<TextByCs> allTextByCs){
        this.allTextByCs=allTextByCs;
        notifyDataSetChanged();
    }

    class ByCsDeptViewHolder extends RecyclerView.ViewHolder{
        TextView bookByCsCourse,bookByCsIsbn,bookByCsTitle;

        public ByCsDeptViewHolder(@NonNull View itemView) {
            super(itemView);
            bookByCsCourse=itemView.findViewById(R.id.BookByCsBCourse);
            bookByCsIsbn=itemView.findViewById(R.id.BookByCsBIsbn);
            bookByCsTitle=itemView.findViewById(R.id.BookByCsBTitle);
        }
    }
}
