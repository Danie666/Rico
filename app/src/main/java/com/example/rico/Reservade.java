package com.example.rico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.annotation.Documented;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Reservade extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
   //private FirebaseAuth mAuth;

    DatabaseReference mRootReference;

    private int dia,mes,ano,hora,minutos;
    Button mReservar,btnfecha,btnhora ;
    EditText mEditTextCantidad,mEditTextdetalle;
    EditText mSede, mFecha,mDia;
    @SuppressLint("WrongViewCast")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservade);
       // Button
       mRootReference= FirebaseDatabase.getInstance().getReference();

     /*   FirebaseUser user = mAuth.getCurrentUser();
        String userID =user.getUid();*/



        mReservar=findViewById(R.id.btnreserva);
        btnfecha=findViewById(R.id.btnfecha);
       btnhora=findViewById(R.id.btnhora);
        mEditTextCantidad=findViewById(R.id.txtcantidad);
        mEditTextdetalle=findViewById(R.id.txtdetalle);
        mSede=findViewById(R.id.txtsede);
        mFecha=findViewById(R.id.txtfecha);
        mDia=findViewById(R.id.txthora);

         btnfecha.setOnClickListener(this);
        btnhora.setOnClickListener(this);

        Spinner spinner =findViewById(R.id.spinnerSede);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
   //     spinner.setOnItemSelectedListener(this);







      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Sede Seleccionado"+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                mSede.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               String sede =mSede.getText().toString();
                String cantidad = mEditTextCantidad.getText().toString();
                String detalle =mEditTextdetalle.getText().toString();
               String fecha=mFecha.getText().toString();
                String dia=mDia.getText().toString();

                Map<String, Object> datosreserva = new HashMap<>();
               datosreserva.put("sede",sede);
               datosreserva.put("cantidad", cantidad);
               datosreserva.put("fecha",fecha);
                datosreserva.put("dia",dia);
                datosreserva.put("detalle", detalle);

              /*  mRootReference.child("reserva").getKey(userID).setValue(datosreserva); */
               mRootReference.child("reservas").push().setValue(datosreserva);
            }
        });


    }
    @Override
    public void onClick(View v) {
        if (v== btnfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFecha.setText(dayOfMonth + "/" + (month+1)+ "/" + year);
                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();


        }
        if (v==btnhora) {
            final Calendar c= Calendar.getInstance();
            hora= c.get(Calendar.HOUR_OF_DAY);
            minutos= c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mDia.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }

    }

 /*   @Override
    public void onClick(View v){
        if (v== btnfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFecha.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();


        }
        if (v==btnhora) {
            final Calendar c= Calendar.getInstance()
                    hora= c.get(Calendar.DAY_OF_MONTH);
                    minutos= c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                     mDia.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    } */


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

