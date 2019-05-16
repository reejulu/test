package com.example.milinear;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import static com.example.milinear.R.layout.activity_ejercicio_presionar_colores;

public class EjercicioPresionarColores extends AppCompatActivity {
    private LinearLayout padre;
    private int ncolor;
    private int num_veces ;
    boolean bloque1click;
    boolean bloque2click;
    boolean bloque3click;
    boolean bloque4click;
    boolean bloque5click;
    boolean bloque6click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ejercicio_presionar_colores);
        this.ncolor = getResources().getColor(R.color.negro);
    }

    public void marcarColor1(View view){
        LinearLayout cajatocada = (LinearLayout)view;
        cajatocada.setBackgroundColor(this.ncolor);
        num_veces = num_veces +1;
        if (num_veces == 6){
            salir();
        }

    }

    private void salir(){
        this.finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }
    }

    public void marcarColor(View view) {
        final LinearLayout pepe =(LinearLayout)findViewById(R.id.bloque1);


        pepe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque1click == false) {
                    pepe.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque1click = true;
                    num_veces = num_veces +1;
                    Log.i("test","numero de veces " +num_veces);
                    if (num_veces == 6){
                        salir();
                    }
                } else {
                    pepe.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque1click = false;
                    num_veces = num_veces -1;
                    Log.i("test","numero de veces " +num_veces);
                }
            }
        });

        final LinearLayout pepe2 =(LinearLayout)findViewById(R.id.bloque2);
        pepe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque2click == false) {
                    pepe2.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque2click = true;
                    num_veces = num_veces +1;
                    Log.i("test","numero de veces " +num_veces);
                    if (num_veces == 6){
                        salir();
                    }
                } else {
                    pepe2.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque2click = false;
                    num_veces = num_veces -1;
                    Log.i("test","numero de veces " +num_veces);
                }
            }
        });

        final LinearLayout pepe3 =(LinearLayout)findViewById(R.id.bloque3);
        pepe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque3click == false) {
                    pepe3.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque3click = true;
                    num_veces = num_veces +1;
                    Log.i("test","numero de veces " +num_veces);
                    if (num_veces == 6){
                        salir();
                    }
                } else {
                    pepe3.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque3click = false;
                    num_veces = num_veces -1;
                    Log.i("test","numero de veces " +num_veces);
                }
            }
        });
        final LinearLayout pepe4 =(LinearLayout)findViewById(R.id.bloque4);
        pepe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque4click == false) {
                    pepe4.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque4click = true;
                    num_veces = num_veces +1;
                    Log.i("test","numero de veces " +num_veces);
                    if (num_veces == 6){
                        salir();
                    }
                } else {
                    pepe4.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque4click = false;
                    num_veces = num_veces -1;
                    Log.i("test","numero de veces " +num_veces);
                }
            }
        });
        final LinearLayout pepe5 =(LinearLayout)findViewById(R.id.bloque5);
        pepe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque5click == false) {
                    pepe5.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque5click = true;
                    num_veces = num_veces +1;
                    Log.i("test","numero de veces " +num_veces);
                    if (num_veces == 6){
                        salir();
                    }
                } else {
                    pepe5.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque5click = false;
                    num_veces = num_veces -1;
                    Log.i("test","numero de veces " +num_veces);
                }
            }
        });
        final LinearLayout pepe6 =(LinearLayout)findViewById(R.id.bloque6);
        pepe6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque6click == false) {
                    pepe6.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque6click = true;
                    num_veces = num_veces +1;
                    Log.i("test","numero de veces " +num_veces);
                    if (num_veces == 6){
                        salir();
                    }
                } else {
                    pepe6.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque6click = false;
                    num_veces = num_veces -1;
                    Log.i("test","numero de veces " +num_veces);
                }
            }
        });

       // padre = (LinearLayout) getResources().getLayout(activity_ejercicio_presionar_colores);;
    //padre = (LinearLayout) findViewById(R.id.bloque).getResources().getLayout(activity_ejercicio_presionar_colores);
   // Log.i("hola","padre es : " + (padre.getResources().getLayout(activity_ejercicio_presionar_colores)));
    //padre.setBackgroundColor(getResources().getColor(R.color.azul));

    }
}
