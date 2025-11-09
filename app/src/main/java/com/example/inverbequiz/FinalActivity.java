package com.example.inverbequiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FinalActivity extends AppCompatActivity {

    private int contador;
    private String message;

    private TextView men, pun, top;

    private ArrayList<String> lista;
    private String nombre;

    private MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_final);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MiDBHelper db = new MiDBHelper(this);

        men = findViewById(R.id.Mensaje);
        pun = findViewById(R.id.Puntuacion);
        top = findViewById(R.id.Top);

        Intent intent = getIntent();

        contador = intent.getIntExtra("contador", 0);
        nombre = intent.getStringExtra("nombre");

        db.insertarNombre(nombre, contador);

        if(contador > 4){
            message = "Felicidades, eres DEMASIADO BUENO bro. ";
            md = MediaPlayer.create(FinalActivity.this, R.raw.win);
            md.start();
        }else if(contador < 4){
            message = "Hay que esforzarse un poco más bro";
            md = MediaPlayer.create(FinalActivity.this, R.raw.lose);
            md.start();
        }

        men.setText(message);
        pun.setText(String.valueOf(contador));

        lista = db.obtenerTop3();

        top.setText(lista.toString());

    }
}