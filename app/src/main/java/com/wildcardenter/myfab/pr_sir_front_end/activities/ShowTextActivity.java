package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;
import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.TextViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.TextAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.helpers.SwipeToDeleteCallback;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Text;

public class ShowTextActivity extends AppCompatActivity {

    private static final int TEXT_EDIT_RC =334 ;
    private static final int TEXT_UPDATE_RC = 1897;

    private RecyclerView showTextRecycler;
    private TextAdapter adapter;
    private TextViewModel textViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        showTextRecycler=findViewById(R.id.show_text_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        showTextRecycler.setLayoutManager(manager);
        adapter=new TextAdapter(this);
        textViewModel= ViewModelProviders.of(this).get(TextViewModel.class);
        showTextRecycler.setAdapter(adapter);
        textViewModel.getAllTextList().observe(this,list->{
            adapter.setTextList(list);
            adapter.notifyDataSetChanged();
        });

        SwipeToDeleteCallback callback=new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Alerter.create(ShowTextActivity.this)
                            .enableInfiniteDuration(true)
                            .setDismissable(false)
                            .setBackgroundColorInt(Color.parseColor("#EF5350"))
                            .setTitle("Delete Book Adoption Details?")
                            .setText("Are You Sure You Want To Delete Book Adoption Detail?")
                            .addButton("Confirm", R.style.AlertButton, v -> {
                                textViewModel.deleteText(adapter.getItemAt(viewHolder.getAdapterPosition()));
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                                Toast.makeText(ShowTextActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Alerter.hide();
                            })
                            .addButton("Cancel", R.style.AlertButton, v -> {
                                Alerter.hide();
                                adapter.notifyDataSetChanged();
                            })
                            .setIcon(R.drawable.ic_delete_sweep_black_24dp)
                            .show();

                } else {
                    Intent intent = new Intent(ShowTextActivity.this, EditTextActivity.class);
                    Text text = adapter.getItemAt(viewHolder.getAdapterPosition());
                    intent.putExtra("pk1", text.getBook_isbn());
                    startActivityForResult(intent, TEXT_UPDATE_RC);
                }

            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(showTextRecycler);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String title = data.getStringExtra("title");
                final String publisher = data.getStringExtra("publisher");
                final String author = data.getStringExtra("author");
                final int isbn = data.getIntExtra("isbn", 1);
                Text text = new Text(isbn, title, publisher,author);
                textViewModel.insertText(text);
            }


            Toast.makeText(this, "Text Saved", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == TEXT_UPDATE_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String title = data.getStringExtra("title");
                final String publisher = data.getStringExtra("publisher");
                final String author = data.getStringExtra("author");
                final int isbn = data.getIntExtra("isbn", 1);
                final int pk1=data.getIntExtra("pk1",9999);
                textViewModel.updateText(String.valueOf(isbn),title,publisher,author, String.valueOf(pk1));
            }


            Toast.makeText(this, "Text Updated", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Text Cancelled", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }

    public void openTextEditActivity(View view) {
        Intent intent = new Intent(this, EditTextActivity.class);
        startActivityForResult(intent, TEXT_EDIT_RC);
    }
}
