package com.example.milinear;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    boolean iniciarotra;
    long tInicio;
    long tInicioBoton;
    Button boton;
    private int num_veces ;
    boolean inicio;
    LinearLayout id;
    Button cajatocada;
    Button finalizar;
    double recordPartida = 0;
    int numeroActivities;
    ArrayList<Integer> ids = new ArrayList<Integer>();
    boolean bloqueclick;
   // Bundle mstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ejercicio_presionar_colores);
   // INICIALIZACION
        this.tInicio = System.currentTimeMillis();
        boton = findViewById(R.id.boton_iniciar);
        iniciarotra = false;
        //mstate = savedInstanceState;
   // NO MOSTRAR TEXTOS OCULTOS , solo para ver resultado de tiempo y record
        TextView duracionmostar = findViewById(R.id.txtmostrarduracion);
        duracionmostar.setVisibility(View.INVISIBLE);
        TextView mostrarRecord = findViewById(R.id.txtmostrarrecord);
        mostrarRecord.setVisibility(View.INVISIBLE);
   // CUANDO ES LA PRIMERA VEZ QUE SE EJECUTA ESTA ACTIVIDAD EL INTENT NO TIENE CAMPOS EXTRA ,
        // solo para ver resultado de tiempo y record
        if(getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            //numeroActivities = numeroActivities + 1;
            Log.i("MIAPP","hemos llamado mas de una vez a esta activity");
            recordPartida = extras.getDouble("key");
        } else {
            numeroActivities = 0;
        }
    }

