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
    int contadorSaludable = 0;

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

        if (pMostrar.getGrasas() >= 400) {
            contadorSaludable++;
        }
        if (pMostrar.getHidratos() >= 200) {
            contadorSaludable++;
        }
        if (pMostrar.getAzucares() >= 200) {
            contadorSaludable++;
        }

        if (pMostrar.getCategoria().equals("Frutas") || pMostrar.getCategoria().equals("Hortalizas")) {
            txSalu.setText(R.string.essaludable);
            txSalu.setBackgroundResource(R.drawable.boton_saludable);
        } else {
            if (contadorSaludable >= 1 && contadorSaludable <= 2) {
                txSalu.setText(R.string.tencuidado);
                txSalu.setBackgroundResource(R.drawable.boton_maybesaludable);
            } else if (contadorSaludable == 3) {
                txSalu.setText(R.string.noessaludable);
                txSalu.setBackgroundResource(R.drawable.boton_nosaludable);

            } else if (contadorSaludable == 0) {
                txSalu.setText(R.string.essaludable);
                txSalu.setBackgroundResource(R.drawable.boton_saludable);
            }
        }

        DBComidas.close();
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
