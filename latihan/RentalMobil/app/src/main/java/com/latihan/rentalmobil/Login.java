package com.latihan.rentalmobil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button callSignUp, loginBtn;
    ImageView logo;
    TextView title, tagline;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

//        Hooks
        callSignUp = findViewById(R.id.daftar);
        loginBtn = findViewById(R.id.login_button);
        logo = findViewById(R.id.logo_image);
        title = findViewById(R.id.title);
        tagline = findViewById(R.id.tagline_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(title, "text_title");
                pairs[2] = new Pair<View,String>(tagline, "text_tagline");
                pairs[3] = new Pair<View,String>(username, "username_transition");
                pairs[4] = new Pair<View,String>(password, "password_transition");
                pairs[5] = new Pair<View,String>(loginBtn, "action_transition");
                pairs[6] = new Pair<View,String>(callSignUp, "login_signup_transition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}