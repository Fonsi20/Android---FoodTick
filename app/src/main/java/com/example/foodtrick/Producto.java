package com.example.foodtrick;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodtrick.BBDD.BDHelper;
import com.example.foodtrick.Objetos.productoMostrar;

public class Producto extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase DBComidas;
    int contadorSaludableGrasas = 0;
    int contadorSaludableSal = 0;
    int contadorSaludableAzucar = 0;

    private TextView txtNombreP, txtNombreCat, txtGrasas, txtAzucares, txtHidratos, txSalu;
    private productoMostrar pMostrar;
    private String nombreP;
    private LinearLayout LLProdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombreP = bundle.getString("NombreDelProducto");

        txtNombreP = findViewById(R.id.NombreProducto);
        txtNombreCat = findViewById(R.id.CategoriaProducto);
        txtAzucares = findViewById(R.id.cantidadCaloricas);
        txtHidratos = findViewById(R.id.cantidadHidratos);
        txtGrasas = findViewById(R.id.cantidadGrasas);
        txSalu = findViewById(R.id.txSaludable);
        LLProdu = findViewById(R.id.LLProducto);

        consultaComidaProducto();

        txtNombreP.setText(pMostrar.getNombre());
        txtNombreCat.setText(pMostrar.getCategoria());
        txtGrasas.setText(String.valueOf(pMostrar.getGrasas()));
        txtAzucares.setText(String.valueOf(pMostrar.getAzucares()));
        txtHidratos.setText(String.valueOf(pMostrar.getHidratos()));
        LLProdu.setBackgroundResource(pMostrar.getImg());

        if (pMostrar.getGrasas() < 3) {
            contadorSaludableGrasas = 0;
        } else if (pMostrar.getGrasas() >= 3 && pMostrar.getGrasas() <= 20) {
            contadorSaludableGrasas = 1;
        } else if (pMostrar.getGrasas() > 20) {
            contadorSaludableGrasas = 2;
        }

        if (pMostrar.getHidratos() < 0.3) {
            contadorSaludableSal = 0;
        } else if (pMostrar.getHidratos() >= 0.3f && pMostrar.getHidratos() <= 1.5f) {
            contadorSaludableSal = 1;
        } else if (pMostrar.getHidratos() > 1.5f) {
            contadorSaludableSal = 2;
        }

        if (pMostrar.getAzucares() < 5) {
            contadorSaludableAzucar = 0;
        } else if (pMostrar.getAzucares() >= 5 && pMostrar.getAzucares() <= 10) {
            contadorSaludableAzucar = 1;
        } else if (pMostrar.getAzucares() > 10) {
            contadorSaludableAzucar = 2;
        }

        if (pMostrar.getCategoria().equals("Frutas") || pMostrar.getCategoria().equals("Hortalizas")) {
            txSalu.setText(R.string.essaludable);
            txSalu.setBackgroundResource(R.drawable.boton_saludable);
        } else {
            if (contadorSaludableAzucar == 1 && contadorSaludableSal == 1 && contadorSaludableGrasas == 1) {
                txSalu.setText(R.string.tencuidado);
                txSalu.setBackgroundResource(R.drawable.boton_maybesaludable);

            } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 2 && contadorSaludableGrasas == 2) {
                txSalu.setText(R.string.noessaludable);
                txSalu.setBackgroundResource(R.drawable.boton_nosaludable);

            } else if (contadorSaludableAzucar == 0 && contadorSaludableSal == 0 && contadorSaludableGrasas == 0) {
                txSalu.setText(R.string.essaludable);
                txSalu.setBackgroundResource(R.drawable.boton_saludable);

            } else if (contadorSaludableAzucar == 0 && contadorSaludableSal == 0 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 0 && contadorSaludableSal == 1 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 1 && contadorSaludableSal == 0 && contadorSaludableGrasas == 0) {
                txSalu.setText(R.string.essaludable);
                txSalu.setBackgroundResource(R.drawable.boton_saludable);

            } else if (contadorSaludableAzucar == 0 && contadorSaludableSal == 1 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 0 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 1 && contadorSaludableGrasas == 0) {
                txSalu.setText(R.string.tencuidado);
                txSalu.setBackgroundResource(R.drawable.boton_maybesaludable);

            } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 1 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 2 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 1 && contadorSaludableGrasas == 2) {
                txSalu.setText(R.string.tencuidado);
                txSalu.setBackgroundResource(R.drawable.boton_maybesaludable);

            } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 2 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 2 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 2 && contadorSaludableSal == 1 && contadorSaludableGrasas == 2) {
                txSalu.setText(R.string.noessaludable);
                txSalu.setBackgroundResource(R.drawable.boton_nosaludable);

            } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 0 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 2 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 0 && contadorSaludableGrasas == 2) {
                txSalu.setText(R.string.tencuidado);
                txSalu.setBackgroundResource(R.drawable.boton_maybesaludable);

            } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 2 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 2 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 2 && contadorSaludableSal == 0 && contadorSaludableGrasas == 2) {
                txSalu.setText(R.string.noessaludable);
                txSalu.setBackgroundResource(R.drawable.boton_nosaludable);

            } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 1 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 1 && contadorSaludableSal == 2 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 1 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 2 && contadorSaludableSal == 0 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 0 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 0 && contadorSaludableSal == 2 && contadorSaludableGrasas == 1) {
                txSalu.setText(R.string.tencuidado);
                txSalu.setBackgroundResource(R.drawable.boton_maybesaludable);

            }

            DBComidas.close();
        }
    }

    private void consultaComidaProducto() {


        Cursor cursor = DBComidas.rawQuery("select a.nombreA,c.nombreC,a.hidratos,a.azucar,a.grasa,a.imgpro from Alimentos as a inner join Categorias as c on a.cat=c.id where a.nombreA='" + nombreP.toString() + "'", null);

        if (cursor.moveToFirst()) {
            do {
                pMostrar = new productoMostrar();
                pMostrar.setNombre(cursor.getString(0));
                pMostrar.setCategoria(cursor.getString(1));

                //Para ver si tiene una imagen para la lista
                int valor = cursor.getInt(5);
                if (valor == 0) {
                    pMostrar.setImg(R.drawable.fondoprodudefualt);
                } else {
                    pMostrar.setImg(cursor.getInt(5));
                }

                pMostrar.setHidratos(cursor.getInt(2));
                pMostrar.setAzucares(cursor.getInt(3));
                pMostrar.setGrasas(cursor.getInt(4));
            } while (cursor.moveToNext());
        }

    }

}
