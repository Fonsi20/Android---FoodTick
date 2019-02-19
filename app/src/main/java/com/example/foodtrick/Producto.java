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

    private TextView txtNombreP, txtNombreCat, txtGrasas, txtAzucares, txtHidratos;
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
        LLProdu = findViewById(R.id.LLProducto);

        consultaComidaProducto();

        txtNombreP.setText(pMostrar.getNombre());
        txtNombreCat.setText(pMostrar.getCategoria());
        txtGrasas.setText(String.valueOf(pMostrar.getGrasas()));
        txtAzucares.setText(String.valueOf(pMostrar.getAzucares()));
        txtHidratos.setText(String.valueOf(pMostrar.getHidratos()));
        //LLProdu.setBackground(R.drawable.fondoprodudefualt);

        DBComidas.close();
    }

    private void consultaComidaProducto() {

        Cursor cursor = DBComidas.rawQuery("select a.nombreA,c.nombreC,a.hidratos,a.azucar,a.grasa from Alimentos as a inner join Categorias as c on a.cat=c.id where a.nombreA='" + nombreP.toString() + "'", null);

        if (cursor.moveToFirst()) {
            do {
                pMostrar = new productoMostrar();
                pMostrar.setNombre(cursor.getString(0));
                pMostrar.setCategoria(cursor.getString(1));
                pMostrar.setImg(R.drawable.fondoprodudefualt);
                pMostrar.setHidratos(cursor.getInt(2));
                pMostrar.setAzucares(cursor.getInt(3));
                pMostrar.setGrasas(cursor.getInt(4));


                Log.i("productoNUEVO", String.valueOf(pMostrar.getNombre().toString()));
                Log.i("productoNUEVO", pMostrar.getCategoria().toString());
                Log.i("productoNUEVO", String.valueOf(pMostrar.getAzucares()));
            } while (cursor.moveToNext());
        }

    }
}
