package com.example.malikfragment;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;

    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference();


        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        Button btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(v -> {
            if (email_register.getText().toString().isEmpty() || password_register.getText().toString().isEmpty()){
                Toast.makeText(RegisterActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }else{
                mAuth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("email").setValue(email_register.getText().toString());
                                ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("password").setValue(password_register.getText().toString());

                                Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "You have some errors", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}