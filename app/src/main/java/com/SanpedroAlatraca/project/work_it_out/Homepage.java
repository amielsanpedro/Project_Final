package com.SanpedroAlatraca.project.work_it_out;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class Homepage extends AppCompatActivity {

    public Button button_logout, button_body, button_vit, button_med;
    public ImageButton button_activity;

    public void openMain(){
        Intent next = new Intent(this,MainActivity.class);
        startActivity(next);
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    public void openActivity(){
        Intent next = new Intent(this,ActivityPage.class);
        startActivity(next);
    }

    public void openBodyMeasurement(){
        Intent next = new Intent(this,BodyMeasurement.class);
        startActivity(next);
    }

    public void openVitals(){
        Intent next = new Intent(this,Vitals.class);
        startActivity(next);
    }

    public void openMedical(){
        Intent next = new Intent(this,Medical.class);
        startActivity(next);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        button_logout= (Button)findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMain();
            }
        });

        button_activity= (ImageButton)findViewById(R.id.button_activity);
        button_activity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity();
            }
        });

        button_body= (Button)findViewById(R.id.button_bodyMeasure);
        button_body.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openBodyMeasurement();
            }
        });

        button_vit= (Button)findViewById(R.id.button_vitals);
        button_vit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openVitals();
            }
        });

        button_med= (Button)findViewById(R.id.button_medicalID);
        button_med.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMedical();
            }
        });
    }
}
