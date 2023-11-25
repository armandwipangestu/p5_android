package com.latihan.rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

//    Variables
    LinearLayout registrationStatusLayout;
    TextView registrationStatusText;
    TextInputLayout regNamaLengkap, regUsername, regEmail, regNoTelepon, regPassword;
    Button regBtn, daftarToLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

//        Hooks all xml elements in activity_sign_up.xml
        registrationStatusLayout = findViewById(R.id.registrationStatusLayout);
        registrationStatusText = findViewById(R.id.registrationStatusText);
        regNamaLengkap = findViewById(R.id.nama_lengkap);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regNoTelepon = findViewById(R.id.no_telepon);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.reg_btn);
        daftarToLogin = findViewById(R.id.daftarToLogin);

        rootNode = FirebaseDatabase.getInstance("https://rental-mobil-bde39-default-rtdb.asia-southeast1.firebasedatabase.app/");
        reference = rootNode.getReference("users");

        daftarToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateName() {
        String value = regNamaLengkap.getEditText().getText().toString();

        if (value.isEmpty()) {
            regNamaLengkap.setError("Kolom tidak boleh kosong");
            return false;
        } else {
            regNamaLengkap.setError(null);
            regNamaLengkap.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String value = regUsername.getEditText().getText().toString();
//        String noWhiteSpace = "(?=\\s+$)";
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (value.isEmpty()) {
            regUsername.setError("Kolom tidak boleh kosong");
            return false;
        } else if (value.length() >= 15) {
            regUsername.setError("Username terlalu panjang");
            return false;
        } else if (!value.matches(noWhiteSpace)) {
            regUsername.setError("Username tidak boleh mengandung spasi");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String value = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()) {
            regEmail.setError("Kolom tidak boleh kosong");
            return false;
        } else if (!value.matches(emailPattern)) {
            regEmail.setError("Format email tidak valid");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateNoTelepon() {
        String value = regNoTelepon.getEditText().getText().toString();

        if (value.isEmpty()) {
            regNoTelepon.setError("Kolom tidak boleh kosong");
            return false;
        } else {
            regNoTelepon.setError(null);
            regNoTelepon.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String value = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
            //"(?=.*[0-9])" +             //at least 1 digit
            //"(?=.*[a-z])" +             //at least 1 lower case letter
            //"(?=.*[A-Z])" +             //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +          //any letter
            //"(?=.*[@#$%^&+=])" +        //at least 1 special character
            //"(?=\\S+$)" +               //no white spaces
            ".{4,}" +                   //at least 4 characters
            "$";

        if (value.isEmpty()) {
            regPassword.setError("Kolom tidak boleh kosong");
            return false;
        } else if (!value.matches(passwordVal)) {
            regPassword.setError("Password terlalu pendek, minimal 4 karakter!");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


//    Save data in FireBase on button click
    public void registerUser(View view) {

        if (!validateName() | !validateUsername() | !validateEmail() | !validateNoTelepon() | !validatePassword()) {
            return;
        }

//        Get all the values in String
        String namaLengkap = regNamaLengkap.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String noTelepon = regNoTelepon.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        // Generate a unique key for each user
        String userId = username + '_' + reference.push().getKey();

        UserHelperClass helperClass = new UserHelperClass(namaLengkap, username, email, noTelepon, password);
        reference.child(userId).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Pendaftaran berhasil, silahkan login!", Toast.LENGTH_SHORT).show();
                    registrationStatusLayout.setBackgroundColor(Color.parseColor("#22c55e"));
                    registrationStatusText.setText("Pendaftaran berhasil!");
                } else {
                    Toast.makeText(getApplicationContext(), "Pendaftaran gagal!", Toast.LENGTH_SHORT).show();
                    registrationStatusLayout.setBackgroundColor(Color.parseColor("#ef4444"));
                    registrationStatusText.setText("Pendaftaran gagal!");
                }

                registrationStatusLayout.setVisibility(View.VISIBLE);

                regNamaLengkap.getEditText().setText("");
                regUsername.getEditText().setText("");
                regEmail.getEditText().setText("");
                regNoTelepon.getEditText().setText("");
                regPassword.getEditText().setText("");
            }
        });
    }
}