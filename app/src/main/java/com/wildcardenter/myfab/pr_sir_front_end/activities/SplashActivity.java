package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.wildcardenter.myfab.pr_sir_front_end.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LottieAnimationView animationView=findViewById(R.id.splashAnim);
        animationView.playAnimation();
        new Handler().postDelayed(()->{
            startActivity(new Intent(SplashActivity.this,LandingActivity.class));
            animationView.pauseAnimation();
            finish();
        },4000);
        TextView textView=findViewById(R.id.sp_text);
        textView.setTypeface(Typeface.createFromAsset(this.getAssets(),"DeathRattle BB.ttf"));
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.alerter_slide_in_from_left);
        textView.setAnimation(animation);
        textView.animate();
    }
}
