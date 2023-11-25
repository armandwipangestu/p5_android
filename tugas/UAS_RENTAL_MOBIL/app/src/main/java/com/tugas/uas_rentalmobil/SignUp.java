package com.tugas.uas_rentalmobil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    // Variables
    ImageView logo;
    TextView title, tagline;
    TextInputLayout username, password;
    Button signUpButton, signUpToSignInButton;
    private static final int _TIME_INTERVAL = 2000; // time for between 2x when press back (in miliseconds)
    private long backPressedTime = 0; // initialize back press

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hooks
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        tagline = findViewById(R.id.tagline);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signUpButton = findViewById(R.id.signUpButton);
        signUpToSignInButton = findViewById(R.id.signUpToSignInButton);
    }

    public void signUpToSignInButtonClick(View v) {
        Intent intent = new Intent(SignUp.this, SignIn.class);

        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View,String>(logo, "logo_image");
        pairs[1] = new Pair<View,String>(title, "text_title");
        pairs[2] = new Pair<View,String>(tagline, "text_tagline");
        pairs[3] = new Pair<View,String>(username, "input_username");
        pairs[4] = new Pair<View,String>(password, "input_password");
        pairs[5] = new Pair<View,String>(signUpButton, "action_button");
        pairs[6] = new Pair<View,String>(signUpToSignInButton, "change_view");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intent, options.toBundle());
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + _TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}