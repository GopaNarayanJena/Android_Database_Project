package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.tapadoo.alerter.Alerter;
import com.wildcardenter.myfab.pr_sir_front_end.R;

public class LandingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.Drawer_Opened, R.string.Drawer_Closed);
        toggle.syncState();
        getSupportActionBar().setTitle("Show All Queries");

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.showAllStudentAct) {
            startActivity(new Intent(this,MainActivity.class));


        } else if (id == R.id.showAllCourseAct) {
            startActivity(new Intent(this,ShowCourseActivity.class));
        }
        else if (id==R.id.showAllTextAct){
            startActivity(new Intent(this,ShowTextActivity.class));
        }
        else if (id==R.id.showAllEnrollAct){
            startActivity(new Intent(this,ShowEnrollActivity.class));
        }
        else if (id==R.id.showAllBookAdpAct){
            startActivity(new Intent(this,ShowAdaptionActivity.class));
        }
        else if (id==R.id.showByDept){
            startActivity(new Intent(this,DeptByPublisherActivity.class));
        }
        else if (id==R.id.showAllBookByCs){
            startActivity(new Intent(this,BookByCsCourseActivity.class));
        }
        else if (id==R.id.showAbout){
            startActivity(new Intent(this,AboutUsActivity.class));
        }
        else if (id==R.id.exit){
            Alerter.create(this)
                    .setDismissable(true)
                    .setTitle("Exit?")
                    .setText("Are You Sure You Want To Exit The Application?")
                    .addButton("Confirm",R.style.AlertButton,v-> finish())
                    .addButton("Cancel",R.style.AlertButton,v->Alerter.hide())
                    .setIcon(R.drawable.alerter_ic_notifications)
                    .setBackgroundColorInt(Color.parseColor("#FCEF5350"))
                    .show();

        }


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
