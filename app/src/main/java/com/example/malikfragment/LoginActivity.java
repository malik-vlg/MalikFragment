package com.example.malikfragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email_login;
    private EditText password_login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        Button btn_login = findViewById(R.id.btn_login);
        TextView register_txt = findViewById(R.id.register_txt);

        mAuth = FirebaseAuth.getInstance();

        register_txt.setOnClickListener(v -> {
            Intent intent = new Intent (LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(v -> {
            if (email_login.getText().toString().isEmpty() || password_login.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }else{
                mAuth.signInWithEmailAndPassword(email_login.getText().toString(), password_login.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "You have some errors", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}