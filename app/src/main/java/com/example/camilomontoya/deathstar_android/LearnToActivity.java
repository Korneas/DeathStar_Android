package com.example.camilomontoya.deathstar_android;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import serial.MessageSerial;

public class LearnToActivity extends AppCompatActivity {

    private ImageButton back, next;
    private static String GROUP_ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_to);

        back = (ImageButton) findViewById(R.id.backing);
        next = (ImageButton) findViewById(R.id.next);

        GROUP_ADDRESS = Comunicacion.getInstance().getGROUP_ADDRESS();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnToActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aviso("Gracias por ver el tutorial +10 prrro");
                startActivity(new Intent(LearnToActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


    }

    public void aviso(String msg) {
        Toast notificacion = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificacion.show();
    }
}
