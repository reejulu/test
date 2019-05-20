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
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.Random;

public class SplitView extends AppCompatActivity {

    private static final int COLORES[] = { R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    private int indexColor = 0;
    private static Random r = new Random();
    Menu menureferencia;
    int contador = 1;  // valor inicial
    int contador1 = 0;
    int contadorColores = 1;

    // https://github.com/flcarballeda/CajaColores.git

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_view);
    }
// **************************************************************************************
// para añadir el APP Bar
// **************************************************************************************
    //  DIBUJO EL MENU

    @Override
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
                // lanzar la version infinito  ya estoy no hago nada
               // startActivity(new Intent(this, SplitView.class));
                Log.i("MIAPP","Toco version infinito");
                break;
            case R.id.version_original:
                // lanzar la version original
                startActivity(new Intent(this, EjercicioPresionarColoresVersion1.class));
                Log.i("MIAPP","Toco version original");
                break;
            case R.id.version_carlos:
                // lanzar la version de Carlos
                startActivity(new Intent(this, CajaColor.class));
                Log.i("MIAPP","Toco version Carlos");
                break;
            case R.id.version_OnClick:
                // lanzar la version de OnClick
                startActivity(new Intent(this, EjercicioPresionarColores.class));
                Log.i("MIAPP","Toco version OnClick");
                break;

                default: Log.i("MIAPP","Raro...raroooooo");
        }


        return super.onOptionsItemSelected(item);
    }

    private void mostrarLayout (View vista){

            //Log.i(getClass().getCanonicalName(), vista.getClass().getCanonicalName());
            Log.i("MIAPP", vista.getClass().getCanonicalName());


        if (vista instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) vista;
            //Log.i("MIAPP","numero de casilla es: " +viewGroup.getChildCount());
            for (int i = 0; i<viewGroup.getChildCount(); i++)
            {
                contador1 = contador1 + 1;
                Log.i("MIAPP","numero de casilla es: " + contador1);
                View vistahija = viewGroup.getChildAt(i);
                mostrarLayout(vistahija);


            }

        }

    }

    public void dividir(View view) {

        int randomColor;
        Random rand = new Random();
        int r1 = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        randomColor = Color.rgb(r1,g,b);
        //View hijo1.setBackgroundColor(randomColor);
        contador = contador + 1;   // cada vez se multipica por 2
                                   // i.e 1-vez = 1* 2= 2
                                   //     2-vez = 1 + 1*2 = 3
        Log.i("MIAPP","Contador de cuadros  es: "+contador);

        if (contador > 50){
            ViewGroup vista_raiz = findViewById(R.id.root);
            Log.i("MIAPP","el numero de hijos es :" +vista_raiz.getChildCount());
            vista_raiz.getChildCount();
            mostrarLayout(vista_raiz);
            Toast.makeText(this,"Hasta Luego Lucas",Toast.LENGTH_LONG).show();
            this.finish();
            startActivity(new Intent(this, EjercicioPresionarColoresVersion1.class));
            Log.i("MIAPP","Toco version original");

        }
        LinearLayout padre = (LinearLayout)view;
//        LinearLayout padre = (LinearLayout)findViewById( view.getId());
        LinearLayout hijo1 = new LinearLayout(this);
        int x = newId();
        Log.i("MIAPP", "newId es : "+ x);
        hijo1.setId( x);
        LinearLayout hijo2 = new LinearLayout(this);
        int y = newId();
        Log.i("MIAPP", "newId es : "+ y);
        hijo2.setId( y);
        if ( padre.getOrientation() == LinearLayout.VERTICAL) {
            Log.i("MIAPP", "Vertical");
//        android:layout_width="match_parent"
//        android:layout_height="0dp"
//        android:layout_weight="1"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0, 1F);
//        android:orientation="horizontal"
            hijo1.setOrientation( LinearLayout.HORIZONTAL);
            hijo1.setLayoutParams( parametros);
            hijo2.setOrientation( LinearLayout.HORIZONTAL);
            hijo2.setLayoutParams(parametros);
        } else {
            Log.i("MIAPP", "Horizontal");
//            android:layout_width="0dp"
//            android:layout_height="match_parent"
//            android:layout_weight="1"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.MATCH_PARENT, 1F);
//        android:orientation="vertical"
            hijo1.setOrientation( LinearLayout.VERTICAL);
            hijo1.setLayoutParams( parametros);
            hijo2.setOrientation( LinearLayout.VERTICAL);
            hijo2.setLayoutParams( parametros);
        }

    //   hijo1.setBackgroundColor(randomColor);
        hijo2.setBackgroundColor(randomColor);
        contadorColores = contadorColores + 1;
        Log.i("MIAPP","Color numero : "+contadorColores);
//        hijo1.setBackgroundColor( ((ColorDrawable) padre.getBackground()).getColor());
     //  hijo2.setBackgroundColor( getResources().getColor( COLORES[ indexColor]));
        hijo1.setVisibility( View.VISIBLE);
        hijo2.setVisibility( View.VISIBLE);
        indexColor++;
        if( indexColor == COLORES.length) {
            indexColor = 0;
        }
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
}
