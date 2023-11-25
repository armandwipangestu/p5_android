package com.tugas.uas_rentalmobil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Profile extends BaseActivity {
    @Override
    int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.myProfile;
    }
}