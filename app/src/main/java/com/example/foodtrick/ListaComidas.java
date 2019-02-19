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
    ArrayList<Comida> listComMarcada;

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
            case "Carne":
                numCat = "1";
                break;
            case "Pescado":
                numCat = "2";
                break;
            case "Hortalizas":
                numCat = "3";
                break;
            case "Bebidas":
                numCat = "4";
                break;
            case "Lacteos":
                numCat = "5";
                break;
            case "Frutas":
                numCat = "6";
                break;
            case "Postres":
                numCat = "7";
                break;
            case "Pasta":
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
            if (listComMarcada == null || listComMarcada.size() == 0) {
                Log.i("productoNUEVO", "ERROR: No hay nada en la lista");
            } else {
                Log.i("productoNUEVO", "Tamaño lista: " + listComMarcada.size());
                for (int i = 0; listComMarcada.size() > i; i++) {
                    Log.i("productoNUEVO", "entraaaaaaaaaa");
                    if (listComMarcada.get(i).getNombre().equals(com.getNombre())) {
                        com.setCont(1);
                    } else {
                        com.setCont(0);
                    }
                }
            }

            int valor = cursor.getInt(6);
            if (valor == 0) {
                com.setImg(R.drawable.foodtick360);
            } else {
                com.setImg(cursor.getInt(6));
            }

            if (com.getCategoria().toString().equals(numCat)) {
                ComidaList.add(com);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        int i = 0;
        listComMarcada = new ArrayList<Comida>();
        Log.i("productoNUEVO", "cantidad de comidas en la lista: " + ComidaList.size());
        while (ComidaList.size() > i) {
            String nombre = ComidaList.get(i).getNombre();
            int conta = ComidaList.get(i).getCont();
            i++;

            if (conta != 0) {
                Log.i("productoNUEVO", nombre.toString());
                listComMarcada.add(ComidaList.get(i));
            }
        }
        Log.i("productoNUEVO", "cantidad de comidas en la lista de Marcados: " + listComMarcada.size());
    }
}
