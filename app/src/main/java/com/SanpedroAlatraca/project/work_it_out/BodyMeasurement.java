package com.SanpedroAlatraca.project.work_it_out;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BodyMeasurement extends AppCompatActivity {

    public void backFunc(){
        Intent next = new Intent(this,Homepage.class);
        startActivity(next);
    }
    Button btnSave;
    EditText etHeight, etWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_measurement);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnSave = findViewById(R.id.btnSave);
        etHeight = findViewById(R.id.height);
        etWeight = findViewById(R.id.weight);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = etHeight.getText().toString().trim();
                String weight = etWeight.getText().toString().trim();
                if(height.isEmpty() || weight.isEmpty()){
                    if(height.isEmpty()){
                        etHeight.setError("Please fill up field.");
                    }
                    if(weight.isEmpty()){
                        etWeight.setError("Please fill up field");
                    }
                }else{
                    Measurements measurements = new Measurements(height, weight);
                    FirebaseDatabase.getInstance().getReference("Body Measurements")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(measurements).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BodyMeasurement.this, "Body Measurement Saved.", Toast.LENGTH_SHORT).show();
                                backFunc();
                            }else{
                                Toast.makeText(BodyMeasurement.this, "Body Measurement Saving Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFunc();
            }
        });
    }
}
