package com.wildcardenter.myfab.pr_sir_front_end.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;
import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.CourseViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.CourseAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.helpers.SwipeToDeleteCallback;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowCourseActivity extends AppCompatActivity {

    public static final int COURSE_UPDATE_RC = 3410;
    private static final int COURSE_EDIT_RC = 333;
    private CourseViewModel courseViewModel;
    private CourseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_course);
        RecyclerView showCourseRecycler = findViewById(R.id.show_course_recycler);
        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        showCourseRecycler.setLayoutManager(manager);
        adapter = new CourseAdapter(this);
        showCourseRecycler.setAdapter(adapter);
        courseViewModel.getAllCourseList().observe(this, list -> {
            adapter.setCourseList(list);
            adapter.notifyDataSetChanged();
        });

        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Alerter.create(ShowCourseActivity.this)
                            .enableInfiniteDuration(true)
                            .setDismissable(false)
                            .setBackgroundColorInt(Color.parseColor("#EF5350"))
                            .setTitle("Delete Course Details?")
                            .setText("Are You Sure You Want To Delete Course Detail?")
                            .addButton("Confirm", R.style.AlertButton, v -> {
                                courseViewModel.deleteCourse(adapter.getItemAt(viewHolder.getAdapterPosition()));
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                                Toast.makeText(ShowCourseActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Alerter.hide();
                            })
                            .addButton("Cancel", R.style.AlertButton, v -> {
                                Alerter.hide();
                                adapter.notifyDataSetChanged();
                            })
                            .setIcon(R.drawable.ic_delete_sweep_black_24dp)
                            .show();

                } else {
                    Intent intent = new Intent(ShowCourseActivity.this, CourseEditActivity.class);
                    Course course = adapter.getItemAt(viewHolder.getAdapterPosition());
                    intent.putExtra("pk", course.getCourse());
                    startActivityForResult(intent, COURSE_UPDATE_RC);
                }
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(showCourseRecycler);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COURSE_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String cname = data.getStringExtra("Cname");
                final String cDept = data.getStringExtra("Cdept");
                final int course = data.getIntExtra("Ccourse", 1);
                Course course1 = new Course(course, cname, cDept);
                courseViewModel.insertCourse(course1);

            }


            Toast.makeText(this, "Course Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == COURSE_UPDATE_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String cname = data.getStringExtra("Cname");
                final String cDept = data.getStringExtra("Cdept");
                final int course = data.getIntExtra("Ccourse", 9999);
                final int pk = data.getIntExtra("pkk", 9999);
                courseViewModel.updateCourse(String.valueOf(course), cname, cDept, String.valueOf(pk));

            }

            Toast.makeText(this, "Course Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Course Cancelled", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }

    public void openCourseEditActivity(View view) {
        Intent intent = new Intent(this, CourseEditActivity.class);
        startActivityForResult(intent, COURSE_EDIT_RC);
    }
}
