package com.example.camilomontoya.deathstar_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import serial.MessageSerial;

public class MainActivity extends AppCompatActivity implements Observer{

    private ImageButton start, instruc;
    private static String GROUP_ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (ImageButton) findViewById(R.id.start);
        instruc = (ImageButton) findViewById(R.id.instruc);

        GROUP_ADDRESS = Comunicacion.getInstance().getGROUP_ADDRESS();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().enviar(new MessageSerial(10,0, "online"), GROUP_ADDRESS);
                startActivity(new Intent(MainActivity.this,PlayActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        instruc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LearnToActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }

    public void aviso(String msg) {
        Toast notificacion = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificacion.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
