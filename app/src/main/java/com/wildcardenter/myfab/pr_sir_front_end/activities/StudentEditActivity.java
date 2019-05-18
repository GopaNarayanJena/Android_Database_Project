package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wildcardenter.myfab.pr_sir_front_end.R;

public class StudentEditActivity extends AppCompatActivity {
    EditText editName,editRegNo,editMajor,editBdate;
    String reno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            reno=bundle.getString("pk");
        }
        editName=findViewById(R.id.Edit_Student_info_name);
        editRegNo=findViewById(R.id.Edit_Student_info_regno);
        editMajor=findViewById(R.id.Edit_Student_info_Major);
        editBdate=findViewById(R.id.Edit_Student_info_dob);
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
            if (TextUtils.isEmpty(editName.getText()) || TextUtils.isEmpty(editRegNo.getText())
                    ||TextUtils.isEmpty(editMajor.getText())||TextUtils.isEmpty(editBdate.getText())||editBdate.getText().toString().contains(" ")) {
                setResult(RESULT_CANCELED, intent);
                finish();
            } else {
                String name = editName.getText().toString();
                String regNo = editRegNo.getText().toString();
                String major=editMajor.getText().toString();
                int bdate=Integer.parseInt(editBdate.getText().toString());
                intent.putExtra("name",name );
                intent.putExtra("regNo", regNo);
                intent.putExtra("major",major );
                intent.putExtra("bdate",bdate);
                intent.putExtra("prikey",reno);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
