package com.example.milinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class ElegirJuego extends AppCompatActivity {

    String NOMBRES [] = {"Elegir juego","Infinito","Otro juego" };
    private boolean isSpinnerInitial = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_juego);
        Log.i("MIAPP","Estoy en onCreate : ");

        final Spinner spinner = (Spinner) findViewById(R.id.mispinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NOMBRES);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MIAPP","la posicion selecciona es : " +i);
                String juego = (String) spinner.getAdapter().getItem(i);
                if (isSpinnerInitial == false) {
                    if (juego.contains("Infinito")) {
                        startActivity(new Intent(ElegirJuego.this, InfinitoPrevio.class));
                        spinner.setSelection(0);
                        Log.i("MIAPP","el valor de isSpinnerInitial es : "+ isSpinnerInitial);
                    }else {
                        if (juego.contains("Elegir")){
                            // No hacer nada
                        }else {
                            startActivity(new Intent(ElegirJuego.this, CajaColor.class));
                        }
                    }
                }else {
                    isSpinnerInitial = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MIAPP","Estoy en onRestoreInstance : ");
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.i("MIAPP", "TOCADO " + pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}




