package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wildcardenter.myfab.pr_sir_front_end.R;

public class CourseEditActivity extends AppCompatActivity {
    private EditText editCourse,editCname,editCdept;
    private int pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            pk=bundle.getInt("pk");
        }
        editCourse=findViewById(R.id.Edit_Course);
        editCdept=findViewById(R.id.Edit_Course_Dept);
        editCname=findViewById(R.id.Edit_Course_Name);
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
            if (TextUtils.isEmpty(editCourse.getText()) || TextUtils.isEmpty(editCdept.getText())
                    ||TextUtils.isEmpty(editCname.getText())||editCourse.getText().toString().trim().contains(" ")) {
                setResult(RESULT_CANCELED, intent);
                finish();
            } else {
                String cname = editCname.getText().toString();
                String cdept=editCdept.getText().toString();
                int course=Integer.parseInt(editCourse.getText().toString());
                intent.putExtra("Cname",cname );
                intent.putExtra("Cdept", cdept);
                intent.putExtra("Ccourse",course);
                intent.putExtra("pkk",pk);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
