package com.example.milinear;


import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;
public class SplitViewCopia extends AppCompatActivity {

    private static final int COLORES[] = { R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    private int indexColor = 0;
    private static Random r = new Random();
    Menu menureferencia;
    int contador = 1;  // valor inicial
    int contador1 = 0;
    int contadorColores = 1;
    public static final String SPLIT_VIEW_DIVIDIR = "MIAPP";
    int contardorToques = 2;
    int cajasportoque = 2;
    LinearLayout vista;
    TextView texto;
    Button boton;
    long t1Start;
    long t1Pause;
    double t1Duraciont1Startt1Pause = 0;
    // https://github.com/flcarballeda/CajaColores.git

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_view_copia);


        if(getIntent().getExtras() != null) {
            t1Start = System.currentTimeMillis();
            contardorToques = getIntent().getIntExtra("toques",2);  // numero de toques
            cajasportoque = getIntent().getIntExtra("cajas",2);
        }

    }
// **************************************************************************************
// para añadir el APP Bar
// **************************************************************************************
    //  DIBUJO EL MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
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
            case R.id.pause:
                // Paramos la ejecucion del juego , el contador de juego debe pausarse tambien
                // startActivity(new Intent(this, SplitView.class));
                t1Pause = System.currentTimeMillis();
                if (t1Duraciont1Startt1Pause == 0) {
                    t1Duraciont1Startt1Pause = (t1Pause - t1Start)/1000d;  // en segundos

                }else {
                    t1Duraciont1Startt1Pause = ((t1Pause - t1Start)/1000d) + t1Duraciont1Startt1Pause;
                }
                Log.i("MIAPP","tiempo de juego : "+ t1Duraciont1Startt1Pause);

                String Texto = String.format("Duracion en segundos de la App es: %1$.3f", t1Duraciont1Startt1Pause);
                String Texto2 = Texto + " Segundos";
                Log.i("MIAPP","tiempo de juego : "+ Texto2 );
                 vista = findViewById(R.id.root);
                 texto = findViewById(R.id.txtpausar);
                 boton = findViewById(R.id.btnContinuar);
                vista.setVisibility(View.INVISIBLE);
                texto.setVisibility(View.VISIBLE);
                boton.setVisibility(View.VISIBLE);
                Log.i("MIAPP","Toco version pause");
                break;
            case R.id.reiniciar:
                // Si el Juego estaba en modo pausa ,,entonces continuar , el contador se restablece
                // con el valor pausado anteriormente
                //startActivity(new Intent(this, EjercicioPresionarColoresVersion1.class));
                t1Start = System.currentTimeMillis();
                vista = findViewById(R.id.root);
                texto = findViewById(R.id.txtpausar);
                boton = findViewById(R.id.btnContinuar);
                vista.setVisibility(View.VISIBLE);
                texto.setVisibility(View.INVISIBLE);
                boton.setVisibility(View.INVISIBLE);
                Log.i("MIAPP","Toco version reniciar");
                break;
            case R.id.finalizar:
                // Se para la ejecucion del juego actual y se lanza de nuevo desde el inicio
                //startActivity(new Intent(this, CajaColor.class));
                this.finish();
                Log.i("MIAPP","Toco version finalizar");
                break;

            default: Log.i("MIAPP","Raro...raroooooo");
        }


        return super.onOptionsItemSelected(item);
    }


    private int randomColor() {
        int randomColor1;
        Random rand = new Random();
        int r1 = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        randomColor1 = Color.rgb(r1,g,b);
        return randomColor1;

    }

    public void dividir(View view) {

        contador = contador + 1;   // cada vez se multipica por 2
        // i.e 1-vez = 1* 2= 2
        //     2-vez = 1 + 1*2 = 3
        Log.i("MIAPP","Contador de cuadros  es: "+contador);
        limite(contador);
        LinearLayout padre = (LinearLayout) view;
        for (int i= 0;i<cajasportoque;i++){   //  numero de cuadros por toque
            LinearLayout hijo = crearHijo(padre.getOrientation());
            padre.addView(hijo);
        }
        /**
        LinearLayout hijo1 = crearHijo(padre.getOrientation());
        padre.addView( hijo1);
        LinearLayout hijo2 = crearHijo(padre.getOrientation());
        padre.addView( hijo2);
        */
    }

    private LinearLayout crearHijo (int orientacion)
    {
        LinearLayout nueva_caja = null;
        LinearLayout.LayoutParams parametros = null;

        nueva_caja = new LinearLayout(this);
        nueva_caja.setId(newId());

        if (orientacion == LinearLayout.VERTICAL)
        {
            nueva_caja.setOrientation( LinearLayout.HORIZONTAL);
            parametros = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0, 1F);
        }else //padre en horizontal
        {
            nueva_caja.setOrientation( LinearLayout.VERTICAL);
            parametros = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.MATCH_PARENT, 1F);
        }
        nueva_caja.setLayoutParams(parametros);
        nueva_caja.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { dividir(v); }});
        nueva_caja.setBackgroundColor( randomColor());
        nueva_caja.setVisibility( View.VISIBLE);

        return  nueva_caja;
    }




    


    public void limite(int contador) {
        if (this.contador > contardorToques){
            Toast.makeText(this,"Hasta Luego Lucas",Toast.LENGTH_LONG).show();
            this.finish();


        }

    }


    public void onClick(LinearLayout padre, LinearLayout hijo1, LinearLayout hijo2) {
        padre.addView( hijo1);
        padre.addView( hijo2);
        hijo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });


        hijo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });
    }


    /**
     * Es el ID calculado
     * Va generando números aleatorios. Comprueba que el valor generado
     * no coincide con un ID existente y si no existe devuelvo el nuevo
     * valor.
     *
     * @return

     */

    private int newId() {
        int resultado = -1;
        do {
            resultado = r.nextInt(Integer.MAX_VALUE);
        } while( findViewById( resultado) != null);
        return resultado;
    }

    public void Reiniciar(View view) {
        // hay que poner el procedimiento de poner invisible text y boton
        // despues se retoma la activity y ya se puede continuar jugando
        // ademas hay que restablecer los contadores de tiempo
        t1Start = System.currentTimeMillis();
        vista = findViewById(R.id.root);
        texto = findViewById(R.id.txtpausar);
        boton = findViewById(R.id.btnContinuar);
        vista.setVisibility(View.VISIBLE);
        texto.setVisibility(View.INVISIBLE);
        boton.setVisibility(View.INVISIBLE);
    }
}
