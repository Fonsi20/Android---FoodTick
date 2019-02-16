package com.example.foodtrick;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodtrick.Adaptadores.AdaptadorPersonalizado;
import com.example.foodtrick.BBDD.BDHelper;
import com.example.foodtrick.Objetos.Categoria;
import com.example.foodtrick.Objetos.Comida;

import java.util.ArrayList;

public class ListaComidas extends AppCompatActivity {

    private ListView lv;
    private Comida[] comidas;
    private TextView nomCat;
    private String Categoria;
    ArrayList<String> listaComida;
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

        consultarListaComidas();

        /*String[] arrayNombreComidas = getResources().getStringArray(R.array.nombrecesC);
        String[] arrayCategoriaComidas = getResources().getStringArray(R.array.categoriasC);
        Integer[] idFotos = new Integer[]{R.drawable.carne, R.drawable.pescados, R.drawable.pasta, R.drawable.procesados, R.drawable.saugar, R.drawable.donut, R.drawable.drink, R.drawable.hortalizas, R.drawable.pescados};
        comidas = new Comida[arrayNombreComidas.length];

        for (int i = 0; i < arrayNombreComidas.length; i++) {
            Comida comida = new Comida(arrayNombreComidas[i], arrayCategoriaComidas[i], idFotos[i]);
            comidas[i] = comida;
        }*/

        AdaptadorPersonalizado adaptador = new AdaptadorPersonalizado(this, ComidaList);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListaComidas.this, Producto.class);
                startActivity(i);
            }
        });

    }

    private void consultarListaComidas() {
        Comida com = null;
        ComidaList = new ArrayList<Comida>();
        //select * from Alimentos, la categoria ya la tengo arriba, que la recibo el putExtra
        Cursor cursor = DBComidas.rawQuery("SELECT * FROM Alimentos", null);

        while (cursor.moveToNext()) {
            com = new Comida();
            com.setCategoria(Categoria);
            com.setNombre(cursor.getString(0));
            com.setImg(R.drawable.hortalizas);

            Log.i("Categoria", String.valueOf(com.getCategoria().toString()));
            Log.i("Nombre", com.getNombre().toString());

            ComidaList.add(com);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaComida = new ArrayList<String>();
        for (int i = 0; i < ComidaList.size(); i++) {
            listaComida.add(ComidaList.get(i).getNombre());
        }
    }
}
