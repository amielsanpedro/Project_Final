package com.SanpedroAlatraca.project.work_it_out;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    private Button button_signup, button_login;
    private FirebaseAuth mAuth;

    public void openSignup(){
        Intent next = new Intent(this,SignUp.class);
        startActivity(next);

    }

    public void openLogin(){
        Intent next = new Intent(this,Homepage.class);
        startActivity(next);finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        button_signup= (Button)findViewById(R.id.button_signup);
        button_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignup();
            }
        });
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        button_login= (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty()){
                    if(email.isEmpty()){
                        etEmail.setError("Please fill up field.");
                    }
                    if(password.isEmpty()){
                        etPassword.setError("Please fill up field");
                    }
                }else{
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                openLogin();
                            }else{
                                Toast.makeText(MainActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
