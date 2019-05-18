package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.wildcardenter.myfab.pr_sir_front_end.R;

public class AboutUsActivity extends AppCompatActivity {

    LinearLayout googlePlay,faceBook,github,twitter,linkedin,instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        googlePlay=findViewById(R.id.playstore);
        faceBook=findViewById(R.id.facebook);
        github=findViewById(R.id.github);
        twitter=findViewById(R.id.twitter);
        linkedin=findViewById(R.id.linkedin);
        instagram=findViewById(R.id.instagram);
        googlePlay.setOnClickListener(v->{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Mortaza+Corp.")));
        });
        faceBook.setOnClickListener(v->{
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/asif.mondal.9212")));
        });
        github.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/as786sarex")));
        });
        twitter.setOnClickListener(v->{
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/as786sarex")));
        });
        instagram.setOnClickListener(v->{
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/as786sarex/")));
        });
        linkedin.setOnClickListener(v->{
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com")));
        });


    }
}
