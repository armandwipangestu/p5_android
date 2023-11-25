package com.tugas.uas_rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    // Variables
    ImageView logo;
    TextView title, tagline;
    LinearLayout registrationStatusLayout;
    TextView registrationStatusText;
    TextInputLayout _REG_NAMA_LENGKAP_, _REG_USERNAME_, _REG_EMAIL_, _REG_NO_TELEPON_, _REG_PASSWORD_;
    Button signUpButton, signUpToSignInButton;

    FirebaseDatabase database;
    DatabaseReference reference;

    private static final int _TIME_INTERVAL = 2000; // time for between 2x when press back (in miliseconds)
    private long backPressedTime = 0; // initialize back press

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Hooks all xml elements in activity_sign_up.xml
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        tagline = findViewById(R.id.tagline);
        registrationStatusLayout = findViewById(R.id.registrationStatusLayout);
        registrationStatusText = findViewById(R.id.registrationStatusText);
        _REG_NAMA_LENGKAP_ = findViewById(R.id.namaLengkap);
        _REG_USERNAME_ = findViewById(R.id.username);
        _REG_EMAIL_ = findViewById(R.id.email);
        _REG_NO_TELEPON_ = findViewById(R.id.noTelepon);
        _REG_PASSWORD_ = findViewById(R.id.password);
        signUpButton = findViewById(R.id.signUpButton);
        signUpToSignInButton = findViewById(R.id.signUpToSignInButton);

        // Connect to Database or Node Firebase
        database = FirebaseDatabase.getInstance("https://uas---rental-mobil-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // Set reference for store the data user sign up
        reference = database.getReference("users");
    }

    private Boolean validateNamaLengkap() {
        String value = _REG_NAMA_LENGKAP_.getEditText().getText().toString();

        if (value.isEmpty()) {
            _REG_NAMA_LENGKAP_.setError("Nama Lengkap tidak boleh kosong!");
            return false;
        } else {
            _REG_NAMA_LENGKAP_.setError(null);
            _REG_NAMA_LENGKAP_.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String value = _REG_USERNAME_.getEditText().getText().toString();
        //String noWhiteSpace = "(?=\\s+$)";
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (value.isEmpty()) {
            _REG_USERNAME_.setError("Username tidak boleh kosong!");
            return false;
        } else if (value.length() < 4) {
            _REG_USERNAME_.setError("Username terlalu pendek, minimal 4 karakter!");
            return false;
        } else if (value.length() > 15) {
            _REG_USERNAME_.setError("Username terlalu panjang, maksimal 15 karakter!");
            return false;
        } else if (!value.matches(noWhiteSpace)) {
            _REG_USERNAME_.setError("Username tidak boleh mengandung spasi!");
            return false;
        } else {
            _REG_USERNAME_.setError(null);
            _REG_USERNAME_.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String value = _REG_EMAIL_.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()) {
            _REG_EMAIL_.setError("Email tidak boleh kosong!");
            return false;
        } else if (!value.matches(emailPattern)) {
            _REG_EMAIL_.setError("Masukan format email yang valid!");
            return false;
        } else {
            _REG_EMAIL_.setError(null);
            _REG_EMAIL_.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateNoTelepon() {
        String value = _REG_NO_TELEPON_.getEditText().getText().toString();

        if (value.isEmpty()) {
            _REG_NO_TELEPON_.setError("No Telepon tidak boleh kosong!");
            return false;
        } else {
            _REG_NO_TELEPON_.setError(null);
            _REG_NO_TELEPON_.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String value = _REG_PASSWORD_.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +             //at least 1 digit
                //"(?=.*[a-z])" +             //at least 1 lower case letter
                //"(?=.*[A-Z])" +             //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +            //any letter
                //"(?=.*[@#$%^&+=])" +        //at least 1 special character
                //"(?=\\S+$)" +               //no white spaces
                ".{4,}" +                     //at least 4 characters
                "$";

        if (value.isEmpty()) {
            _REG_PASSWORD_.setError("Password tidak boleh kosong!");
            return false;
        } else if (!value.matches(passwordVal)) {
            _REG_PASSWORD_.setError("Password terlalu pendek, minimal 4 karakter!");
            return false;
        } else {
            _REG_PASSWORD_.setError(null);
            _REG_PASSWORD_.setErrorEnabled(false);
            return true;
        }
    }

    public void signUpButtonClick(View v) {
        if (!validateNamaLengkap() | !validateUsername() | !validateEmail() | !validateNoTelepon() | !validatePassword()) {
            return;
        }

        // Get all values in String
        String namaLengkap = _REG_NAMA_LENGKAP_.getEditText().getText().toString();
        String username = _REG_USERNAME_.getEditText().getText().toString();
        String email = _REG_EMAIL_.getEditText().getText().toString();
        String noTelepon = _REG_NO_TELEPON_.getEditText().getText().toString();
        String password = _REG_PASSWORD_.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(namaLengkap, username, email, noTelepon, password);
        reference.child(username).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Pendaftaran berhasil, silahkan masuk!", Toast.LENGTH_SHORT).show();
                    registrationStatusLayout.setBackgroundColor(Color.parseColor("#22c55e"));
                    registrationStatusText.setText("Pendaftaran berhasil, silahkan masuk!");
                } else {
                    Toast.makeText(getBaseContext(), "Pendaftaran gagal!", Toast.LENGTH_SHORT).show();
                    registrationStatusLayout.setBackgroundColor(Color.parseColor("#ef4444"));
                    registrationStatusText.setText("Pendaftaran gagal!");
                }

                registrationStatusLayout.setVisibility(View.VISIBLE);

                _REG_NAMA_LENGKAP_.getEditText().setText("");
                _REG_USERNAME_.getEditText().setText("");
                _REG_EMAIL_.getEditText().setText("");
                _REG_NO_TELEPON_.getEditText().setText("");
                _REG_PASSWORD_.getEditText().setText("");
            }
        });
    }

    public void signUpToSignInButtonClick(View v) {
        Intent intent = new Intent(SignUp.this, SignIn.class);

        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View,String>(logo, "logo_image");
        pairs[1] = new Pair<View,String>(title, "text_title");
        pairs[2] = new Pair<View,String>(tagline, "text_tagline");
        pairs[3] = new Pair<View,String>(_REG_USERNAME_, "input_username");
        pairs[4] = new Pair<View,String>(_REG_PASSWORD_, "input_password");
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