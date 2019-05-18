package com.wildcardenter.myfab.pr_sir_front_end.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.models.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {
    private Context context;
    private List<Text> textList;

    public TextAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.text_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        if (textList != null) {
            Text text = textList.get(position);
            holder.book_isbn.setText(String.valueOf(text.getBook_isbn()));
            holder.title.setText(text.getBook_title());
            holder.publisher.setText(text.getPublisher());
            holder.author.setText(text.getAuthor());
        }

    }

    public void setTextList(List<Text> list) {
        this.textList = list;
        notifyDataSetChanged();
    }
    public Text getItemAt(int position){
        return textList.get(position);
    }

    @Override
    public int getItemCount() {
        if (textList != null) {
            return textList.size();
        } else {
            return 0;
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView book_isbn, title, publisher, author;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            book_isbn = itemView.findViewById(R.id.text_book_isbn);
            title = itemView.findViewById(R.id.text_book_title);
            publisher = itemView.findViewById(R.id.text_book_publisher);
            author = itemView.findViewById(R.id.text_book_author);
        }
    }
}
