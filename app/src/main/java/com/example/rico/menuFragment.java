package com.example.rico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class menuFragment extends AppCompatActivity   implements  View.OnClickListener{
    private CardView cardReservar,cardTc, cardComentarios, cardAjustes, cardInfo, cardRestaurante,cardmapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fragment);
        cardReservar=findViewById(R.id.cardReservar);
        cardTc=findViewById(R.id.cardTc);
        cardComentarios=findViewById(R.id.cardComentarios);
        cardAjustes=findViewById(R.id.cardAjustes);
        cardInfo=findViewById(R.id.cardInfo);
        cardRestaurante=findViewById(R.id.cardRestaurante);





        cardReservar.setOnClickListener(this::onClick);
        cardTc.setOnClickListener(this::onClick);
        cardComentarios.setOnClickListener(this::onClick);
        cardAjustes.setOnClickListener(this::onClick);
        cardInfo.setOnClickListener(this::onClick);
        cardRestaurante.setOnClickListener(this::onClick);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.cardReservar: i = new Intent(this, Reservade.class);startActivity(i);
                break;
            case R.id.cardTc: i = new Intent(this, Terminos.class);startActivity(i);
                break;
            case R.id.cardComentarios: i = new Intent(this, coment.class);startActivity(i);
                break;
            case R.id.cardAjustes: i = new Intent(this, ajustes.class);startActivity(i);
                break;
            case R.id.cardInfo: i = new Intent(this, info.class);startActivity(i);
                break;
            case R.id.cardRestaurante: i = new Intent(this, Restaurante.class);startActivity(i);
                break;

            default:break;

        }
    }
}

