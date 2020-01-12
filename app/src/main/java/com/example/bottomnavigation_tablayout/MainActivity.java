package com.example.bottomnavigation_tablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigation_tablayout.Fragment.MapFragment;
import com.example.bottomnavigation_tablayout.Fragment.PeopleFragment;
import com.example.bottomnavigation_tablayout.Fragment.PlayFragment;
import com.example.bottomnavigation_tablayout.Fragment.WorkFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        //將Fragment設定到FramLayout裡顯示  並預設Fragment位置在Play
        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,new PlayFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.nav_play:
                            selectedFragment = new PlayFragment();
                            break;
                        case R.id.nav_map:
                            selectedFragment = new MapFragment();
                            break;
                        case R.id.nav_people:
                            selectedFragment = new PeopleFragment();
                            break;
                        case R.id.nav_heart:
                            selectedFragment = null;
                            startActivity(new Intent(MainActivity.this, HeartActivity.class));
                            break;
                        case R.id.nav_work:
                            selectedFragment = new WorkFragment();
                            break;
                    }

                    if (selectedFragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,selectedFragment).commit();
                    }
                    return true;
                }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //從其他 Activity 返回時的焦點位置
        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,new PlayFragment()).commit();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }
}

