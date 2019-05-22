package com.example.milinear;

import android.animation.ValueAnimator;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class IntentImplicito extends AppCompatActivity {
    private static final String URL_BUCADOR = "https://www.google.es/search?q=";

    // boton animado Fernando

    private int originalWidth;
    private static int DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_intent_implicito);

        // boton animado Fernando
        setContentView(R.layout.activity_intent_implicito_boton_animado);

    }


    // para activity_intent_implicito.xml ...buscar
    public void buscarx(View view) {
        EditText texto = findViewById(R.id.txtabuscar);
        String textoabuscar = texto.getText().toString();
        String cadenadebusqueda = URL_BUCADOR + textoabuscar;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cadenadebusqueda));
        startActivity(intent);


    }

    /**
     * https://www.google.es/search?source=hp&ei=Z9PjXLzeJo-UlwSC0ZnYCQ&q=perro&oq=perro&gs_l=psy-ab.12..0l10.4352.5144..5414...0.0..0.267.889.3j1j2......0....1..gws-wiz.....0..35i39j0i131j0i3.sRMxOAnkU_Y
     */

    // VER TODOS LOS INTENT IMPLICITOS EN LA DOCUMENTACION DE GOOGLE.
    // https://developer.android.com/guide/components/intents-common?hl=es-419


    // para activity_intent_implicito.xml ...buscar1
    public void buscar1(View view) {
        EditText texto = findViewById(R.id.txtabuscar);
        String textoabuscar = texto.getText().toString();
        String cadenadebusqueda = URL_BUCADOR + textoabuscar;

        Intent intent1 = new Intent(Intent.ACTION_WEB_SEARCH);
        intent1.putExtra(SearchManager.QUERY, cadenadebusqueda);
        if (intent1.resolveActivity(getPackageManager()) != null) {
            startActivity(intent1);
        }

    }


    // para la version de Fernando .
    // activity_intent_implicito_boton_aminado.xml
    public void buscar (View view) {
        final EditText editText = findViewById(R.id.editText);

        if (editText.getWidth() == originalWidth) { // Debo animar el expand
            final TextView textView = findViewById(R.id.textViewLabel);
            final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
            ValueAnimator anim = ValueAnimator.ofInt(editText.getWidth(), textView.getWidth());
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    lparams.width = val;
                    editText.setLayoutParams(lparams);
                }
            });

            anim.setDuration(DURATION);
            anim.start();
        } else {
            String texto = editText.getText().toString();
            if (texto.isEmpty()) {
                final ConstraintLayout.LayoutParams lparams = (ConstraintLayout.LayoutParams) editText.getLayoutParams();
                ValueAnimator anim = ValueAnimator.ofInt(editText.getWidth(), originalWidth);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        lparams.width = val;
                        editText.setLayoutParams(lparams);
                    }
                });
                anim.setDuration(DURATION);
                anim.start();

            } else {
                Log.d("MYAPP", Integer.toString(editText.getWidth()) + " Expandido");
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, texto);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        }
    }

}
