package com.example.milinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TiempoTranscurrido extends AppCompatActivity {
    String tiempotranscurrido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo_transcurrido);

        tiempotranscurrido =getIntent().getStringExtra("duracionensegundos");  // numero de toques

        TextView tiempo = findViewById(R.id.tiempoensegundos);
        tiempo.setText(tiempotranscurrido);
    }


    public void volver(View view) {

        this.finish();

    }
}
