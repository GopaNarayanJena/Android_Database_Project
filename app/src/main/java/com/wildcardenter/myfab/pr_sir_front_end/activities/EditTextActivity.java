package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wildcardenter.myfab.pr_sir_front_end.R;

public class EditTextActivity extends AppCompatActivity {
    private EditText editIsbn,editTitle,editPublisher,editAuthor;
    int pk1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            pk1=bundle.getInt("pk1");
        }
        editIsbn=findViewById(R.id.Edit_Text_Book_isbn);
        editTitle=findViewById(R.id.Edit_book_title);
        editPublisher=findViewById(R.id.Edit_book_publisher);
        editAuthor=findViewById(R.id.Edit_book_author);
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
            if (TextUtils.isEmpty(editIsbn.getText()) || TextUtils.isEmpty(editTitle.getText())
                    ||TextUtils.isEmpty(editPublisher.getText())||editIsbn.getText().toString().trim().contains(" ")
            ||TextUtils.isEmpty(editAuthor.getText())) {
                setResult(RESULT_CANCELED, intent);
                finish();
            } else {
                String title = editTitle.getText().toString();
                String publisher=editPublisher.getText().toString();
                String author=editAuthor.getText().toString();
                int isbn=Integer.parseInt(editIsbn.getText().toString());
                intent.putExtra("isbn",isbn );
                intent.putExtra("title", title);
                intent.putExtra("publisher",publisher);
                intent.putExtra("author",author);
                intent.putExtra("pk1",pk1);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
