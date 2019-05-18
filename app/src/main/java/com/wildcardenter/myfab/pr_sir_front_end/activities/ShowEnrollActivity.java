package com.wildcardenter.myfab.pr_sir_front_end.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;
import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.EnrollViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.EnrollAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.helpers.SwipeToDeleteCallback;
import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowEnrollActivity extends AppCompatActivity {

    private static final int ENROLL_EDIT_RC = 1001;
    private static final int ENROLL_UPDATE_RC = 3600;
    private EnrollAdapter adapter;
    private EnrollViewModel enrollViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_enroll);
        RecyclerView enrollRecycler = findViewById(R.id.show_enroll_recycler);
        enrollViewModel = ViewModelProviders.of(this).get(EnrollViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        enrollRecycler.setLayoutManager(manager);
        adapter = new EnrollAdapter(this);
        enrollRecycler.setAdapter(adapter);
        enrollViewModel.getAllEnrollList().observe(this, enrolls -> adapter.setEnrolls(enrolls));

        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Alerter.create(ShowEnrollActivity.this)
                            .enableInfiniteDuration(true)
                            .setDismissable(false)
                            .setBackgroundColorInt(Color.parseColor("#EF5350"))
                            .setTitle("Delete Enroll Details?")
                            .setText("Are You Sure You Want To Delete Enroll Detail?")
                            .addButton("Confirm", R.style.AlertButton, v -> {
                                enrollViewModel.deleteEnroll(adapter.getItemAt(viewHolder.getAdapterPosition()));
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                                Toast.makeText(ShowEnrollActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Alerter.hide();
                            })
                            .addButton("Cancel", R.style.AlertButton, v -> {
                                Alerter.hide();
                                adapter.notifyDataSetChanged();
                            })
                            .setIcon(R.drawable.ic_delete_sweep_black_24dp)
                            .show();
                } else {
                    Intent intent = new Intent(ShowEnrollActivity.this, EditEnrollActivity.class);
                    Enroll enroll = adapter.getItemAt(viewHolder.getAdapterPosition());
                    intent.putExtra("pk1", enroll.getRegno());
                    intent.putExtra("pk2", enroll.getCourse());
                    intent.putExtra("pk3", enroll.getSem());
                    startActivityForResult(intent, ENROLL_UPDATE_RC);
                }
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(enrollRecycler);
    }

    public void openEnrollEditActivity(View view) {
        Intent intent = new Intent(this, EditEnrollActivity.class);
        startActivityForResult(intent, ENROLL_EDIT_RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ENROLL_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String enrollRegNo = data.getStringExtra("enrollRegNo");
                final int enrollCourse = data.getIntExtra("enrollCourse", 1);
                final int enrollSem = data.getIntExtra("enrollSem", 1);
                final int enrollMarks = data.getIntExtra("enrollMarks", 1);
                Enroll enroll = new Enroll(enrollRegNo, enrollCourse, enrollSem, enrollMarks);
                enrollViewModel.insrtEnroll(enroll);


            }


            Toast.makeText(this, "Enroll Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == ENROLL_UPDATE_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String enrollRegNo = data.getStringExtra("enrollRegNo");
                final int enrollCourse = data.getIntExtra("enrollCourse", 1);
                final int enrollSem = data.getIntExtra("enrollSem", 1);
                final int enrollMarks = data.getIntExtra("enrollMarks", 1);
                final String pk1 = data.getStringExtra("pkk1");
                final int pk2 = data.getIntExtra("pkk2", 1);
                final int pk3 = data.getIntExtra("pkk3", 1);
                enrollViewModel.updateEnroll(enrollRegNo, String.valueOf(enrollCourse), String.valueOf(enrollSem),
                        String.valueOf(enrollMarks), pk1, String.valueOf(pk2), String.valueOf(pk3));
            }


            Toast.makeText(this, "Enroll Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enroll Cancelled", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }
}
