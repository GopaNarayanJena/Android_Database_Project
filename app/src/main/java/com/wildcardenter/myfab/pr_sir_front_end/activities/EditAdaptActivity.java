package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wildcardenter.myfab.pr_sir_front_end.R;

public class EditAdaptActivity extends AppCompatActivity {
    private EditText editAdaptCourse,editAdaptSem,editAdaptBookIsbn;
    int pk1,pk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_adapt);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            pk1=bundle.getInt("pk1");
            pk2=bundle.getInt("pk2");
        }
        editAdaptCourse=findViewById(R.id.Edit_Adapt_Course);
        editAdaptSem=findViewById(R.id.Edit_Adapt_Sem);
        editAdaptBookIsbn=findViewById(R.id.Edit_Adapt_BookIsbn);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(editAdaptCourse.getText()) || TextUtils.isEmpty(editAdaptSem.getText())
                    ||TextUtils.isEmpty(editAdaptBookIsbn.getText())||editAdaptCourse.getText().toString().trim().contains(" ")
                ||editAdaptSem.getText().toString().trim().contains(" ")||editAdaptBookIsbn.getText().toString().trim().contains(" ")) {
                setResult(RESULT_CANCELED, intent);
                finish();
            } else {
                int adaptSem=Integer.parseInt(editAdaptSem.getText().toString().trim());
                int adaptIsbn=Integer.parseInt(editAdaptBookIsbn.getText().toString().trim());
                int adaptCourse=Integer.parseInt(editAdaptCourse.getText().toString());
                intent.putExtra("adaptCourse",adaptCourse );
                intent.putExtra("adaptSem", adaptSem);
                intent.putExtra("adaptIsbn",adaptIsbn);
                intent.putExtra("pk1",pk1);
                intent.putExtra("pk2",pk2);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
