package com.wildcardenter.myfab.pr_sir_front_end.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.TextViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.BookByCsDeptAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.models.TextByCs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookByCsCourseActivity extends AppCompatActivity {

    private RecyclerView bookByCsRecycler;
    private TextViewModel viewModel;
    private BookByCsDeptAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_by_cs_course);
        bookByCsRecycler=findViewById(R.id.BookByCsRecycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        bookByCsRecycler.setLayoutManager(manager);
        adapter=new BookByCsDeptAdapter(this);
        bookByCsRecycler.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(TextViewModel.class);
        viewModel.getAllTextByCs().observe(this, texts -> adapter.setAllTextByCs(texts));

    }
}
