package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdaptAdapter extends RecyclerView.Adapter<BookAdaptAdapter.BookViewHolder> {
    private Context context;
    private List<Book_Adaptation> book_adaptations;

    public BookAdaptAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.book_adapt_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book_Adaptation adaptation=book_adaptations.get(position);
        if (adaptation!=null){
            holder.course.setText(String.valueOf(adaptation.getCourse()));
            holder.sem.setText(String.valueOf(adaptation.getSem()));
            holder.book_isbn.setText(String.valueOf(adaptation.getBook_isbn()));
        }

    }
    public void setBook_adaptations(List<Book_Adaptation> list){
        this.book_adaptations=list;
        notifyDataSetChanged();
    }
    public Book_Adaptation getItemAt(int position){
        return book_adaptations.get(position);
    }

    @Override
    public int getItemCount() {
        if (book_adaptations!=null){
            return book_adaptations.size();
        }
        else {
            return 0;
        }

    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView course,sem,book_isbn;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            course=itemView.findViewById(R.id.Adapt_Course);
            sem=itemView.findViewById(R.id.Adapt_Sem);
            book_isbn=itemView.findViewById(R.id.Adapt_Book_isbn);
        }
    }

}
