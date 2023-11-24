package com.latihan.rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

//    Variables
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
        regNamaLengkap = findViewById(R.id.nama_lengkap);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regNoTelepon = findViewById(R.id.no_telepon);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.reg_btn);
        daftarToLogin = findViewById(R.id.daftarToLogin);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance("https://rental-mobil-bde39-default-rtdb.asia-southeast1.firebasedatabase.app/");
                reference = rootNode.getReference("users");

                // Generate a unique key for each user
                String userId = reference.push().getKey();

//                Get All the Values
                String nama_lengkap = regNamaLengkap.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String no_telepon = regNoTelepon.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(nama_lengkap, username, email, no_telepon, password);

                reference.child(userId).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Data berhasil tersimpan ke Firebase!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal menyimpan data ke Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        daftarToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
}