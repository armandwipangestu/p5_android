package com.tugas.uas_rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    // Variables
    ImageView logo;
    TextView title, tagline;

    FirebaseDatabase database;
    DatabaseReference reference;

    TextInputLayout username, password;
    Button signInToSignUpButton;
    RelativeLayout signInButton;

    private static final int _TIME_INTERVAL = 2000; // time for between 2x when press back (in miliseconds)
    private long backPressedTime = 0; // initialize back press

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Hooks
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        tagline = findViewById(R.id.tagline);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInButton);
        signInToSignUpButton = findViewById(R.id.signInToSignUpButton);

        // Connect to Database or Node Firebase
        database = FirebaseDatabase.getInstance("https://uas---rental-mobil-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // Set reference for store the data user sign up
        reference = database.getReference("users");
    }

    public void signInToSignUpButtonClick(View v) {
        Intent intent = new Intent(SignIn.this, SignUp.class);

        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View,String>(logo, "logo_image");
        pairs[1] = new Pair<View,String>(title, "text_title");
        pairs[2] = new Pair<View,String>(tagline, "text_tagline");
        pairs[3] = new Pair<View,String>(username, "input_username");
        pairs[4] = new Pair<View,String>(password, "input_password");
        pairs[5] = new Pair<View,String>(signInButton, "action_button");
        pairs[6] = new Pair<View,String>(signInToSignUpButton, "change_view");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignIn.this, pairs);
        startActivity(intent, options.toBundle());
        finish();
    }

    private boolean validateUsername() {
        String value = username.getEditText().getText().toString();

        if (value.isEmpty()) {
            username.setError("Username tidak boleh kosong!");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String value = password.getEditText().getText().toString();

        if (value.isEmpty()) {
            password.setError("Username tidak boleh kosong!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void signInButtonClick(View v) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            executeSignIn();
        }
    }

    private void executeSignIn() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if data exist by username input
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);

                    // Get password user from DB
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    // Check if password entered by user equal (=) to password from db
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        // If password same with data from db
                        password.setError(null);
                        password.setErrorEnabled(false);

                        // Get all data from DB
                        String namaLengkapFromDB = snapshot.child(userEnteredUsername).child("namaLengkap").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String noTeleponFromDB = snapshot.child(userEnteredUsername).child("noTelepon").getValue(String.class);

                        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("namaLengkap", namaLengkapFromDB);
                        editor.putString("username", usernameFromDB);
                        editor.putString("email", emailFromDB);
                        editor.putString("noTelepon", noTeleponFromDB);
                        editor.putString("password", passwordFromDB);
                        editor.apply();

                        // Prepare Dashboard View
                        Intent intent = new Intent(SignIn.this, ListCar.class);

                        // Send all data from DB to Dashboard
                        //intent.putExtra("namaLengkap", namaLengkapFromDB);
                        //intent.putExtra("username", usernameFromDB);
                        //intent.putExtra("email", emailFromDB);
                        //intent.putExtra("noTelepon", noTeleponFromDB);
                        //intent.putExtra("password", passwordFromDB);

                        // Change to Dashboard View
                        startActivity(intent);
                        finish();
                    } else {
                        password.setError("Password yang anda masukan salah!");
                        password.requestFocus();
                    }
                } else {
                    username.setError("Username yang anda masukan tidak ditemukan!");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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