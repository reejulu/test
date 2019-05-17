package com.example.milinear;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static com.example.milinear.R.layout.activity_ejercicio_presionar_colores;

// VERSION 1
// ESTA SE BASA EN PASAR EL VIEW view del XML para pasar la accion del OnClick
//  En layout.xml
//        Button ------> OnClick ---> al metodo "iniciar"
//  En file.java
//  LinearLayout cajatocada = (LinearLayout)view;   --->donde la vista buton se copia a cajatocada
//  Para empezar a contar el tiempo de duraccion hasta que se cambian todas las casillas se crea
//  un boolean que esta a false hasta que el boton INICIAR se ha pulsado .
//            -En este momento el boton se hace invisible y el boolean se pone true
//  para identificar el LinearLayout presionado hay que leer el getId de la view y guardarlo en
//  un array: -Si ya estaba en el array hay que quitarlo y decrementar el numero de casillas cambiadas
//            -Si no lo estaba se añade al array
//            -Si no hay ninguno simplemente se añade
// Cuando el numero de casillas cambiada es 5 entonces se cierra la App.


public class EjercicioPresionarColoresVersion1 extends AppCompatActivity {

    long tInicio;
    long tInicioBoton;
    Button boton;
    private int num_veces ;
    boolean inicio;
    LinearLayout id;
    Button cajatocada;
    Button finalizar;
    ArrayList<Integer> ids = new ArrayList<Integer>();

    boolean bloqueclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ejercicio_presionar_colores);
        this.tInicio = System.currentTimeMillis();
        boton = findViewById(R.id.boton_iniciar);

    }

    public void iniciar (View view){
        cajatocada = (Button) view;
        // al presionar el boton se mira el tiempo, se pone invisible el boton y se inicia el marcado
        //  de las cajas, ademas se habilita el mismo poniendo inicio = true
        this.tInicioBoton = System.currentTimeMillis();
        boton.setVisibility(View.INVISIBLE);
        //finalizar.setVisibility(View.INVISIBLE);
        inicio = true;
    }
    public void finalizarsi (View view){
        finalizar = (Button) view;
        salir();
    }

    public void marcarColor(View view) {
        // Si ya se ha pulsado el Boton Inicio entonces --true
        // eso quiere decir que empezamos a poder cambiar cajas de colores

        //id = view.findViewById(R.id.bloque1);
        LinearLayout x = (LinearLayout) view;
        if (inicio == true) {
        if (ids.size() == 0){
            ids.add(x.getId());
            num_veces = num_veces +1;
            bloqueclick = false;    // poner en negro
        }else {
            int temporal = num_veces;
            for (int i=0 ;i<= ids.size()-1;i++){
                if (ids.get(i)== x.getId()){
                    num_veces = num_veces - 1;
                    ids.remove(i);
                    bloqueclick = true;  // poner en blanco
                    break;
                }
            }
            if (temporal == num_veces){
                ids.add(x.getId());
                num_veces = num_veces +1;
                bloqueclick = false;    // poner en negro
            }else {
                //no hay que hacer nada ya estaba y se ha quitado
            }
        }
        Log.i("test","array momentaneo es: " + ids);
        Log.i("test","el linear pulsado es " +x.getId());
       // Log.i("test","x" +x.toString().contains("bloque"));

      //  if (inicio == true) {
            if (bloqueclick == false) {
                view.setBackgroundColor(getResources().getColor(R.color.negro));
                bloqueclick = true;
                Log.i("test", "numero de veces " + num_veces);
                if (num_veces == 6) {
                    continuar(cajatocada);
                    Button b = findViewById(R.id.boton_no);
                    b.setVisibility(View.VISIBLE);
                    //salir();
                }
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.blanco));
                bloqueclick = false;
                Log.i("test", "numero de veces " + num_veces);
            }

            }else {
            // No hay que hacer nada aun . Esperar a que el boton INICIAR se presione
        }
    }

    public void continuar(Button cajatocada){
        Button x = (Button) cajatocada;
        x.setVisibility(View.VISIBLE);
        x.append(" continuar? si",0,14);
        inicio = false;
    }

    public void salir(){
        long tFinal = System.currentTimeMillis();
        double tDuracion = (tFinal-tInicio)/1000d;
        double tDuracion1 = (tFinal-tInicioBoton)/1000d;
        String Texto = String.format("Duracion en segundos de la App es: %1$.3f",tDuracion);
        String Texto1 = String.format("Duracion en segundos de la App es: %1$.3f",tDuracion1);
        Log.i("test",Texto);
        Log.i("test",Texto1);
        this.finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }
    }


}
