package com.example.rico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText emailEditText2;
    private EditText passEditText2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText2=findViewById(R.id.emailEditText2);
        passEditText2=findViewById(R.id.passEditText2);


        mAuth = FirebaseAuth.getInstance();

           }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

}

    private void updateUI(FirebaseUser currentUser) {

        Log.i("user",""+currentUser);
    }
    public  void signInWithEmailAndPassword(String email,String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Exito", "signInWithEmail:success",task.getException());
                            Toast.makeText(Login.this, "Acceso Exitoso, Bienvenido",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            startActivity(new Intent(Login.this,menuFragment.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Error: Datos incorrectos",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public  void buttonPress(View view){

        String email=emailEditText2.getText().toString();
        String password=passEditText2.getText().toString();

        if (!email.isEmpty()&&!password.isEmpty()){
            if(password.length()>5){
                signInWithEmailAndPassword(email,password);
            }else{
                Toast.makeText(this,"contraseña equivoaca",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Llema todos los campos ",Toast.LENGTH_SHORT).show();

        }

   }
    public void registrarButton(View view){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    }