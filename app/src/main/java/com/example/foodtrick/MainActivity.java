package com.example.foodtrick;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodtrick.BBDD.BDHelper;
import com.example.foodtrick.Objetos.Categoria;
import com.example.foodtrick.Objetos.Comida;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase DBComidas;

    private TextView bC1, bC2, bC3, bC4, bC5, bC6, bC7, bC8;
    private ImageButton btnCat1, btnCat2, btnCat3, btnCat4, btnCat5, btnCat6, btnCat7, btnCat8, btnMenu;
    ArrayList<String> listaCategorias;
    ArrayList<Categoria> CategoriasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCat1 = (ImageButton) findViewById(R.id.btncate1);
        btnCat2 = (ImageButton) findViewById(R.id.btncate2);
        btnCat3 = (ImageButton) findViewById(R.id.btncate3);
        btnCat4 = (ImageButton) findViewById(R.id.btncate4);
        btnCat5 = (ImageButton) findViewById(R.id.btncate5);
        btnCat6 = (ImageButton) findViewById(R.id.btncate6);
        btnCat7 = (ImageButton) findViewById(R.id.btncate7);
        btnCat8 = (ImageButton) findViewById(R.id.btncate8);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        bC1 = (TextView) findViewById(R.id.btn1);
        bC2 = (TextView) findViewById(R.id.btn2);
        bC3 = (TextView) findViewById(R.id.btn3);
        bC4 = (TextView) findViewById(R.id.btn4);
        bC5 = (TextView) findViewById(R.id.btn5);
        bC6 = (TextView) findViewById(R.id.btn6);
        bC7 = (TextView) findViewById(R.id.btn7);
        bC8 = (TextView) findViewById(R.id.btn8);

        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();

        consultarListaCategorias();

        for (int i = 0; i < listaCategorias.size() + 1; i++) {
            switch (i) {
                case 1:
                    bC1.setText(listaCategorias.get(0));
                    break;
                case 2:
                    bC2.setText(listaCategorias.get(1));
                    break;
                case 3:
                    bC3.setText(listaCategorias.get(2));
                    break;
                case 4:
                    bC4.setText(listaCategorias.get(3));
                    break;
                case 5:
                    bC5.setText(listaCategorias.get(4));
                    break;
                case 6:
                    bC6.setText(listaCategorias.get(5));
                    break;
                case 7:
                    bC7.setText(listaCategorias.get(6));
                    break;
                case 8:
                    bC8.setText(listaCategorias.get(7));
                    break;
            }
        }

        btnCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(0));
                startActivity(i);
            }
        });

        btnCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(1));
                startActivity(i);
            }
        });

        btnCat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(2));
                startActivity(i);
            }
        });

        btnCat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(3));
                startActivity(i);
            }
        });

        btnCat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(4));
                startActivity(i);
            }
        });

        btnCat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(5));
                startActivity(i);
            }
        });

        btnCat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(6));
                startActivity(i);
            }
        });

        btnCat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                i.putExtra("Categoria", listaCategorias.get(7));
                startActivity(i);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });

        DBComidas.close();
    }

    private void consultarListaCategorias() {
        Categoria cat = null;
        CategoriasList = new ArrayList<Categoria>();
        //select * from Categorias
        Cursor cursor = DBComidas.rawQuery("SELECT * FROM Categorias", null);

        while (cursor.moveToNext()) {
            cat = new Categoria();
            cat.setId(cursor.getInt(0));
            cat.setNombre(cursor.getString(1));
            cat.setDescripcion(cursor.getString(2));

            CategoriasList.add(cat);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaCategorias = new ArrayList<String>();
        for (int i = 0; i < CategoriasList.size(); i++) {
            listaCategorias.add(CategoriasList.get(i).getNombre());
        }
    }
}
