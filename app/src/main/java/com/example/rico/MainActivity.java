package com.example.rico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Controlador.PagerController;
import Controlador.Platos;
import Controlador.Reserva;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passEditText;
    private EditText rePassEditText;

    Button reservaMesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);
        rePassEditText = findViewById(R.id.rePassEditText);

      //  reservaMesas = (Button) findViewById(R.id.button1);
     /*   reservaMesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menuFragment.class);
                startActivity(intent);



            }
        });*/

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User:", "" + currentUser);
    }

    public void createUserWithEmailAndPassword(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,    task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("eXITO", "createUserWithEmail:success",task.getException());
                        Toast.makeText(MainActivity.this, "Registro Exitoso",
                                Toast.LENGTH_LONG).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        startActivity(new Intent(this,Login.class));
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("ERROR Uu", "createUserWithEmail:failure", task.getException());

                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // ...
                });


    }

    public void buttonPress(View v) {

        String email = emailEditText.getText().toString();
        String pass = passEditText.getText().toString();
        String rePass = rePassEditText.getText().toString();

        if (!email.isEmpty() &&!pass.isEmpty() && !rePass.isEmpty()) {
            if (pass.equals(rePass)) {
                if (pass.length() > 5) {
                    createUserWithEmailAndPassword(email, pass);

                } else
                    Toast.makeText(this, "la contraseña tiene minimo de 6", Toast.LENGTH_SHORT).show();

                    } else Toast.makeText(this, "la contraseña no son iguales", Toast.LENGTH_SHORT).show();
            } else{
               Toast.makeText(this,"Ingrese todos los campos vacios", Toast.LENGTH_SHORT).show();
        }




        }
    public void  TengoCuentaButton(View view){

        startActivity(new Intent(this,Login.class));
        finish();
    }

}
