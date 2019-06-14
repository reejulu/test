package com.example.milinear;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.SectionIndexer;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Resultados extends AppCompatActivity {
    ArrayList<String> arrayDataLog = new ArrayList<String>();
    ArrayList<String> arrayDataLogtemp = new ArrayList<>();
    String jugador;
    String juego;
    String tiempo;
    String record;
    Double tiempod;
    String NOMBRES [] = {"Todos","Jugador","Juego-Record", "Juego"};
    boolean juegoset = false;
    String oldrecord = null;
    String juegotmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        // intentresultados.putStringArrayListExtra("resultados",arrayDataLog);
        arrayDataLog = getIntent().getStringArrayListExtra("resultados");
        // formato del array recibido es:
        // Jugador,tiempo , Juego, record?


        final Spinner spinner = (Spinner) findViewById(R.id.mispinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NOMBRES);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MIAPP","la posicion selecciona es : " +i);
                String juego = (String) spinner.getAdapter().getItem(i);

                    //   String NOMBRES [] = {"Todos","Jugador","Juego-Record", "Juego"};
                    switch (juego) {
                        case "Todos":
                            filtar(0);
                            break;
                        case "Jugador":
                            filtar(1);
                            break;
                        case "Juego-Record":
                            filtar(2);
                            break;
                        case "Juego":
                            filtar(3);
                            break;
                        default:    // "Elegir"
                            break;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        TextView texto = findViewById(R.id.txtresultados);
        //para imprimir un array en un text view

      //  Collections.sort(arrayDataLog, new Sortbyroll());
        /**
        Collections.sort(this.arrayDataLog, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
               String [] m = s.split(",");
                String [] m1 = t1.split(",");
                Double md = Double.parseDouble(m[1]);
                Double m1d = Double.parseDouble(m1[1]);

                Log.i("MIAPP","muestar s es: "+s);
                return new Integer(md.compareTo(m1d));
            }
        });
        */
        int size = arrayDataLog.size()-1;
        Collections.sort(arrayDataLog);
        int w = arrayDataLog.size()-1;
        for (int x = w; x >-1;x--){
            String data = arrayDataLog.get(x).toString();
            String [] campos = data.split(",");
            jugador = campos[0];
            tiempo = campos[1];
            juego = campos[2];
            record = campos[3];

        }


        //Collections.sort(arrayDataLog,Collections.<String>reverseOrder());

        texto.setText("");
        int z = arrayDataLog.size();
           for(int i = 0; i < z; i++) {
            texto.append(arrayDataLog.get(i));
        }

    }

    public void filtar(int menu){
        // "Todos","Jugador","Juego-Record", "Juego"}
        // menu 0,   1      ,   2           ,   3
        Log.i("MIAPP","arrayDataLog (antes de filtrar) es: " +arrayDataLog);
        int size = arrayDataLog.size()-1;
        //Collections.sort(arrayDataLog);
        int w = arrayDataLog.size()-1;
        TextView texto = findViewById(R.id.txtresultados);
        String menusalida = ""+"\n";
        texto.setText(menusalida);
        juegoset = false;
       // texto.setText(Texto+"\n"+Texto2+"\n"+Texto3+"\n"+Texto4);
        for (int x = w; x >-1;x--){
            String data = arrayDataLog.get(x).toString();
            String [] campos = data.split(",");
            jugador = campos[0];
            tiempo = campos[1];
            juego = campos[2];
            record = campos[3];
            switch (menu) {
                case 0: // Todos
                    menusalida = "Jugador :\""+ jugador +"\",Juego_"+juego + "-tiempo : "+tiempo + " Seg. \n";
                    texto.append(menusalida);
                    break;
                case 1: // Jugador
                    menusalida = "Jugador : "+ jugador +" // Juego_"+juego + " Tiempo : "+tiempo + " Segundos \n";
                    texto.setText(menusalida);
                    break;
                case 2: // Juego-Record

                    boolean hay = false;  // hay que añadir
                    if (record.contains("yes")) {

                        if (arrayDataLogtemp.size() == 0) { // el primer record -entonces almacenar registro
                            //menusalida = "Jugador : " + jugador + " // Juego_" + juego + " Tiempo : " + tiempo + " Seg. " + record;

                            arrayDataLogtemp.add(data);
                            Log.i("MIAPP","arrayDataLogtemp -if size = 0 es: "+arrayDataLogtemp);
                            //  texto.append(menusalida);
                            //  juegoset = true;
                            //  break;
                        } else {
                            // como ya hay algun juego con record almacenado ,,compruebo que no lo tenga ya
                            for (int s = 0; s < arrayDataLogtemp.size(); s++) {
                                if (arrayDataLogtemp.get(s).toString().contains("Juego_" + juego)) {
                                    // hay = true; // ya esta --no hay que añadirlo
                                    // break;
                                } else {
                                    //menusalida = "Jugador : " + jugador + " // Juego_" + juego + " Tiempo : " + tiempo + " Seg. " + record;
                                    arrayDataLogtemp.add(data);
                                    Log.i("MIAPP","arrayDataLogtemp -if size > 0 es: "+arrayDataLogtemp);
                                    break;
                                }
                            }
                        }

                        // menusalida = "Jugador : " + jugador + " // Juego_" + juego + " Tiempo : " + tiempo + " Segundos" + record ;
                        //texto.append(menusalida);
                        //break;
                    }
                    break;

                case 3: // Juego
                    menusalida = "Jugador : "+ jugador +" // Juego_"+juego + " Tiempo : "+tiempo + " Segundos \n";
                    texto.setText(menusalida);
                    break;
                default:
            }

        }
        // en este momento arrayDataLogtemp tiene la lista de los marcados con yes.
        // hay que dejar solo el primero encontrado de diferente juego .
        int v = arrayDataLogtemp.size();
        if (v > 0) {
            texto.setMovementMethod(new ScrollingMovementMethod());
            texto.append("   Mejores Tiempos por Juego  \n");
            for (w = v-1; w > -1; w--) {
                String data = arrayDataLogtemp.get(w).toString();
                String[] campos = data.split(",");
                jugador = campos[0];
                tiempo = campos[1];
                juego = campos[2];
                record = campos[3];
                menusalida = "Jugador: " + jugador + ",Juego_" + juego + ",tiempo: " + tiempo + " Seg. " + record;
                if (w == v-1) {
                    texto.append("    "+menusalida);
                    juegotmp = juego;
                } else {
                    if (data.toString().contains("," + juegotmp +",")) {
                        // Ya esta el record para este juego no hay que añadirlo
                    } else {
                        // añadir el record para este juego
                        texto.append(menusalida);
                        juegotmp = juego;
                    }
                }
            }
        }
        arrayDataLogtemp.clear();
    }


    public void volveramenu(View view) {
        //onBackPressed();
        finish();
        Intent List = new Intent(getApplicationContext(), SplitViewCopia.class);
        startActivity(List);
    }

    /**
     // ejemplo filtering --a Estudiar
     ArrayList<Student> ar = new ArrayList<Student>();
     ar.add(new Student(111, "bbbb", "london"));
     ar.add(new Student(131, "aaaa", "nyc"));
     ar.add(new Student(121, "cccc", "jaipur"));


     for (int i=0; i<ar.size(); i++)
     System.out.println(ar.get(i));
     Log.i("MIAPP","ar es :"+ ar);
     //Collections.sort(ar, new Sortbyroll());

     System.out.println("\nSorted by rollno");
     for (int i=0; i<ar.size(); i++)
     System.out.println(ar.get(i));
     Log.i("MIAPP","ar es :"+ ar);
     */

/**
    //pruebas
    // A class to represent a student.
    class Student
    {
        int rollno;
        String name, address;

        // Constructor
        public Student(int rollno, String name,
                       String address)
        {
            this.rollno = rollno;
            this.name = name;
            this.address = address;
        }

        // Used to print student details in main()
        public String toString()
        {
            return this.rollno + " " + this.name +
                    " " + this.address;
        }
    }

    class Sortbyroll implements Comparator<Student>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Student a, Student b)
        {
            return a.rollno - b.rollno;
        }
    }
*/

class Jugada
{

    String jugador, tiempo,juego,record;

    // Constructor
    public Jugada(String jugador, String tiempo,
                   String juego,String record)
    {
        this.jugador = jugador;
        this.tiempo = tiempo;
        this.juego = juego;
        this.record = record;
    }

    // Used to print student details in main()
    public String toString()
    {
        return this.jugador + " " + this.juego +
                " " + this.tiempo;
    }
}

    class Sortbyroll implements Comparator<Jugada>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Jugada a, Jugada b)
        {
            return   a.juego.compareTo(b.juego);
        }
    }


}
