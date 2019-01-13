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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private Button button_done;
    EditText regFirst, regLast, regEmail, regPass, regConfirm;
    private FirebaseAuth mAuth;

    public void openMain(){
        Intent next = new Intent(this,MainActivity.class);
        startActivity(next);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        regFirst = findViewById(R.id.firstName_txt);
        regLast = findViewById(R.id.lastName_txt);
        regEmail = findViewById(R.id.email_txt);
        regPass = findViewById(R.id.Password_txt);
        regConfirm = findViewById(R.id.confirmPassword_txt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_done= (Button)findViewById(R.id.button_done);
        button_done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String firstname = regFirst.getText().toString().trim();
                final String lastname = regLast.getText().toString().trim();
                final String email = regEmail.getText().toString().trim();
                final String password = regPass.getText().toString().trim();
                String confirm = regConfirm.getText().toString().trim();

                if(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()
                        || confirm.isEmpty() || password.length() < 6){
                    Toast.makeText(SignUp.this, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
                    if(password.length() < 6){
                        Toast.makeText(SignUp.this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(password.equals(confirm)){
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    User user = new User(firstname, lastname, email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUp.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                                openMain();
                                            }
                                        }
                                    });

                                }else{
                                    Toast.makeText(SignUp.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(SignUp.this, "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
