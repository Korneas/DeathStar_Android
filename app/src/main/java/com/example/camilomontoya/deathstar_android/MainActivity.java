package com.example.camilomontoya.deathstar_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import serial.MessageSerial;

public class MainActivity extends AppCompatActivity {

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
                Comunicacion.getInstance().enviar(new MessageSerial(10,1, "Envio datos"), GROUP_ADDRESS);
            }
        });
    }

    public void aviso(String msg) {
        Toast notificacion = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificacion.show();
    }
}
