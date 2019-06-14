package com.example.milinear;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasUsuario {
    //nombre fichero
    private static final String RECORD = "RecordPartidaAnterior";
    // clave
    private static final String CLAVE = "RecordPartidaAnteriorClave";

    public String leerTexto ()
    { String cadenaleida = null;

    return cadenaleida;
    }

    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }


    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

}
