package com.example.camilomontoya.deathstar_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Observable;
import java.util.Observer;

import serial.Item;
import serial.Usuario;

public class PlayActivity extends AppCompatActivity implements Observer{

    private ToggleButton player1, player2, player3;
    private ImageButton r2d2,fighter,bomber,stroom;
    private String GROUP_ADDRESS;
    private String j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Comunicacion.getInstance().addObserver(this);

        GROUP_ADDRESS = Comunicacion.getInstance().getGROUP_ADDRESS();

        player1 = (ToggleButton) findViewById(R.id.player1);
        player2 = (ToggleButton) findViewById(R.id.player2);
        player3 = (ToggleButton) findViewById(R.id.player3);

        r2d2 = (ImageButton) findViewById(R.id.r2d2);
        fighter = (ImageButton) findViewById(R.id.fighter);
        bomber = (ImageButton) findViewById(R.id.bomber);
        stroom = (ImageButton) findViewById(R.id.stroom);

        r2d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(1,1),GROUP_ADDRESS);
                }

                if(player2.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(2,1),GROUP_ADDRESS);
                }

                if(player3.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(3,1),GROUP_ADDRESS);
                }

                if(!player1.isChecked() && !player2.isChecked() && !player3.isChecked()){
                    aviso("Selecciona almenos un jugador");
                }
            }
        });

        fighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(1,2),GROUP_ADDRESS);
                }

                if(player2.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(2,2),GROUP_ADDRESS);
                }

                if(player3.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(3,2),GROUP_ADDRESS);
                }

                if(!player1.isChecked() && !player2.isChecked() && !player3.isChecked()){
                    aviso("Selecciona almenos un jugador");
                }
            }
        });

        bomber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(1,3),GROUP_ADDRESS);
                }

                if(player2.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(2,3),GROUP_ADDRESS);
                }

                if(player3.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(3,3),GROUP_ADDRESS);
                }

                if(!player1.isChecked() && !player2.isChecked() && !player3.isChecked()){
                    aviso("Selecciona almenos un jugador");
                }
            }
        });

        stroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(1,4),GROUP_ADDRESS);
                }

                if(player2.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(2,4),GROUP_ADDRESS);
                }

                if(player3.isChecked()){
                    Comunicacion.getInstance().enviar(new Item(3,4),GROUP_ADDRESS);
                }

                if(!player1.isChecked() && !player2.isChecked() && !player3.isChecked()){
                    aviso("Selecciona almenos un jugador");
                }
            }
        });
    }

    public void aviso(String msg) {
        Toast notificacion = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        notificacion.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Usuario){
            Usuario u = (Usuario) arg;
            if(u.getEmisor()==1){
                nuevoUsuario(player1,u.getName());
            }

            if(u.getEmisor()==2){
                nuevoUsuario(player2,u.getName());
            }

            if(u.getEmisor()==3){
                nuevoUsuario(player3,u.getName());
            }
        }
    }

    private void nuevoUsuario(final ToggleButton t,final String name){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                j = t.getText()+"\n"+name.substring(0,name.length()-1);
                t.setText(j);
                t.setTextOff(j);
                t.setTextOn(j);
            }
        });
    }
}
