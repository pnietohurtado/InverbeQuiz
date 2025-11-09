package com.example.inverbequiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;

public class TerceraActivity extends AppCompatActivity {

    private int contador;
    private Chip r1,r2,r3;
    private Button siguiente;
    private String nombre;


    private MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tercera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            r1 = findViewById(R.id.r1);
            r2 = findViewById(R.id.r2);
            r3 = findViewById(R.id.r3);

            Intent intent = getIntent();

            contador = intent.getIntExtra("contador", 0);
            nombre = intent.getStringExtra("nombre");

                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            md = MediaPlayer.create(TerceraActivity.this, R.raw.incorrecto);
                            md.start();
                            Toast.makeText(TerceraActivity.this, "Respuesta Incorrecta! " + contador, Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(TerceraActivity.this, CuartaActivity.class);
                            intent.putExtra("contador", contador);
                            startActivity(intent);
                        }
                    });

                    r2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            md = MediaPlayer.create(TerceraActivity.this, R.raw.incorrecto);
                            md.start();
                            Toast.makeText(TerceraActivity.this, "Respuesta Incorrecta!", Toast.LENGTH_SHORT).show();

                            Intent intent  = new Intent(TerceraActivity.this, CuartaActivity.class);
                            intent.putExtra("contador", contador);
                            startActivity(intent);

                        }
                    });

                    r3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            md = MediaPlayer.create(TerceraActivity.this, R.raw.correcto);
                            md.start();
                            Toast.makeText(TerceraActivity.this, "Respuesta Correcta!", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(TerceraActivity.this, CuartaActivity.class);
                            contador++;
                            intent.putExtra("contador", contador);
                            intent.putExtra("nombre", nombre);
                            startActivity(intent);

                        }
                    });

    }
}