package com.example.milinear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnviarWhatsapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_whatsapp);

    }

    public void EnviarWhatsap(View view) {

        EditText texto = findViewById(R.id.txtabuscar);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+ texto);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

        // segunda forma de hacerlo
     //   sendIntent.setPackage("com.whatsapp");
     //   startActivity(sendIntent);
    }
}
