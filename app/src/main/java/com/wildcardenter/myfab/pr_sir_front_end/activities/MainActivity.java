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
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.StudentViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.StudentAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.helpers.SwipeToDeleteCallback;
import com.wildcardenter.myfab.pr_sir_front_end.models.Student;

public class MainActivity extends AppCompatActivity {
    private static final int STUDENT_EDIT_RC =313 ;
    public static final int STUDENT_UPDATE_RC=3500;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.StudentRecycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter=new StudentAdapter(this);
        recyclerView.setAdapter(adapter);
        viewModel= ViewModelProviders.of(this).get(StudentViewModel.class);
        viewModel.getAllStudentList().observe(this,list->{
            adapter.setlist(list);
            adapter.notifyDataSetChanged();
        });

        SwipeToDeleteCallback callback=new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==ItemTouchHelper.LEFT) {
                    Alerter.create(MainActivity.this)
                            .enableInfiniteDuration(true)
                            .setDismissable(false)
                            .setBackgroundColorInt(Color.parseColor("#EF5350"))
                            .setTitle("Delete Student Details?")
                            .setText("Are You Sure You Want To Delete Student Detail?")
                            .addButton("Confirm",R.style.AlertButton,v->{
                                viewModel.deleteStudent(adapter.getItemAt(viewHolder.getAdapterPosition()));
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Alerter.hide();
                            })
                            .addButton("Cancel",R.style.AlertButton,v->{
                                Alerter.hide();
                                adapter.notifyDataSetChanged();
                            })
                            .setIcon(R.drawable.ic_delete_sweep_black_24dp)
                            .show();

                }
                else{
                    Intent intent=new Intent(MainActivity.this,StudentEditActivity.class);
                    Student student=adapter.getItemAt(viewHolder.getAdapterPosition());
                    intent.putExtra("pk",student.getRegno());
                    startActivityForResult(intent,STUDENT_UPDATE_RC);
                }
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
    }

    public void openStudentEdit(View view) {
        Intent intent = new Intent(this, StudentEditActivity.class);
        startActivityForResult(intent,STUDENT_EDIT_RC );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STUDENT_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String name = data.getStringExtra("name");
                final String regNo = data.getStringExtra("regNo");
                final int bdate = data.getIntExtra("bdate",00000000);
                final String major=data.getStringExtra("major");
                Student model = new Student(regNo,name,major,bdate);
                viewModel.insertStudent(model);
            }


            Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == STUDENT_UPDATE_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String name = data.getStringExtra("name");
                final String regNo = data.getStringExtra("regNo");
                final int bdate = data.getIntExtra("bdate",00000000);
                final String major=data.getStringExtra("major");
                final String pk=data.getStringExtra("prikey");
                viewModel.updateStudent(regNo,name,major, String.valueOf(bdate),pk);
            }


            Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Student Cancelled", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }
}
