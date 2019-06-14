package com.example.milinear;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BaseDatosPuntuaciones extends SQLiteOpenHelper {

    private final static String sqlCreacionTablaPartida = "CREATE TABLE PARTIDA ( id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,tiempo TEXT,juego TEXT,record TEXT)";
    private final static String sqlCreacionTablaPartidaRecord = "CREATE TABLE IF NOT EXISTS PARTIDARECORD ( id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,tiempo TEXT,juego TEXT,record TEXT)";
    String juegotmp;
    public BaseDatosPuntuaciones(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreacionTablaPartidaRecord);
        sqLiteDatabase.execSQL(sqlCreacionTablaPartida);

    }

    public void insertarJugada (Puntuacion jugada)
    {
       // database.execSQL("INSERT INTO PERSONA (id, nombre) VALUES ("+ persona.getId()+" , '"+ persona.getNombre()+"')");
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO PARTIDA (nombre,tiempo,juego,record)  VALUES ( '"+ jugada.getNombre().toString()+"' , '"+ jugada.getTiempo().toString()+"' , '"+ jugada.getJuego().toString()+"','"+ jugada.getRecord().toString()+"' )");

        //database.execSQL("INSERT INTO PARTIDA  VALUES ("+ jugada.getId()+" , '"+ jugada.getNombre().toString()+"' , '"+ jugada.getTiempo().toString()+"' , '"+ jugada.getJuego().toString()+"','"+ jugada.getRecord().toString()+"' )");
        this.cerrarBaseDatos(database);
    }

    public void insertRecord (Puntuacion jugada)
    {
        // database.execSQL("INSERT INTO PERSONA (id, nombre) VALUES ("+ persona.getId()+" , '"+ persona.getNombre()+"')");
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO PARTIDARECORD (nombre,tiempo,juego,record)  VALUES ( '"+ jugada.getNombre().toString()+"' , '"+ jugada.getTiempo().toString()+"' , '"+ jugada.getJuego().toString()+"','"+ jugada.getRecord().toString()+"' )");

        //database.execSQL("INSERT INTO PARTIDA  VALUES ("+ jugada.getId()+" , '"+ jugada.getNombre().toString()+"' , '"+ jugada.getTiempo().toString()+"' , '"+ jugada.getJuego().toString()+"','"+ jugada.getRecord().toString()+"' )");
        this.cerrarBaseDatos(database);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // buscar el string dado - que puede ser cualquiera de las cuatro columnas de la DB PARTIDA
    //     I.E buscar el nombre del juego en la clase Puntuacion
    //    private final static String sqlCreacionTablaPartida =
    //    "CREATE TABLE PARTIDA ( id INTEGER PRIMARY KEY,nombre TEXT,tiempo TEXT,juego TEXT,record TEXT)";
    public Puntuacion buscarJuego (String name,String name1)
    {
        Puntuacion juego = null;
        int aux_id = -1;
        String nombre_aux = null;
        String jugador= null;
        String tiempo= null;
        String juego1= null;
        String record = null;
        //("SELECT * FROM TABLENAME WHERE name LIKE'%?%'",(Variable,))
        String consulta = "SELECT * FROM PARTIDA WHERE record LIKE ('%name%')";
        String consulta2 = "SELECT * FROM PARTIDA WHERE record LIKE '%"+name+"%';";
        String consulta2distinct = "SELECT DISTINCT * FROM PARTIDA WHERE record LIKE '%"+name+"%';";
        String consulta3 = "SELECT * FROM (PARTIDA WHERE record LIKE '%"+name+"%'AND WHERE juego LIKE '%"+name1+"%';)";

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta2, null);
            int count = 0;
            int count1 = cursor.getCount();
            if (cursor != null && cursor.getCount()  > 0) {
                        cursor.moveToLast();
                        //juegotmp = cursor.getString(3); // guardo el juego en variable temporal
                        for (int i = 0;i< count1;i++) {
                            String p = cursor.getString(3);
                            if (p.equals(name1)) {
                                aux_id = cursor.getInt(0); //la posicion primera, el id
                                jugador = cursor.getString(1); //la posicion segunda, el id
                                tiempo = cursor.getString(2);
                                juego1 = cursor.getString(3);
                                record = cursor.getString(4);
                                juego = new Puntuacion(aux_id, jugador, tiempo, juego1, record);
                                Log.i("MIAPP", "dentro -buscarjuego- juego es :" + juego);
                                insertRecord(juego);
                                break;
                                // solo voy a coger el record final por eso lo he interrumpido
                                //cursor.moveToPrevious();
                            } else {
                                cursor.moveToPrevious();
                            }
                        }
                    }

                //cursor.close();

        cursor.close();
        this.cerrarBaseDatos(basedatos);

        return juego;
    }

    public List<Puntuacion> buscaridultimo (Puntuacion persona)
    {
        List<Puntuacion> lista_coches = null;
        Puntuacion coche = null;
        int aux_id = -1;
        String modelo = null;


        String consulta = "SELECT id FROM PARTIDA WHERE id ="+persona.getId();

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();
            lista_coches = new ArrayList<Puntuacion>(cursor.getCount());

            do
            {

                modelo = cursor.getString(0); //la posicion primera, el id
                coche = new Puntuacion();
                lista_coches.add(coche);

            }while (cursor.moveToNext());

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);
        return lista_coches;
    }

    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }
}
