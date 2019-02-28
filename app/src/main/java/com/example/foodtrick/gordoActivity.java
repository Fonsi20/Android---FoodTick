package com.example.foodtrick;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class gordoActivity extends AppCompatActivity {

    private TextView tituloicg, tituloge;
    private LinearLayout lledad, rgGenero, rgActividad;
    private Button btnCalcular;
    private EditText edAltura, edPeso, edEdad;
    private RadioButton rbHom, rbMujer, rbLigero, rbModerado, rbMucho;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    lledad.setVisibility(View.GONE);
                    rgActividad.setVisibility(View.GONE);
                    rgGenero.setVisibility(View.GONE);
                    tituloicg.setVisibility(View.VISIBLE);
                    tituloge.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    lledad.setVisibility(View.VISIBLE);
                    rgActividad.setVisibility(View.VISIBLE);
                    rgGenero.setVisibility(View.VISIBLE);
                    tituloicg.setVisibility(View.GONE);
                    tituloge.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gordo);

        lledad = (LinearLayout) findViewById(R.id.LLedad);
        rgGenero = (LinearLayout) findViewById(R.id.llgen);
        rgActividad = (LinearLayout) findViewById(R.id.llact);
        tituloge = (TextView) findViewById(R.id.tituloEG);
        tituloicg = (TextView) findViewById(R.id.tituloIGC);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        edAltura = (EditText) findViewById(R.id.edAltura);
        edPeso = (EditText) findViewById(R.id.edPeso);
        edEdad = (EditText) findViewById(R.id.edEdad);
        rbHom = (RadioButton) findViewById(R.id.rbHombre);
        rbMujer = (RadioButton) findViewById(R.id.rbMujer);
        rbLigero = (RadioButton) findViewById(R.id.rbLigero);
        rbModerado = (RadioButton) findViewById(R.id.rbModerado);
        rbMucho = (RadioButton) findViewById(R.id.rbPesado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tituloge.getVisibility() == View.VISIBLE) {

                    float peso = Float.parseFloat(edPeso.getText().toString());
                    float altura = Float.parseFloat(edAltura.getText().toString());
                    int edad = Integer.parseInt(edEdad.getText().toString());
                    String texto = null;
                    float MB = 0f, GET = 0f;

                    if (rbLigero.isChecked()) {
                        if (rbHom.isChecked()) {
                            MB = (float) (66 + (13.7 * peso) + (5 * altura) - (6.8 * edad));
                            GET = MB * 1.55f;
                        } else if (rbMujer.isChecked()) {
                            MB = (float) (655 + (9.6 * peso) + (1.7 * altura) - (4.7 * edad));
                            GET = MB * 1.56f;
                        }
                    } else if (rbModerado.isChecked()) {
                        if (rbHom.isChecked()) {
                            MB = (float) (66 + (13.7 * peso) + (5 * altura) - (6.8 * edad));
                            GET = MB * 1.78f;
                        } else if (rbMujer.isChecked()) {
                            MB = (float) (655 + (9.6 * peso) + (1.7 * altura) - (4.7 * edad));
                            GET = MB * 1.64f;
                        }
                    } else if (rbMucho.isChecked()) {
                        if (rbHom.isChecked()) {
                            MB = (float) (66 + (13.7 * peso) + (5 * altura) - (6.8 * edad));
                            GET = MB * 2.10f;
                        } else if (rbMujer.isChecked()) {
                            MB = (float) (655 + (9.6 * peso) + (1.7 * altura) - (4.7 * edad));
                            GET = MB * 1.82f;
                        }
                    }

                    AlertDialog.Builder ventana = new AlertDialog.Builder(gordoActivity.this);
                    ventana.setTitle(R.string.ge);
                    ventana.setMessage("Tienes un metabolismo basal es: " + String.valueOf(MB) + "\nTu gasto energético total es: " + String.valueOf(GET));
                    ventana.setIcon(R.drawable.foodtick);
                    ventana.show();

                } else {
                    float peso = Float.parseFloat(edPeso.getText().toString());
                    float altura = Float.parseFloat(edAltura.getText().toString());
                    float IMC = peso / (altura * altura);
                    String texto = null;

                    if (IMC >= 40f) {
                        texto = "Obesidad Morvido: " + IMC;
                    } else if (IMC >= 35f && IMC <= 39.9f) {
                        texto = "Obesidad Tipo II: " + IMC;
                    } else if (IMC >= 30f && IMC <= 34.9f) {
                        texto = "Obesidad Tipo I: " + IMC;
                    } else if (IMC >= 25f && IMC <= 29.9f) {
                        texto = "Sobrepeso: " + IMC;
                    } else if (IMC >= 19f && IMC <= 24.9f) {
                        texto = "Peso normal: " + IMC;
                    } else if (IMC >= 17f && IMC <= 18.9f) {
                        texto = "Desnutrición leve: " + IMC;
                    } else if (IMC >= 16f && IMC <= 16.9f) {
                        texto = "Desnutrición moderada: " + IMC;
                    } else if (IMC <= 15.9f) {
                        texto = "Desnutrición grave: " + IMC;
                    }

                    AlertDialog.Builder ventana = new AlertDialog.Builder(gordoActivity.this);
                    ventana.setTitle(R.string.igc);
                    ventana.setMessage(texto);
                    ventana.setIcon(R.drawable.foodtick);
                    ventana.show();
                }
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
