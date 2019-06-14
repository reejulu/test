package com.example.milinear;


import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class SplitViewCopia extends AppCompatActivity {

    private static final int COLORES[] = { R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    private int indexColor = 0;
    private static Random r = new Random();
    Menu menureferencia;
    int numerodeCajas = 1;  // valor inicial para ver numero de cajas totales depues de cade click
    int contador1 = 0;  // contador toques
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
    String Texto;
    String Texto2;
    Boolean jugarahora = false;
    // https://github.com/flcarballeda/CajaColores.git
    private static final String RECORD = "RecordPartidaAnterior";
    private static final String CLAVE = "RecordPartidaAnteriorClave";
    private static final String JUGADOR = "Jugadornombre";
    private static final String JUGADORCLAVE = "Jugadornombreclave";

    // news
    boolean inicio = false;
    ArrayList<Integer> ids = new ArrayList<Integer>();
    private int num_veces ;
    boolean bloqueclick;
    double recordjuego = 0;
    int record = 0;
    int otrojuego = 0;
    //boolean disableButtonFlagJugarAhora = false;
    Menu myMenu;
    MenuItem logojugarahora;
    EditText nombrejugador;
    String jugadorRecord;
    String jugadoractual;
    ArrayList<String> arrayData = new ArrayList<>();
    ArrayList<String> arrayDataLog = new ArrayList<>();
    MediaPlayer mediaPlayer;
    String dato1;
    String dato2;
    String dato3;
    String dato4;
    String Texto1;
    String Texto3previo;
    String Texto3;
    String Texto4;
    ArrayList<Puntuacion> top10 = new ArrayList<Puntuacion>();
    int id = 0;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_view_copia);

         //******************************//
        //Recupar Datos de Preferencias //
        //******************************//
        // RECORD = "RecordPartidaAnterior";
        // CLAVE = "RecordPartidaAnteriorClave";
        // JUGADOR = "Jugadornombre";
        // JUGADORCLAVE = "Jugadornombreclave";
        // Definicion de los nombre de los futuros ficheros xml que se van a crear
        SharedPreferences prefs = getSharedPreferences(RECORD,MODE_PRIVATE);  // no la uso
        SharedPreferences prefs1 = getSharedPreferences(JUGADOR,MODE_PRIVATE);// no la uso
        SharedPreferences pref2 = getSharedPreferences("arraydatalog",MODE_PRIVATE);
        SharedPreferences pref1 = getSharedPreferences("Serializados",MODE_PRIVATE);

        //*********************************************************************//
        //Datos de los ficheros "RecordPartidaAnterior" y "Jugadornombreclave" //
        //*********************************************************************//
        Float pepe = prefs.getFloat(CLAVE,0);
        String pepe1 = prefs1.getString(JUGADORCLAVE,"sin nombre");
        recordjuego = pepe;
        jugadorRecord = pepe1;
        Log.i("MIAPP","SplitViewCopia-onCreate-recordjuego"+recordjuego);
        Log.i("MIAPP","SplitViewCopia-onCreate-jugadorRecord"+jugadorRecord);
        //*********************************//
        //Datos del fichero "Serializados" //
        //*********************************//
        // arrayDataLog <---"valornuevo"
        String p2 = pref1.getString("valornuevo","[{}]");
        top10 = Puntuacion.getTop();
        Log.i("MIAPP","SplitViewCopia-onCreate-Serializados-recibido p2 es :"+ p2);
        Log.i("MIAPP","SplitViewCopia-onCreate-Serializados-recibido top10 es :"+ top10);
        //*********************************//
        //Datos del fichero "arraydatalog" //
        //*********************************//
        int size = pref2.getInt("size", -1);   //<--del fichero "arraydatalog"
        if (size >0 ) {             // si tengo algo guardado lo recupero sino no hago nada
            arrayDataLog.clear();   // borro el array
            for (int i = 0; i < size; i++)
                arrayDataLog.add(i, pref2.getString("Indice " + i, null));
        }
        Log.i("MIAPP","SplitViewCopia-onCreate-arrayDatalog- recibido de preferences es: "+arrayDataLog);
        //*******************************//
        // Recupero los datos del Intent //
        //*******************************//
        if(getIntent().getExtras() != null) {
            t1Start = System.currentTimeMillis();
            contardorToques = getIntent().getIntExtra("toques", 2);  // numero de toques
            cajasportoque = getIntent().getIntExtra("cajas", 2);
            record = getIntent().getIntExtra("record",0);
            numerodeCajas = getIntent().getIntExtra("recordporcajas",1);
            otrojuego = getIntent().getIntExtra("otrojuego",0);
            jugadoractual = getIntent().getStringExtra("jugador");
        }
        arrayData.add(0,jugadoractual);  // almaceno el primer dato
        arrayData.add(1,"relleno");
        arrayData.add(2,"relleno");
        arrayData.add(3,"no"); // No -Por defecto este jugador no tiene el record
        Log.i("MIAPP","SplitViewCopia-onCreate-jugadorActual es: "+jugadoractual);

        // EJEMPLO CONVERSION JSON
        /**
        Gson gson = new Gson();
        String puntuacion_json = gson.toJson(p2);
        Log.i("MIAPP","JSON"+puntuacion_json);
        // para leer
        Log.i("MIAPP","El json recibido es : "+ p2);
        */

        // TODO por aqui voy
        //invalidateOptionsMenu();
        //logojugarahora.setVisible(false);

        //disableButtonFlagJugarAhora = true;

        //menu1.findItem(R.id.jugarahora).setEnabled(false);
        // menujuego = findViewById(R.id.jugarahora);
       // menujuego.setVisibility(View.INVISIBLE);
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

      //  logojugarahora = (MenuItem) menu.findItem(R.id.jugarahora);


        /**
        for(int i=0; i<menu.size(); i++){
            menu.getItem(i).setEnabled(jugarahora);
        }

        if (disableButtonFlagJugarAhora == true){
            menu.findItem(R.id.jugarahora).setEnabled(false);
        }
        */
        return super.onCreateOptionsMenu(menu);


    }


    // RECIBE EL EVENTO DEL MENU ELEGIDO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.resultados:
                // mostrar en un nuevo layout el resultado
                //this.finish();
                Intent intentresultados = new Intent(SplitViewCopia.this, Resultados.class);
                intentresultados.putStringArrayListExtra("resultados",arrayDataLog);
                startActivity(intentresultados);
                break;
            case R.id.jugarahora:
                // Ahora tiene que coger la pantalla actual con el numero de cajas que contenga
                // y a partir de aqui se puede jugar a rellenarlas de color negro.
                // si se pulsa 2 veces se pone de color blanco
                // El objetivo es rellenar todas las casillas de negro y mostrara si hay record o no.
                jugarahora = true;
                //quitar onclick de todos las cajas
                // recorrer y crear nuevo onclick,,con el nuevo link al metodo
                // empiezo a recorrer desde el primero .--llamado root
                vista = findViewById(R.id.root);
                recorrerLayouts(vista);
                inicio = true;
                arrayData.remove(2);
                arrayData.add(2,(Integer.toString(numerodeCajas)));
                iniciar();
                //marcarColor(vista);

                break;
                /**
                this.finish();
                Intent intent4 = new Intent(SplitViewCopia.this,CajaColor.class);
                intent4.clone();
                Intent intent3 = new Intent(SplitViewCopia.this, CajaColor.class);
                //contardorToques = getIntent().getIntExtra("toques",2);  // numero de toques
                //cajasportoque = getIntent().getIntExtra("cajas",2);

                intent4.putExtra("jugarpartida", 10);

                intent3.clone().getClass();
                startActivity(intent4);
                break;
                 */
            case R.id.pause:
                // Paramos la ejecucion del juego , el contador de juego debe pausarse tambien
                // startActivity(new Intent(this, SplitView.class));
                otrojuego = 0;
                pause();
                break;

            case R.id.reiniciar:
                // Si el Juego estaba en modo pausa ,,entonces continuar , el contador se restablece
                // con el valor pausado anteriormente
                //startActivity(new Intent(this, EjercicioPresionarColoresVersion1.class));
                reanudar();
                break;

            case R.id.reiniciarpartida:
                // Se para la ejecucion del juego actual y se lanza de nuevo desde el inicio
                //startActivity(new Intent(this, CajaColor.class));
                /**
                this.finish();
                Intent intent = new Intent(SplitViewCopia.this, SplitViewCopia.class);
                //contardorToques = getIntent().getIntExtra("toques",2);  // numero de toques
                //cajasportoque = getIntent().getIntExtra("cajas",2);
                intent.putExtra("toques", contardorToques);
                intent.putExtra("cajas", cajasportoque);
                intent.putExtra("jugador",jugadoractual);
                startActivity(intent);
                 */
                Log.i("MIAPP","onOptionsItemSelected-Toco version Reiniciar Partida");
                break;
            case R.id.finalizar:
                // Se para la ejecucion del juego actual y se lanza de nuevo desde el inicio
                //startActivity(new Intent(this, CajaColor.class));
                this.finish();
                Intent intent1 = new Intent(SplitViewCopia.this, TiempoTranscurrido.class);
                //contardorToques = getIntent().getIntExtra("toques",2);  // numero de toques
                //cajasportoque = getIntent().getIntExtra("cajas",2);
                intent1.putExtra("duracionensegundos",Texto2 );
                startActivity(intent1);
                Log.i("MIAPP","onOptionsItemSelected-Toco version finalizar");
                break;


            default: Log.i("MIAPP","onOptionsItemSelected-Raro...raroooooo");
        }


        return super.onOptionsItemSelected(item);
    }

    private void iniciar() {
        t1Start = System.currentTimeMillis();
        vista = findViewById(R.id.root);
        texto = findViewById(R.id.txtpausar);
        boton = findViewById(R.id.btnContinuar);
        texto.setText("¿ Listo para empezar el Juego ?");
        boton.setText("Iniciar Juego");
        vista.setVisibility(View.INVISIBLE);
        texto.setVisibility(View.VISIBLE);
        boton.setVisibility(View.VISIBLE);
        Log.i("MIAPP","SplitViewCopia-iniciar()");
    }




    private void pause() {
        t1Pause = System.currentTimeMillis();

            if (t1Duraciont1Startt1Pause == 0) {
                t1Duraciont1Startt1Pause = (t1Pause - t1Start) / 1000d;  // en segundos

            } else {
                t1Duraciont1Startt1Pause = ((t1Pause - t1Start) / 1000d) + t1Duraciont1Startt1Pause;
            }
            Log.i("MIAPP", "SplitViewCopia-pause()-tiempo de juego : " + t1Duraciont1Startt1Pause);

            Texto = String.format("Duracion en segundos de la App es: %1$.3f", t1Duraciont1Startt1Pause);
            Texto2 = Texto + " Segundos";
            Log.i("MIAPP", "tiempo de juego : " + Texto2);
            vista = findViewById(R.id.root);
            texto = findViewById(R.id.txtpausar);
            boton = findViewById(R.id.btnContinuar);
            vista.setVisibility(View.INVISIBLE);
            texto.setText("Juego pausado, si desea continuar pulse continuar");
            boton.setText("CONTINUAR");
            texto.setVisibility(View.VISIBLE);
            boton.setVisibility(View.VISIBLE);

    }

    private void reanudar () {
        if (num_veces != numerodeCajas) {
            t1Start = System.currentTimeMillis();
            vista = findViewById(R.id.root);
            texto = findViewById(R.id.txtpausar);
            boton = findViewById(R.id.btnContinuar);
            vista.setVisibility(View.VISIBLE);
            texto.setVisibility(View.INVISIBLE);
            boton.setVisibility(View.INVISIBLE);
            Log.i("MIAPP", "SplitViewCopia-reanudar()-Toco version reniciar");
        }
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

    private void recorrerLayouts(View vista)
    {
       // Log.d(getClass().getCanonicalName(), vista.getClass().getCanonicalName());
        if (vista instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) vista;
            for (int i = 0; i<viewGroup.getChildCount(); i++)
            {

                View vistahija = viewGroup.getChildAt(i);
                vistahija.setClickable(false);
                vistahija.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {marcarColor(v); }});
                recorrerLayouts(vistahija);
            }
        }
    }

    private void recorrerLayoutsycolorear(View vista)
    {
        // Log.d(getClass().getCanonicalName(), vista.getClass().getCanonicalName());
        if (vista instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) vista;
            for (int i = 0; i<viewGroup.getChildCount(); i++)
            {

                View vistahija = viewGroup.getChildAt(i);
                vistahija.setBackgroundColor( randomColor());
                vistahija.setClickable(false);
                vistahija.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {marcarColor(v); }});
                recorrerLayoutsycolorear(vistahija);

            }
        }
    }




    public void dividir(View view) {

        if (jugarahora == false) {
            // LA FORMULA ES: (CONTADOR -1)+cajasportoque
            numerodeCajas = (numerodeCajas -1) + cajasportoque;

            Log.i("MIAPP", "SplitViewCopia-dividir()-Contador de cuadros  es: " + numerodeCajas);
            //limite(contador1);
            LinearLayout padre = (LinearLayout) view;
            for (int i = 0; i < cajasportoque; i++) {   //  numero de cuadros por toque
                LinearLayout hijo = crearHijo(padre.getOrientation());
                padre.addView(hijo);
            }
        }else {

            int id = view.getId();
            Log.i("MIAPP","SplitViewCopia-dividir - getId seleccionado es: "+ id);
            setContentView(R.layout.activity_caja_color);
            inicio = true;
            marcarColor(view);
            // mainActivity.marcarColor(view);

        }
        /**
        LinearLayout hijo1 = crearHijo(padre.getOrientation());
        padre.addView( hijo1);
        LinearLayout hijo2 = crearHijo(padre.getOrientation());
        padre.addView( hijo2);
        */
    }


    public void marcarColor(View view) {
        // Si ya se ha pulsado el Boton Inicio entonces --true
        // eso quiere decir que empezamos a poder cambiar cajas de colores

        //id = view.findViewById(R.id.bloque1);
        LinearLayout x = (LinearLayout) view;
        Log.i("MIAPP","SplitViewCopia--marcarColor(view) -inicio es : "+ inicio);
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
            Log.i("MIAPP", "SplitViewCopia--marcarColor(view)-array momentaneo ids es: " + ids);
            Log.i("MIAPP", "SplitViewCopia--marcarColor(view)-el linear pulsado es " + x.getId());

            if (bloqueclick == false) {
                //view.setBackgroundColor(Color.red(100000));
                view.setBackgroundColor(getResources().getColor(R.color.negro));
                bloqueclick = true;
                Log.i("MIAPP", "SplitViewCopia--marcarColor(view)-numero de veces " + num_veces);
                if (num_veces == numerodeCajas) {
                    // ahora tiene que preguntar si quieres jugar otra vez y si debe
                    // mostrarte el tiempo actual y el record ( si hay nuevo lo indicara)
                    otraVez();
                }
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.blanco));
                bloqueclick = false;
                Log.i("MIAPP", "SplitViewCopia--marcarColor(view)-numero de veces " + num_veces);
            }

        } else {
            // No hay que hacer nada aun . Esperar a que el boton INICIAR se presione
        }

        //view.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { marcarColor(v); }});
    }


    private LinearLayout crearHijo (int orientacion)
    {
        LinearLayout nueva_caja = null;
        LinearLayout.LayoutParams parametros = null;

        nueva_caja = new LinearLayout(this);
        nueva_caja.setId(newId());
        int id_creado = nueva_caja.getId();
        Log.i("MIAPP","SplitViewCopia -crearHijo- , getid creado es :"+id_creado);

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
        if (this.contador1 > contardorToques){
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
        vista = findViewById(R.id.root);
        texto = findViewById(R.id.txtpausar);
        boton = findViewById(R.id.btnContinuar);
        // en caso de reniciar la partida y volver a jugar el boton tiene escrito:
        //   --Para volver a jugar : "OTRA PARTIDA?
        //if (texto.getText().toString().contains("Otra Partida")) {
        Log.i("MIAPP","SplitCopia-Reiniciar-otrojuego: " +otrojuego);
        if (otrojuego == 1){
            // tenemos que empezar otra partida con los mismos cuadros
            /**
            this.finish();

            Intent intent4 = new Intent(SplitViewCopia.this,SplitViewCopia.class);
            //contardorToques = getIntent().getIntExtra("toques",2);  // numero de toques
            //cajasportoque = getIntent().getIntExtra("cajas",2);
            intent4.putStringArrayListExtra("datosalamcenados",arrayDataLog);
            intent4.putExtra("record",record);
            intent4.putExtra("recordporcajas",numerodeCajas);
            intent4.putExtra("toques",contardorToques);
            intent4.putExtra("cajas",cajasportoque);
            intent4.putExtra("otrojuego",otrojuego);
            startActivity(intent4);
*/
            jugarahora = true;
            //quitar onclick de todos las cajas
            // recorrer y crear nuevo onclick,,con el nuevo link al metodo
            // empiezo a recorrer desde el primero .--llamado root
            vista = findViewById(R.id.root);
            Log.i("MIAPP","SplitViewCopia-Reiniciar-antes llamar a recorrerLayoutycolorear");
            recorrerLayoutsycolorear(vista);
            inicio = true;
            t1Start = System.currentTimeMillis();
            t1Duraciont1Startt1Pause = 0;
            vista = findViewById(R.id.root);
            texto = findViewById(R.id.txtpausar);
            boton = findViewById(R.id.btnContinuar);
            nombrejugador= findViewById(R.id.editnombrejugador);
            nombrejugador.setVisibility(View.INVISIBLE);
            texto.setText("¿ Listo para empezar el Juego ?");
            boton.setText("Iniciar Juego");
            vista.setVisibility(View.VISIBLE);
            texto.setVisibility(View.INVISIBLE);
            boton.setVisibility(View.INVISIBLE);
            ids.clear();
            num_veces = 0;


        }else {
            // continuamos con el procedimiento normal


            // hay que poner el procedimiento de poner invisible text y boton
            // despues se retoma la activity y ya se puede continuar jugando
            // ademas hay que restablecer los contadores de tiempo
            t1Start = System.currentTimeMillis();

            vista.setVisibility(View.VISIBLE);
            texto.setVisibility(View.INVISIBLE);
            boton.setVisibility(View.INVISIBLE);
        }
    }

    public void otraVez () {
        // hay que poner el procedimiento de poner invisible text y boton
        // despues se retoma la activity y ya se puede continuar jugando
        // ademas hay que restablecer los contadores de tiempo
        otrojuego = 1;
        t1Pause = System.currentTimeMillis();
        record =0;
        if (t1Duraciont1Startt1Pause == 0) {
            t1Duraciont1Startt1Pause = (t1Pause - t1Start)/1000d;  // en segundos

        }else {
            t1Duraciont1Startt1Pause = ((t1Pause - t1Start)/1000d) + t1Duraciont1Startt1Pause;
        }
        Log.i("MIAPP","SplitViewCopia-otraVez()-tiempo de juego : "+ t1Duraciont1Startt1Pause);

        // calcula si hay o no record y devuelve record 1 o 0
        calcularRecordPorJuego(numerodeCajas,jugadoractual,t1Duraciont1Startt1Pause);

        /**
        if (recordjuego == 0) {
            // PRIMERA PARTIDA - HAY RECORD
            recordjuego = t1Duraciont1Startt1Pause;
            record = 1;

        } else {
            // VER SI HAY NUEVO RECORD
            if (t1Duraciont1Startt1Pause < recordjuego) {
                // HAY RECORD
                recordjuego = t1Duraciont1Startt1Pause;
                record = 1;
            } else {
                // CONTINUA EL RECORD ANTERIOR

            }
        }
         */
        arrayData.remove(3);
        arrayData.add(3,"no"); // no -indica que este jugador no tiene el record
        if (record == 1){
            //jugadorRecord = jugadoractual;
            guardarJugador();
        }

        mostrarRecord(t1Duraciont1Startt1Pause,record,numerodeCajas);

    }

    public void guardarJugador() {

        Log.i("MIAPP","SplitViewCopia-guardarJuagador-jugadorRecord es :"+jugadorRecord);
        // primero defino una nueva
        // JUGADOR = "Jugadornombre"
        // JUGADORCLAVE = "Jugadornombreclave"
        SharedPreferences prefs = getSharedPreferences(JUGADOR,MODE_PRIVATE);
        // Entro en modo Edicion
        SharedPreferences.Editor editor = prefs.edit();
        // Asigno una clave para acceder poner la informacion que quiero
        //   CLAVE <-----recordjuego
        editor.putString(JUGADORCLAVE,  jugadorRecord);
        editor.commit();
        arrayData.remove(3);
        arrayData.add(3,"yes"); // yes -indica que este jugador tiene record

    }

    private void mostrarRecord(double t1Duraciont1Startt1Pause, int record, int numerodeCajas) {
        //***************************************************//
        //Actualizo texto a ver en layout y preparo locucion //
        //***************************************************//
        String recordString = "no";
        if (record == 1){
            // nuevo record
            sonido(record);
            Texto1 = String.format("Nuevo Record : %1$.3f",recordjuego);
            recordString = "yes";
        } else {
            sonido(record);
            Texto1 = String.format("Record actual : %1$.3f", recordjuego);
        }
        //*****************************************************************//
        //Convierto tiempo de Float a string para poderlo guardar en array //
        //*****************************************************************//
        String tiempoActualString = Float.toString((float) t1Duraciont1Startt1Pause);
        String numerocajasString = Integer.toString(numerodeCajas);
        //******************************//
        //Actualizo array con el tiempo //
        //******************************//
        arrayData.remove(1);
        arrayData.add(1, tiempoActualString); // Se Guarda el tiempo actual hecho
        // se pasan los datos de la partida actual al array historico de partidas
        // arrayDataLog <---arrayData(0-3)
        // como si fuera un fichero .csv separados por ,
        for (int i = 0;i<arrayData.size();i= i + 4){
            dato1 = arrayData.get(i);
            dato2 = arrayData.get(i+1);
            dato3 = arrayData.get(i+2);
            dato4 = arrayData.get(i+3);
            arrayDataLog.add(dato1+","+dato2+","+dato3+","+dato4+"\n");
        }
        Log.i("MIAPP","SplitViewCopia-mostrarRecord()-arrayData es :"+arrayData);
        Log.i("MIAPP","SplitViewCopia-mostrarRecord()-arrayDataLog es :"+arrayDataLog);
        //*************************************//
        // Guardar partida en clase Puntuacion //
        //*************************************//
                // PARA ASIGNAR id - AL CERRAR LA APP Y VOLVERLA A CREAR EL id empezaria desde 0
                // y ese valor se volveria a repetir dando error .
                // todo leer el ultimo id usado en la tabla
                // primero ver si la tabla ya esta definida y tal caso leer el ultimo usado.
        Puntuacion.setTop(top10);
        Puntuacion nuevojuego = new Puntuacion();
        //nuevojuego.setId(id);
        nuevojuego.setNombre(dato1);
        nuevojuego.setTiempo(dato2);
        nuevojuego.setJuego(dato3);
        nuevojuego.setRecord(dato4);
        top10.add(nuevojuego);
        //id = id + 1;
        //***************************************************//
        // Guardar record actual puntuacion1 en Preferences  //
        //***************************************************//
        SharedPreferences pref1 = getSharedPreferences("Serializados",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = pref1.edit();
            // convertir a JSON (hay libreria externa de gooble-GSON)
            // 1-importar la libreria- ir menu/build/editlibriryandDependencies
            //                          + libreryDependecies
            //                       busco gson y descargo la de google
            // declaro objeto gson
        Gson gson = new Gson();

            // guardo el Json como si fuera un string para poder pasarlo a las Preferences
        String puntuacion_json1 = gson.toJson(top10);
        editor1.putString("valornuevo",puntuacion_json1);
        editor1.commit();
        //************************************************************//
        // Guardar array total con todas las partidas en Preferences  //
        //************************************************************//
        SharedPreferences pref = getSharedPreferences("arraydatalog",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt( "size", arrayDataLog.size());
        for(int i=0;i<arrayDataLog.size();i++)
            editor.putString("Indice " + i, arrayDataLog.get(i));
        editor.commit();


        // prueba DB //
        // base de datos prueba//
        //private final static String sqlCreacionTablaPartida =
        //   "CREATE TABLE PARTIDA ( id INTEGER PRIMARY KEY,nombre TEXT,tiempo TEXT,juego TEXT,record TEXT)";


        //creo el objeto de la base de datos
        BaseDatosPuntuaciones baseDatosPuntuaciones = new BaseDatosPuntuaciones(this, "MiDB", null, 1);
        // inserto jugada en la tabla creada PARTIDA
        baseDatosPuntuaciones.insertarJugada(nuevojuego);

        //consulto el record = yes  ultimo y obtengo los datos jugador,tiempo,juego,record
        // y lo almaceno en otra table PARTIDARECORD
        // consulto cual es el record para mi juego -numeroCajas
        Puntuacion listajuegos = baseDatosPuntuaciones.buscarJuego("yes",dato3);
        Log.i("MIAPP","listajuegos es: "+listajuegos);
        //baseDatosPuntuaciones.insertRecord(listajuegos);


        //consulto los record
       // List<Puntuacion> listacoches = baseDatosPuntuaciones(nuevojuego);

        //*******************************************************//
        //Prepara y muestra texto con datos partida en el layout //
        //*******************************************************//
        prepararTexto();

       // SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
       // SharedPreferences.Editor editor = prefs.edit();
        // voy a subir un segundo preference como json
        // lo que se llama serializar
                 //Puntuacion puntuacion = new Puntuacion(dato1,dato2,dato3,dato4);
        //Puntuacion puntuacion = new Puntuacion(jugadoractual,tiempoActualString,numerocajasString,recordString);
               // String puntuacion_json = gson.toJson(puntuacion);
               //Log.i("MIAPP","JSON"+puntuacion_json);
        // para leer
               //Puntuacion p2 = gson.fromJson(puntuacion_json,Puntuacion.class);
               //Log.i("MIAPP","JSON.leido de p2"+p2);
        // para convertir a double
                //Double t1 = Double.parseDouble(p2.getTiempo().toString());
        //editor.putString("Indice " + i, arrayDataLog.get(i));


    }

    private void prepararTexto() {
        Texto = "Infinito_"+ this.numerodeCajas;
        // en Texto2 esta el record , sea el nuevo o el actual
        Texto2 = Texto1 + " Segundos";
        // en Texto3 esta el tiempo actual
        Texto3previo = String.format("Tiempo Actual  : %1$.3f",t1Duraciont1Startt1Pause);
        Texto3 = Texto3previo + " Segundos";
        Log.i("MIAPP","SplitViewCopia-mostrarRecord(.)- "+Texto2 );
        Log.i("MIAPP","SplitViewCopia-mostrarREcord(.)"+Texto3);
        Texto4 = "El record lo tiene el jugador: "+jugadorRecord;
        vista = findViewById(R.id.root);
        texto = findViewById(R.id.txtpausar);
        boton = findViewById(R.id.btnContinuar);
        vista.setVisibility(View.INVISIBLE);

        //para imprimir un array en un text view
        //      Collections.sort(arrayDataLog);
        //int z = arrayDataLog.size();
        //   for(int i = 0; i < z; i++) {
        //    texto.append(arrayDataLog.get(i));
        //}
        texto.setText(Texto+"\n"+Texto2+"\n"+Texto3+"\n"+Texto4);
        boton.setText("Otra Partida?");
        texto.setVisibility(View.VISIBLE);
        boton.setVisibility(View.VISIBLE);
    }

    private void calcularRecordPorJuego(int numerodeCajas, String jugadoractual, double t1Duraciont1Startt1Pause) {
        // Comprobar si hay alguna partida guardada - por seguridad
        int size = arrayDataLog.size();
        Log.i("MIAPP", "SplitViewCopia-calcularRecordporJuego-size es :" + size);
        record = 0;
        boolean buscar = false;
        if (size > 0) {
            // leer el arraydatalog y buscar si hay alguna partida para el juego-numerodeCajas
            //for (int i=0;i<size;i++){
            for (int i = size - 1; i > -1; i--) {
                if (arrayDataLog.get(i).toString().contains("," + Integer.toString(numerodeCajas) + ",")) {
                    if (arrayDataLog.get(i).toString().contains("yes")) {
                        //....leer el tiempo guardado para este index
                        String temporaldata = arrayDataLog.get(i);
                        String[] fields = temporaldata.split(",");
                        String timerecordantiguo = fields[1].toString();
                        //todo
                        //   con el tiempoactual(t1Durationt1Startt1Pause) y el tiempo anterior se verifica si hay
                        //         nuevo record
                        //        nuevo record = yes...entonces se marca variable record = 1
                        if (t1Duraciont1Startt1Pause < Double.parseDouble(timerecordantiguo)) {
                            // nuevo record
                            record = 1;
                            recordjuego = t1Duraciont1Startt1Pause;
                            jugadorRecord = jugadoractual;
                            Log.i("MIAPP", "SplitViewCopia-CalcularRecordporJuego-recordjuego--hay es :" + recordjuego);
                            Log.i("MIAPP", "SplitViewCopia-CalcularRecordporJuego-jugadorRecord-- es :" + jugadorRecord);

                        } else {
                            // no ha superado el record
                            recordjuego = Double.parseDouble((timerecordantiguo));
                            jugadorRecord = fields[0];
                            Log.i("MIAPP", "SplitViewCopia-CalcularRecordporJuego-recordjuego--antiguo es :" + recordjuego);

                        }
                        break;
                    } else {
                        // este caso es muy raro al menos un registro tendria que tener record
                        Log.i("MIAPP", "SplitViewCopia-calcularRecordporJuego-caso raro");
                    }

                } else {
                    // continua buscando en el array
                    // si ha llegado hasta el index 0 y record es 0, entonces hay que fijarlo a 1
                    if (i == 0) {
                        if (record == 0) {
                            record = 1;
                            recordjuego = t1Duraciont1Startt1Pause;
                            String temporaldata = arrayDataLog.get(i);
                            String[] fields = temporaldata.split(",");
                            jugadorRecord = jugadoractual;
                            //jugadorRecord = fields[0].toString();
                            Log.i("MIAPP", "El caso especial-calcularRecordPorJuego" + jugadorRecord);
                        }
                    }

                }

            }


        } else {
            // no hay nada registrado..esto quiere decir que es la primera partida
            record = 1;
            jugadorRecord = jugadoractual;
            recordjuego = t1Duraciont1Startt1Pause;
        }

    }

    private void sonido(int record){

        if (record == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.aguanta);
        }else {
            mediaPlayer = MediaPlayer.create(this,R.raw.ohno);
        }
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MIAPP","SplitViewCopia-onStop-recordjuego :"+recordjuego);
        // TODO guardar el record que jugadas
        // primero defino una nueva
        // RECORD = "RecordPartidaAnterior"
        // CLAVE = "RecordPartidaAnteriorClave"
        SharedPreferences prefs = getSharedPreferences(RECORD,MODE_PRIVATE);
        // Entro en modo Edicion
        SharedPreferences.Editor editor = prefs.edit();
        // Asigno una clave para acceder poner la informacion que quiero
        //   CLAVE <-----recordjuego
        editor.putFloat(CLAVE, (float) recordjuego);
        editor.commit();

    }
}
