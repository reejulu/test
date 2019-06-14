package com.example.milinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;



public class InfinitoPrevio extends AppCompatActivity {
     NumberPicker numberPicker;
    NumberPicker numberPicker1;
    Button continuar;
    EditText editText;
    String jugador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinito_previo);
        // DEFINO BOTON CONTINUAR
        continuar = findViewById(R.id.continuarsi);

        // DEFINO NUMBERPICKER - PARA TOQUES Y NUMERO DE CAJAS POR CLICK
        numberPicker = findViewById(R.id.edit_toques);
        numberPicker1 = findViewById(R.id.edit_cajas);

        //      DEFINO RANGO DE VALORES
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(40);
        numberPicker.setValue(10); // valor por defecto
        numberPicker1.setMinValue(2);
        numberPicker1.setMaxValue(10);
        numberPicker1.setValue(3); // valor por defecto
        //      EJECUTO EL NUMBERPICKER
        numberPicker.setOnValueChangedListener(onValueChangeListener);
        numberPicker1.setOnValueChangedListener(onValueChangeListener1);

        editText = findViewById(R.id.jugadornombre);


    }

NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

/*/
            Intent intent = new Intent(InfinitoPrevio.this, SplitViewCopia.class);
            intent.putExtra("toques", numberPicker.getValue());
            InfinitoPrevio.this.finish();
            startActivity(intent);
*/
        }

    };

    NumberPicker.OnValueChangeListener onValueChangeListener1 = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker1, int i, int i1) {

        }

    };


    public void Proseguir(View view) {
        Intent intent = new Intent(InfinitoPrevio.this, SplitViewCopia.class);
        intent.putExtra("toques", numberPicker.getValue());
        intent.putExtra("cajas", numberPicker1.getValue());
        jugador = editText.getText().toString();
        intent.putExtra("jugador",jugador);
        InfinitoPrevio.this.finish();
        startActivity(intent);
    }
};


