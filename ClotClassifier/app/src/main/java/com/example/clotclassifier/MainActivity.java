package com.example.clotclassifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    HomeFragment homeFragment = new HomeFragment();
    DataFragment dataFragment = new DataFragment();
    CommentsFragment commentsFragment = new CommentsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.nav);
        // Loading Default Fragment (Home Fragment)
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;

                    case R.id.send:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,dataFragment).commit();
                        return true;

                    case R.id.comment:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container,commentsFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}