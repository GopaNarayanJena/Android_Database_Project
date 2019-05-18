package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.versionedparcelable.VersionedParcel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.CourseViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.ByPublisherAdapter;

public class DeptByPublisherActivity extends AppCompatActivity {
    private CourseViewModel viewModel;
    private EditText fbPublisherEdit;
    private RecyclerView fbPublisherRecycler;
    private Button fbPublisherBtn;
    private ByPublisherAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_by_publisher);
        fbPublisherEdit=findViewById(R.id.FindByPublisherEdit);
        fbPublisherBtn=findViewById(R.id.FindByPublisherBtn);
        fbPublisherRecycler=findViewById(R.id.ByPublisherRecycler);
        LinearLayoutManager manager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        fbPublisherRecycler.setLayoutManager(manager);
        viewModel= ViewModelProviders.of(this).get(CourseViewModel.class);
        adapter=new ByPublisherAdapter(this);
        fbPublisherRecycler.setAdapter(adapter);
        fbPublisherBtn.setOnClickListener(v->{
            viewModel.getAllDeptByPublisher(fbPublisherEdit.getText().toString()).observe(DeptByPublisherActivity.this,
                    list->{
                        adapter.setAllDept(list);
                        adapter.notifyDataSetChanged();
                    });
            //adapter.notifyDataSetChanged();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