// **************************************************************************************
// para añadir el APP Bar
// **************************************************************************************

    @Override
    //  DIBUJO EL MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // para crear uno nuevo por codigo
        //    menu.add("Fulanito");
        // para guardar el menu ,,,por si luego lo necesito
        //    this.menureferencia = menu;

        return super.onCreateOptionsMenu(menu);
    }
    // RECIBE EL EVENTO DEL MENU ELEGIDO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.version_infinito:
                // lanzar la version infinito
      // EN ANDROIDMANIFEST FIJAMOS A TRUE EL COMPONENTE SIGUIENTE:  <---*** SE RECUPERARA EL ESTADO PREVIO
      //      android:alwaysRetainTaskState="true"

                numeroActivities = numeroActivities + 1;
                Log.i("MIAPP","Creamos otra ,numero de activities :" +numeroActivities);
                startActivity(new Intent(this, SplitView.class));
                Log.i("MIAPP","Toco version infinito");
                break;
            case R.id.version_original:
                // lanzar la version original ya estoy no hago nada
                Log.i("MIAPP","Toco version original");
                break;
            case R.id.version_carlos:
                // lanzar la version de Carlos
                numeroActivities = numeroActivities + 1;
                Log.i("MIAPP","Creamos otra ,numero de activities :" +numeroActivities);
                startActivity(new Intent(this, CajaColor.class));
                Log.i("MIAPP","Toco version Carlos");
                break;
            case R.id.version_OnClick:
                // lanzar la version OnClick
                startActivity(new Intent(this,EjercicioPresionarColores.class));
                Log.i("MIAPP","Toco version OnClick");
                break;

            default: Log.i("MIAPP","Raro...raroooooo");
        }
        return super.onOptionsItemSelected(item);
    }

    public void iniciarotra() {
        // ANTES DE LANZAR DE NUEVO LA ACTIVITY GUARDAMOS LOS VALORES INDICADOS Y CERRAMOS LA ACTUAL
        Intent intent = new Intent(EjercicioPresionarColoresVersion1.this, EjercicioPresionarColoresVersion1.class);
        Bundle bundle = new Bundle();
        numeroActivities = numeroActivities + 1;
        Log.i("MIAPP","Creamos otra ,numero de activities :" +numeroActivities);
        bundle.putInt("key2",numeroActivities);
        bundle.putDouble("key", recordPartida);
        intent.putExtras(bundle);
        EjercicioPresionarColoresVersion1.this.finish();
        startActivity(intent);
    }

    public void iniciar (View view){
        if (iniciarotra == true){
            iniciarotra();
        }else {
            cajatocada = (Button) view;
            // al presionar el boton se mira el tiempo, se pone invisible el boton y se inicia el marcado
            //  de las cajas, ademas se habilita el mismo poniendo inicio = true
            this.tInicioBoton = System.currentTimeMillis();
            boton.setVisibility(View.INVISIBLE);
            Button b = findViewById(R.id.boton_no);
            b.setVisibility(View.INVISIBLE);
            //finalizar.setVisibility(View.INVISIBLE);
            inicio = true;
        }
    }

    public void finalizarsi (View view){
        finalizar = (Button) view;
        salir();
    }

    public void cambiarinicio() {
        inicio = true;
    }

    public void marcarColor(View view) {
        // Si ya se ha pulsado el Boton Inicio entonces --true
        // eso quiere decir que empezamos a poder cambiar cajas de colores

        //id = view.findViewById(R.id.bloque1);
        LinearLayout x = (LinearLayout) view;
        Log.i("MIAPP","EjercicioPresionarColeresVersion1-marcarColor -inicio es : "+ inicio);
        if (inicio == true) {
            if (ids.size() == 0) {
                ids.add(x.getId());
                num_veces = num_veces + 1;
                bloqueclick = false;    // poner en negro
            } else {
                int temporal = num_veces;
                for (int i = 0; i <= ids.size() - 1; i++) {
                    if (ids.get(i) == x.getId()) {
                        num_veces = num_veces - 1;
                        ids.remove(i);
                        bloqueclick = true;  // poner en blanco
                        break;
                    }
                }
                if (temporal == num_veces) {
                    ids.add(x.getId());
                    num_veces = num_veces + 1;
                    bloqueclick = false;    // poner en negro
                } else {
                    //no hay que hacer nada ya estaba y se ha quitado
                }
            }
            Log.i("MIAPP", "array momentaneo es: " + ids);
            Log.i("MIAPP", "el linear pulsado es " + x.getId());
            // Log.i("test","x" +x.toString().contains("bloque"));

            //  if (inicio == true) {
            if (bloqueclick == false) {
                view.setBackgroundColor(Color.red(100000));
               // view.setBackgroundColor(getResources().getColor(R.color.negro));
                bloqueclick = true;
                Log.i("MIAPP", "numero de veces " + num_veces);
                if (num_veces == 6) {

                    // BOTON "CONTINUAR" + comentario "? SI" ES MOSTRADO
                    cajatocada.setVisibility(View.VISIBLE);
                    // ------------> en algunos terminales da error y es necesario definir
                    //               en el button xml
                    //               android:inputType="text"
                    cajatocada.setText("Otra vez");
                    //cajatocada.append(" continuar? si",0,14);
                    inicio = false;
                    // marca para indicar que se ha ejecutado una partida
                    iniciarotra = true;
                    // BOTON "finalizar" SE MUESTRA DE NUEVO
                    Button b = findViewById(R.id.boton_no);
                    b.setVisibility(View.VISIBLE);
                    mostrarDuracion();
                }
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.blanco));
                bloqueclick = false;
                Log.i("MIAPP", "numero de veces " + num_veces);
            }

        } else {
            // No hay que hacer nada aun . Esperar a que el boton INICIAR se presione
        }
    }

    public void mostrarDuracion() {
        long tFinal = System.currentTimeMillis();
        double tDuracion = (tFinal - tInicio) / 1000d;
        double tDuracion1 = (tFinal - tInicioBoton) / 1000d;
        // COMPROBAR SI HAY RECORD
        int record = 0;  // no hay record flag
        if (recordPartida == 0) {
            // PRIMERA PARTIDA - HAY RECORD
            recordPartida = tDuracion1;
            record = 1;

        } else {
            // VER SI HAY NUEVO RECORD
            if (tDuracion1 < recordPartida) {
                // HAY RECORD
                recordPartida = tDuracion1;
                record = 1;
            } else {
                // CONTINUA EL RECORD ANTERIOR
            }
        }
        mostarRecord(tDuracion1, record);

        String Texto = String.format("Duracion en segundos de la App es: %1$.3f", tDuracion);
        String Texto1 = String.format("Duracion del juego : %1$.3f", tDuracion1);
        String Texto2 = Texto1 + " Segundos";
        Log.i("MIAPP", Texto);
        Log.i("MIAPP", Texto1);

        TextView duracionmostar = findViewById(R.id.txtmostrarduracion);
        duracionmostar.setVisibility(View.VISIBLE);
        duracionmostar.setText(Texto2);
    }

    private void mostarRecord( double tDuracion1, int record) {
        String Texto1;
        if (record == 1){
            // nuevo record
            Texto1 = String.format("Nuevo Record : %1$.3f",tDuracion1);
        } else {
            Texto1 = String.format("Record actual : %1$.3f", recordPartida);
        }
        String Texto2 = Texto1 + " Segundos";
        Log.i("MIAPP",Texto1 );

        TextView duracionmostar = findViewById(R.id.txtmostrarrecord);
        duracionmostar.setVisibility(View.VISIBLE);
        duracionmostar.setText(Texto2);
    }

    public void salir(){
        //mostrarDuracion();
        // CIERRO VERSION ORIGINAL
        this.finish();
        Log.i("MIAPP","numero de activities :" +numeroActivities);
        // INICIO VERSION INFINITO
       // startActivity(new Intent(this, SplitView.class));
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
       //     this.finishAffinity();
       // }
    }

}
