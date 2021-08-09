package com.example.malikfragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateNotesActivity extends AppCompatActivity {

    EditText title, description;
    Button updateNotes;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        updateNotes = findViewById(R.id.updateNote);

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        id =i.getStringExtra("id");

        updateNotes.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(title.getText().toString()) &&
            !TextUtils.isEmpty(description.getText().toString())){
                DatabaseClass db = new DatabaseClass(UpdateNotesActivity.this);
                db.updateNotes(title.getText().toString(), description.getText().toString(), id);

                Intent i1 = new Intent(UpdateNotesActivity.this, MainActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i1);
                finish();
            }else {
                Toast.makeText(UpdateNotesActivity.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
            }
        });
    }
}