package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.BookAdaptViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.BookAdaptAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.helpers.SwipeToDeleteCallback;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;

public class ShowAdaptionActivity extends AppCompatActivity {

    private static final int ADAPT_EDIT_RC =444 ;
    private static final int ADAPT_UPDATE_RC =8761 ;

    private RecyclerView adaptRecycler;
    private BookAdaptAdapter adapter;
    private BookAdaptViewModel adaptViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adaption);
        adaptViewModel= ViewModelProviders.of(this).get(BookAdaptViewModel.class);
        adaptRecycler=findViewById(R.id.show_Adapt_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        adaptRecycler.setLayoutManager(manager);
        adapter=new BookAdaptAdapter(this);
        adaptRecycler.setAdapter(adapter);
        adaptViewModel.getAllAdaptList().observe(this,adapts->{
            adapter.setBook_adaptations(adapts);
            adapter.notifyDataSetChanged();
        });

        SwipeToDeleteCallback callback=new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Alerter.create(ShowAdaptionActivity.this)
                            .enableInfiniteDuration(true)
                            .setDismissable(false)
                            .setBackgroundColorInt(Color.parseColor("#EF5350"))
                            .setTitle("Delete Book Adoption Details?")
                            .setText("Are You Sure You Want To Delete Book Adoption Detail?")
                            .addButton("Confirm", R.style.AlertButton, v -> {
                                adaptViewModel.deleteAdapt(adapter.getItemAt(viewHolder.getAdapterPosition()));
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                                Toast.makeText(ShowAdaptionActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Alerter.hide();
                            })
                            .addButton("Cancel", R.style.AlertButton, v -> {
                                Alerter.hide();
                                adapter.notifyDataSetChanged();
                            })
                            .setIcon(R.drawable.ic_delete_sweep_black_24dp)
                            .show();

                } else {
                    Intent intent = new Intent(ShowAdaptionActivity.this, EditAdaptActivity.class);
                    Book_Adaptation adaptation = adapter.getItemAt(viewHolder.getAdapterPosition());
                    intent.putExtra("pk1", adaptation.getCourse());
                    intent.putExtra("pk2",adaptation.getSem());
                    startActivityForResult(intent, ADAPT_UPDATE_RC);
                }

            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(adaptRecycler);
    }

    public void openAdaptEditActivity(View view) {
        Intent intent = new Intent(this, EditAdaptActivity.class);
        startActivityForResult(intent, ADAPT_EDIT_RC);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADAPT_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final int adaptCourse = data.getIntExtra("adaptCourse",1);
                final int adaptSem = data.getIntExtra("adaptSem",1);
                final int adaptIsbn = data.getIntExtra("adaptIsbn", 1);
                Book_Adaptation adaptation = new Book_Adaptation(adaptCourse,adaptSem,adaptIsbn);
                adaptViewModel.insertAdapt(adaptation);

            }


            Toast.makeText(this, "Adaptation Saved", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == ADAPT_UPDATE_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final int adaptCourse = data.getIntExtra("adaptCourse",1);
                final int adaptSem = data.getIntExtra("adaptSem",1);
                final int adaptIsbn = data.getIntExtra("adaptIsbn", 1);
                final int pk1=data.getIntExtra("pk1",9999);
                final int pk2=data.getIntExtra("pk2",9999);
                adaptViewModel.updateAdapt(adaptCourse,adaptSem,adaptIsbn,pk1,pk2);

            }


            Toast.makeText(this, "Adaptation Saved", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Adaptation Cancelled", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }
}
