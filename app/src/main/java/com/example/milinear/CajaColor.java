package com.example.milinear;
import android.content.Context;
import android.content.Intent;
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


public class CajaColor extends AppCompatActivity {
    boolean iniciarotra;
    private int color;
    private int colorblanco;
    private int veces;
    long tInicioBoton;
    long tFinal;
    boolean inicio = false;
    Button cajatocada;
    Button finalizar;
    int numeroActivities;
    double recordPartida = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_color);
        this.color = getResources().getColor(R.color.negro);
        this.colorblanco = getResources().getColor(R.color.blanco);
        this.veces = 0;
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

                startActivity(new Intent(this, SplitView.class));
                Log.i("MIAPP","Toco version infinito");
                break;
            case R.id.version_original:
                // lanzar la version original ya estoy no hago nada
                startActivity(new Intent(this,EjercicioPresionarColoresVersion1.class));
                Log.i("MIAPP","Toco version original");
                break;
            case R.id.version_carlos:
                // lanzar la version de Carlos   --- NO HACE FALTA Ya estamos
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

    public void iniciar (View view){
        if (iniciarotra == true){
            iniciarotra();
        }
        inicio = true;
        tInicioBoton = System.currentTimeMillis();

        cajatocada = (Button) view;
        // al presionar el boton se mira el tiempo, se pone invisible el boton y se inicia el marcado
        //  de las cajas, ademas se habilita el mismo poniendo inicio = true
        this.tInicioBoton = System.currentTimeMillis();
        Button boton = findViewById(R.id.boton_iniciar);
        boton.setVisibility(View.INVISIBLE);
        Button b = findViewById(R.id.boton_no);
        b.setVisibility(View.INVISIBLE);
    }
    public void iniciarotra() {
        // ANTES DE LANZAR DE NUEVO LA ACTIVITY GUARDAMOS LOS VALORES INDICADOS Y CERRAMOS LA ACTUAL
        Intent intent = new Intent(CajaColor.this, CajaColor.class);
        Bundle bundle = new Bundle();
        numeroActivities = numeroActivities + 1;
        Log.i("MIAPP","Creamos otra ,numero de activities :" +numeroActivities);
        bundle.putInt("key2",numeroActivities);
        bundle.putDouble("key", recordPartida);
        intent.putExtras(bundle);
        CajaColor.this.finish();
        startActivity(intent);
    }

    public void cambiaColor(View v){

        if (inicio == true) {
            LinearLayoutCustom caja = (LinearLayoutCustom) v;
            if (caja.isUsado()) {
                Log.d("MIAPP", "Ya ha sido tocada");  // la cambio de color a blanco
                caja.setBackgroundColor(this.colorblanco);
                caja.setUsado(false);
                this.veces = veces - 1;
            } else {
                caja.setBackgroundColor(this.color);
                caja.setUsado(true);
                this.veces += 1;
                if (this.veces == 6) {

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

                    //salir();
                }
            }
        }
    }

    public void mostrarDuracion() {
        long tFinal = System.currentTimeMillis();

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

        String Texto1 = String.format("Duracion del juego : %1$.3f", tDuracion1);
        String Texto2 = Texto1 + " Segundos";
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
    public void finalizarsi (View view){
        finalizar = (Button) view;
        salir();
    }
    public void salir(){
        //mostrarDuracion();
        // CIERRO VERSION ORIGINAL
        this.finish();
        Log.i("MIAPP","numero de activities :" +numeroActivities);
        // INICIO VERSION INFINITO
        //startActivity(new Intent(this, SplitView.class));
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        //     this.finishAffinity();
        // }
    }

}
