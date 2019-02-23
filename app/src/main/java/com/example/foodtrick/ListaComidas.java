package com.example.foodtrick;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodtrick.Adaptadores.AdaptadorPersonalizado;
import com.example.foodtrick.BBDD.BDHelper;
import com.example.foodtrick.Objetos.Comida;

import java.util.ArrayList;

public class ListaComidas extends AppCompatActivity {

    private ListView lv;
    private Comida[] comidas;
    private TextView nomCat;
    private String Categoria;
    private String numCat;
    ArrayList<Comida> ComidaList;

    private String BDname;
    private int BDversion;
    private SQLiteDatabase DBComidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comidas);


        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Categoria = bundle.getString("Categoria");
        lv = (ListView) findViewById(R.id.lvComidas);
        nomCat = findViewById(R.id.nombreCat);
        nomCat.setText(Categoria);

        switch (Categoria) {
            case "Cereais e derivados":
                numCat = "1";
                break;
            case "Bebidas":
                numCat = "2";
                break;
            case "Froita":
                numCat = "3";
                break;
            case "Legumes":
                numCat = "4";
                break;
            case "Lácteos":
                numCat = "5";
                break;
            case "Ovos":
                numCat = "6";
                break;
            case "Verduras e Hortalizas":
                numCat = "7";
                break;
            case "Sementes e froitos secos":
                numCat = "8";
                break;
        }

        consultarListaComidas();

        AdaptadorPersonalizado adaptador = new AdaptadorPersonalizado(this, ComidaList);
        lv.setAdapter(adaptador);

        DBComidas.close();

    }

    private void consultarListaComidas() {
        Comida com = null;
        ComidaList = new ArrayList<Comida>();

        //select * from Alimentos, la categoria ya la tengo arriba, que la recibo el putExtra
        Cursor cursor = DBComidas.rawQuery("SELECT * FROM Alimentos", null);

        while (cursor.moveToNext()) {
            com = new Comida();
            com.setCategoria(cursor.getString(4));
            com.setNombre(cursor.getString(0));
            com.setCont(cursor.getInt(7));

            //Para ver si tiene una imagen para la lista
            int valor = cursor.getInt(6);
            if (valor == 0) {
                com.setImg(R.drawable.foodtick360);
            } else {
                com.setImg(cursor.getInt(6));
            }

            //Comprobamos que el proyecto pertenece a la categoría
            if (com.getCategoria().toString().equals(numCat)) {
                ComidaList.add(com);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();

        int i = 0;

        do {
            String nombre = ComidaList.get(i).getNombre();
            int conta = ComidaList.get(i).getCont();

            if (conta != 0) {
                DBComidas.execSQL("UPDATE Alimentos SET menu=1 where nombreA='" + nombre + "'");
            } else {
                DBComidas.execSQL("UPDATE Alimentos SET menu=0 where nombreA='" + nombre + "'");
            }

            i++;
        } while (ComidaList.size() > i);

        DBComidas.close();
    }
}
