package com.dashboardnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener navigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment f = null;

//            switch (item.getItemId()) {
//                case R.id.menu_home:
//                    f = new FragmentHome();
//                    break;
//                case R.id.menu_cart:
//                    f = new FragmentCart();
//                    break;
//                case R.id.menu_notification:
//                    f = new FragmentNotification();
//                    break;
//                case R.id.menu_profile:
//                    f = new FragmentProfile();
//                    break;
//            }

            if(item.getItemId() == R.id.menu_home) {
                f = new FragmentHome();
            }

            if(item.getItemId() == R.id.menu_notification) {
                f = new FragmentNotification();
            }

            if(item.getItemId() == R.id.menu_cart) {
                f = new FragmentCart();
            }

            if(item.getItemId() == R.id.menu_profile) {
                f = new FragmentProfile();
            }

            assert f != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, f).commit();
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.btn_navigasi_menu);
        bottomNavigationView.setOnItemSelectedListener(navigation);
    }
}
