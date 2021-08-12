package com.example.malikfragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textView;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button logout_btn = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
                textView.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout_btn.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}