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
import com.google.firebase.database.FirebaseDatabase;

public class Vitals extends AppCompatActivity {
    Button btnSave;
    EditText etPressure, etTemp, etHeart, etRespiratory;
    public void backFunc(){
        Intent next = new Intent(this,Homepage.class);
        startActivity(next);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etPressure = findViewById(R.id.bloodpressure);
        etTemp = findViewById(R.id.bloodtemp);
        etHeart = findViewById(R.id.heartrate);
        etRespiratory = findViewById(R.id.respiratoryrate);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pressure = etPressure.getText().toString().trim();
                String temp = etTemp.getText().toString().trim();
                String heart = etHeart.getText().toString().trim();
                String respiratory= etRespiratory.getText().toString().trim();

                if(pressure.isEmpty() || temp.isEmpty() || heart.isEmpty() || respiratory.isEmpty()){
                    if(pressure.isEmpty()){
                        etPressure.setError("Please fill up field.");
                    }
                    if(temp.isEmpty()){
                        etTemp.setError("Please fill up field.");
                    }
                    if(heart.isEmpty()){
                        etHeart.setError("Please fill up field.");
                    }
                    if(respiratory.isEmpty()){
                        etRespiratory.setError("Please fill up field.");
                    }
                }else{
                    VitalModel vitals = new VitalModel(pressure,temp,heart,respiratory);
                    FirebaseDatabase.getInstance().getReference("Vitals")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(vitals).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Vitals.this, "Vitals Saved.", Toast.LENGTH_SHORT).show();
                                backFunc();
                            }else{
                                Toast.makeText(Vitals.this, "Vitals Saving Failed.", Toast.LENGTH_SHORT).show();
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
