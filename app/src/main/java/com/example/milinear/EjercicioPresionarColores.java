package com.example.milinear;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.milinear.R.layout.activity_ejercicio_presionar_colores;

public class EjercicioPresionarColores extends AppCompatActivity {
    private LinearLayout padre;
    long tInicio;
    long tInicioBoton;
    private int ncolor;
    Button boton;
    double recordPartida = 0;
    private int num_veces ;
    boolean inicio;
    boolean bloque1click;
    boolean bloque2click;
    boolean bloque3click;
    boolean bloque4click;
    boolean bloque5click;
    boolean bloque6click;
    boolean iniciarotra;
    Button finalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ejercicio_presionar_colores);
        this.ncolor = getResources().getColor(R.color.negro);

        // INICIALIZACION
        this.tInicio = System.currentTimeMillis();
        boton = findViewById(R.id.boton_iniciar);
        iniciarotra = false;


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
        }

    }

    public void marcarColor1(View view){
        LinearLayout cajatocada = (LinearLayout)view;
        cajatocada.setBackgroundColor(this.ncolor);
        num_veces = num_veces +1;
        if (num_veces == 6){
            salir();
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

                startActivity(new Intent(this, SplitView.class));
                Log.i("MIAPP","Toco version infinito");
                break;
            case R.id.version_original:
                // lanzar la version original
                startActivity(new Intent(this,EjercicioPresionarColoresVersion1.class));
                Log.i("MIAPP","Toco version original");
                break;
            case R.id.version_carlos:
                // lanzar la version de Carlos

                startActivity(new Intent(this, CajaColor.class));
                Log.i("MIAPP","Toco version Carlos");
                break;

            case R.id.version_OnClick:
                // lanzar la version OnClick ya estoy no hago nada
                Log.i("MIAPP","Toco version OnClick");
                break;

            default: Log.i("MIAPP","Raro...raroooooo");
        }
        return super.onOptionsItemSelected(item);
    }



    public void iniciarotra() {
        // ANTES DE LANZAR DE NUEVO LA ACTIVITY GUARDAMOS LOS VALORES INDICADOS Y CERRAMOS LA ACTUAL
        Intent intent = new Intent(EjercicioPresionarColores.this, EjercicioPresionarColores.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("key", recordPartida);
        intent.putExtras(bundle);
        EjercicioPresionarColores.this.finish();
        startActivity(intent);
    }




    public void iniciar (View view){
        if (iniciarotra == true){
            iniciarotra();
        }else {
            this.tInicioBoton = System.currentTimeMillis();
            boton.setVisibility(View.INVISIBLE);
            inicio = true;
            // es necesario activar los onclick de los linearLayout por eso llamo al metodo marcarColor
            marcarColor(view);
        }

    }

    public void salir(){

        this.finish();
  //      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
  //          this.finishAffinity();
  //      }
    }

    public void finalizarsi (View view){
        finalizar = (Button) view;
        salir();
    }

    public void salirPrevio () {

        // BOTON "CONTINUAR" + comentario "? SI" ES MOSTRADO
        boton.setVisibility(View.VISIBLE);
        // ------------> en algunos terminales da error y es necesario definir
        //               en el button xml
        //               android:inputType="text"
        boton.setText("Otra vez");
        //cajatocada.append(" continuar? si",0,14);
        inicio = false;
        // marca para indicar que se ha ejecutado una partida
        iniciarotra = true;
        // BOTON "finalizar" SE MUESTRA DE NUEVO
        Button b = findViewById(R.id.boton_no);
        b.setVisibility(View.VISIBLE);
        mostrarDuracion();
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

    public void marcarColor(View view) {

    if (inicio == true) {
        final LinearLayout pepe1 = findViewById(R.id.bloque1);
        pepe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque1click == false) {
                    pepe1.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque1click = true;
                    num_veces = num_veces + 1;
                    Log.i("test", "numero de veces " + num_veces);
                    if (num_veces == 6) {
                        salirPrevio();
                    }
                } else {
                    pepe1.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque1click = false;
                    num_veces = num_veces - 1;
                    Log.i("test", "numero de veces " + num_veces);
                }
            }
        });

        final LinearLayout pepe2 = findViewById(R.id.bloque2);
        pepe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque2click == false) {
                    pepe2.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque2click = true;
                    num_veces = num_veces + 1;
                    Log.i("test", "numero de veces " + num_veces);
                    if (num_veces == 6) {
                        salir();
                    }
                } else {
                    pepe2.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque2click = false;
                    num_veces = num_veces - 1;
                    Log.i("test", "numero de veces " + num_veces);
                }
            }
        });

        final LinearLayout pepe3 =  findViewById(R.id.bloque3);
        pepe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque3click == false) {
                    pepe3.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque3click = true;
                    num_veces = num_veces + 1;
                    Log.i("test", "numero de veces " + num_veces);
                    if (num_veces == 6) {
                        salirPrevio();
                    }
                } else {
                    pepe3.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque3click = false;
                    num_veces = num_veces - 1;
                    Log.i("test", "numero de veces " + num_veces);
                }
            }
        });
        final LinearLayout pepe4 =  findViewById(R.id.bloque4);
        pepe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque4click == false) {
                    pepe4.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque4click = true;
                    num_veces = num_veces + 1;
                    Log.i("test", "numero de veces " + num_veces);
                    if (num_veces == 6) {
                        salirPrevio();
                    }
                } else {
                    pepe4.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque4click = false;
                    num_veces = num_veces - 1;
                    Log.i("test", "numero de veces " + num_veces);
                }
            }
        });
        final LinearLayout pepe5 =  findViewById(R.id.bloque5);
        pepe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque5click == false) {
                    pepe5.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque5click = true;
                    num_veces = num_veces + 1;
                    Log.i("test", "numero de veces " + num_veces);
                    if (num_veces == 6) {
                       salirPrevio();
                    }
                } else {
                    pepe5.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque5click = false;
                    num_veces = num_veces - 1;
                    Log.i("test", "numero de veces " + num_veces);
                }
            }
        });
        final LinearLayout pepe6 =  findViewById(R.id.bloque6);
        pepe6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bloque6click == false) {
                    pepe6.setBackgroundColor(getResources().getColor(R.color.negro));
                    bloque6click = true;
                    num_veces = num_veces + 1;
                    Log.i("test", "numero de veces " + num_veces);
                    if (num_veces == 6) {
                        salirPrevio();
                    }
                } else {
                    pepe6.setBackgroundColor(getResources().getColor(R.color.blanco));
                    bloque6click = false;
                    num_veces = num_veces - 1;
                    Log.i("test", "numero de veces " + num_veces);
                }
            }
        });
    }
       // padre = (LinearLayout) getResources().getLayout(activity_ejercicio_presionar_colores);;
    //padre = (LinearLayout) findViewById(R.id.bloque).getResources().getLayout(activity_ejercicio_presionar_colores);
   // Log.i("hola","padre es : " + (padre.getResources().getLayout(activity_ejercicio_presionar_colores)));
    //padre.setBackgroundColor(getResources().getColor(R.color.azul));

    }


}
