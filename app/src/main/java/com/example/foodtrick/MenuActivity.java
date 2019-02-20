package com.example.foodtrick;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.foodtrick.Adaptadores.AdaptadorPersonalizado;
import com.example.foodtrick.BBDD.BDHelper;
import com.example.foodtrick.Objetos.Comida;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase DBComidas;
    private ListView lvMenuC;

    ArrayList<Comida> ComidaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();

        lvMenuC = (ListView) findViewById(R.id.lvMenu);

        consultarListaComidasMenu();

        AdaptadorPersonalizado adaptador = new AdaptadorPersonalizado(this, ComidaList);
        lvMenuC.setAdapter(adaptador);


    }

    private void consultarListaComidasMenu() {

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
            if (com.getCont() == 1) {
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
