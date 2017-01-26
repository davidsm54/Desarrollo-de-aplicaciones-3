package com.davidsm54.ejemplo_002;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Recomendacion extends AppCompatActivity {
    TextView txVDatos;
    TextView txVRecomendacion;
    String imagen = "";
    String recurso = "drawable";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacion);

        txVDatos = (TextView) findViewById(R.id.txVDatos);
        txVRecomendacion = (TextView) findViewById(R.id.txVRecomendacion);

        //Recibiendo datos del activity anterior.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


            String datos = (String) extras.get("datos");
            double imc = (Double) extras.get("imc");

            txVDatos.setText(getString(R.string.Sus_datos) +datos);

        //Metodo para verificar la imagen a colocar dependiendo del peso de la persona
        Persona p = new Persona();

        p.calcularIMC();

        if (imc < 20) {
            imagen = "uno";
            p.setStatus(getString(R.string.bajoPeso));

            txVRecomendacion.setText(getString(R.string.salud_1_1) +
                    getString(R.string.saludo_1_2) +
                    getString(R.string.salud_1_3));
        } else {
            if (imc >= 20 && imc <= 25) {
                imagen = "dos";
                p.setStatus(getString(R.string.pesoNormal));

                txVRecomendacion.setText(getString(R.string.salud_2_1) +
                        getString(R.string.salud_2_2));
            } else {
                imagen = "tres";
                p.setStatus(getString(R.string.sobre_peso));

                txVRecomendacion.setText(getString(R.string.salud_3_1) +
                        getString(R.string.salud_3_2));

            }

        }

        //Colocar una imagen dependiendo del peso que llega.
        ImageView imageView;

        imageView =(ImageView) findViewById(R.id.imageAll);

        int res_imagen = getResources().getIdentifier(imagen, recurso, getPackageName());

        imageView.setImageResource(res_imagen);


    }
    }
