package com.example.camilomontoya.deathstar_android;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Observable;
import java.util.Observer;

import serial.Item;
import serial.Resultados;
import serial.Usuario;

public class PlayActivity extends AppCompatActivity implements Observer {

    private ToggleButton player1, player2, player3;
    private ImageButton r2d2, fighter, bomber, stroom;
    private String GROUP_ADDRESS;
    private String j;
    private boolean[] end;
    private Vibrator vib;

    private Resultados[] resultados;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Comunicacion.getInstance().addObserver(this);

        num = 0;
        end = new boolean[3];
        resultados = new Resultados[3];

        GROUP_ADDRESS = Comunicacion.getInstance().getGROUP_ADDRESS();
        vib = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

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
                vib.vibrate(200);
                if (player1.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(1, 1), GROUP_ADDRESS);
                }

                if (player2.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(2, 1), GROUP_ADDRESS);
                }

                if (player3.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(3, 1), GROUP_ADDRESS);
                }

                if (!player1.isChecked() && !player2.isChecked() && !player3.isChecked()) {
                    aviso("Selecciona almenos un jugador");
                }
            }
        });

        fighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);
                if (player1.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(1, 2), GROUP_ADDRESS);
                }

                if (player2.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(2, 2), GROUP_ADDRESS);
                }

                if (player3.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(3, 2), GROUP_ADDRESS);
                }

                if (!player1.isChecked() && !player2.isChecked() && !player3.isChecked()) {
                    aviso("Selecciona almenos un jugador");
                }
            }
        });

        bomber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);
                if (player1.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(1, 3), GROUP_ADDRESS);
                }

                if (player2.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(2, 3), GROUP_ADDRESS);
                }

                if (player3.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(3, 3), GROUP_ADDRESS);
                }

                if (!player1.isChecked() && !player2.isChecked() && !player3.isChecked()) {
                    aviso("Selecciona almenos un jugador");
                }
            }
        });

        stroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vib.vibrate(200);
                if (player1.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(1, 4), GROUP_ADDRESS);
                }

                if (player2.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(2, 4), GROUP_ADDRESS);
                }

                if (player3.isChecked()) {
                    Comunicacion.getInstance().enviar(new Item(3, 4), GROUP_ADDRESS);
                }

                if (!player1.isChecked() && !player2.isChecked() && !player3.isChecked()) {
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
        if (arg instanceof Usuario) {
            System.out.println("Me llego un usuario");
            Usuario u = (Usuario) arg;
            if (u.getEmisor() == 1) {
                nuevoUsuario(player1, u.getName());
            }

            if (u.getEmisor() == 2) {
                nuevoUsuario(player2, u.getName());
            }

            if (u.getEmisor() == 3) {
                nuevoUsuario(player3, u.getName());
            }
        }

        if (arg instanceof Resultados) {
            Resultados res = (Resultados) arg;
            System.out.println("Me llego un resultado de "+res.getEmisor());

            end[res.getEmisor() - 1] = true;
            end[1]=true;
            end[2]=true;
            resultados[num] = res;

            resultados[1] = new Resultados(2,240,17000,6,12);
            resultados[2] = new Resultados(2,240,17000,6,12);

            System.out.println(res.emisor+" "+res.getPuntuacion());

            /*if (end[0] && end[1] && end[2]) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent change = new Intent(PlayActivity.this, resultActivity.class);
                        change.putExtra("Resultados 1", resultados[0]);
                        change.putExtra("Resultados 2", resultados[1]);
                        change.putExtra("Resultados 3", resultados[2]);
                        startActivity(change);
                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    }
                });
            }*/

            num++;
        }
    }

    private void nuevoUsuario(final ToggleButton t, final String name) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                j = t.getText() + "\n" + name.substring(0, name.length() - 1);
                t.setText(j);
                t.setTextOff(j);
                t.setTextOn(j);
            }
        });
    }
}
