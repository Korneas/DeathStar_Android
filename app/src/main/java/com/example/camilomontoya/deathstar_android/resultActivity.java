package com.example.camilomontoya.deathstar_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import serial.Resultados;

public class resultActivity extends AppCompatActivity {

    private int[] score, dist, enemies;
    private Resultados[] results;
    private TextView[] puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        results = new Resultados[3];
        puntos = new TextView[9];

        puntos[0] = (TextView) findViewById(R.id.score_1);
        puntos[1] = (TextView) findViewById(R.id.score_2);
        puntos[2] = (TextView) findViewById(R.id.score_3);

        puntos[3] = (TextView) findViewById(R.id.dist_1);
        puntos[4] = (TextView) findViewById(R.id.dist_2);
        puntos[5] = (TextView) findViewById(R.id.dist_3);

        puntos[6] = (TextView) findViewById(R.id.enemies_1);
        puntos[7] = (TextView) findViewById(R.id.enemies_2);
        puntos[8] = (TextView) findViewById(R.id.enemies_3);

        Intent intento = getIntent();
        results[0] = (Resultados) intento.getSerializableExtra("Resultados 1");
        results[1] = (Resultados) intento.getSerializableExtra("Resultados 2");
        results[2] = (Resultados) intento.getSerializableExtra("Resultados 3");

        for (int i = 0; i < 3; i++) {
            puntos[i].setText(results[i].getPuntuacion());
            puntos[i+3].setText(results[i].getDistance());
            puntos[i+6].setText(results[i].getDistance());
        }

    }
}
