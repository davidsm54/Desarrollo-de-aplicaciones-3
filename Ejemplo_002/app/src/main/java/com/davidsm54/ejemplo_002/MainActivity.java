package com.davidsm54.ejemplo_002;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class
MainActivity extends AppCompatActivity {
    EditText txtNombre;
    EditText txtPeso;
    EditText txtEstatura;

    CheckBox chEjercicio;
    RadioButton rbHombre;
    RadioButton rbMujer;
    TextView txVResultado;
    Button btnCalcularPeso;
    Button btnLimpiar;
    Button btnLeng;

    Locale locale;
    Configuration config = new Configuration();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEstatura = (EditText) findViewById(R.id.txtEstatura);
        txtPeso = (EditText) findViewById(R.id.txtPeso);
        chEjercicio = (CheckBox) findViewById(R.id.chEjercicio);
        rbHombre = (RadioButton) findViewById(R.id.rdHombre);
        rbMujer = (RadioButton) findViewById(R.id.rdMujer);
        txVResultado = (TextView) findViewById(R.id.txVResultado);
        btnCalcularPeso = (Button) findViewById(R.id.btnCalcular);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);

        //Boton para cambiar de idioma
        btnLeng = (Button) findViewById(R.id.btnleng);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setText("");
                txtEstatura.setText("");
                txtPeso.setText("");
                txVResultado.setText("");
                chEjercicio.setChecked(false);
                rbMujer.setChecked(false);
                rbHombre.setChecked(false);
            }
        });

        btnLeng.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        muestraDialogo();
                    }});

        btnCalcularPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Persona p = new Persona();

                p.setNombre(txtNombre.getText().toString());
                p.setPeso(Double.parseDouble(txtPeso.getText().toString()));
                p.setEstatura(Double.parseDouble(txtEstatura.getText().toString()));


                if (chEjercicio.isChecked()) {

                    p.setEjercicio((byte) 1);
                } else {
                    p.setEjercicio((byte) 0);
                }

                if (rbHombre.isChecked()) {
                    p.setSexo('H');
                } else {
                    p.setSexo('F');
                }

                if ((!rbHombre.isChecked()) && !(rbMujer.isChecked())) {
                    p.setSexo('X');
                }

                p.calcularIMC();

                if (p.getImc() < 20) {
                    p.setStatus(getString(R.string.bajoPeso));

                } else {
                    if (p.getImc() >= 20 && p.getImc() <= 25) {
                        p.setStatus(getString(R.string.pesoNormal));
                    } else {
                        p.setStatus(getString(R.string.sobre_peso));

                    }

                }

                //Enviar datos al siguiente activity

                   Intent intent = new Intent(MainActivity.this, Recomendacion.class);
                    String datos = p.toString();
                    double imc = p.getImc();

                    intent.putExtra("datos", datos);
                    intent.putExtra("imc", imc);

                    startActivity(intent);
            }
        });
    }



        //Metodo para mostrar una ventana de dialogo con los dos idiomas disponibles
        private void muestraDialogo(){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

            dialogo.setTitle(getResources().getString(R.string.lenguaje));

            //Se extraen los idiomas que se han establecido
            String[] types = getResources().getStringArray(R.array.languages);

            dialogo.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int idioma) {

                    dialog.dismiss();
                    switch(idioma){
                        case 0:
                            locale = new Locale("en");
                            config.locale =locale;
                            break;
                        case 1:
                            locale = new Locale("es");
                            config.locale =locale;
                            break;
                        case 2:
                            locale = new Locale("fra");
                            config.locale =locale;
                            break;
                    }

                    getResources().updateConfiguration(config, null);
                    Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(refresh);
                    finish();
                }

            });

            dialogo.show();

            }
        }



