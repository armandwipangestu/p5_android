package com.latihan.rentalmobil;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, UserProfile.class);
//                startActivity(intent);
//            }
//        });

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
                finish();
            }
        });
    }

    private Boolean validateUsername() {
        String value = username.getEditText().getText().toString();

        if (value.isEmpty()) {
            username.setError("Kolom tidak boleh kosong");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String value = password.getEditText().getText().toString();

        if (value.isEmpty()) {
            password.setError("Kolom tidak boleh kosong");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://rental-mobil-bde39-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String namaLengkapFromDB = snapshot.child(userEnteredUsername).child("nama_lengkap").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String noTeleponFromDB = snapshot.child(userEnteredUsername).child("no_telepon").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        intent.putExtra("namaLengkap", namaLengkapFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("noTelepon", noTeleponFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                        finish();
                    } else {
                        password.setError("Password salah");
                        password.requestFocus();
                    }
                } else {
                    username.setError("Username tidak ditemukan");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static final int TIME_INTERVAL = 2000; // Waktu interval antara dua kali tekan back (dalam milidetik)
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (backPressedTime + TIME_INTERVAL > System.currentTimeMillis()) {
            // Jika selisih waktu antara dua kali tekan back cukup kecil, maka keluar dari aplikasi
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Tekan kembali sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}