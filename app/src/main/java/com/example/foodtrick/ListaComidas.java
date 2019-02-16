package com.example.foodtrick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodtrick.Adaptadores.AdaptadorPersonalizado;
import com.example.foodtrick.Objetos.Comida;

public class ListaComidas extends AppCompatActivity {

    private ListView lv;
    private Comida[] comidas;
    private TextView nomCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comidas);

        String Categoria;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Categoria = bundle.getString("Categoria");
        lv = (ListView) findViewById(R.id.lvComidas);
        nomCat = findViewById(R.id.nombreCat);
        nomCat.setText(Categoria);

        String[] arrayNombreComidas = getResources().getStringArray(R.array.nombrecesC);
        String[] arrayCategoriaComidas = getResources().getStringArray(R.array.categoriasC);
        Integer[] idFotos = new Integer[]{R.drawable.carne, R.drawable.pescados, R.drawable.pasta, R.drawable.procesados, R.drawable.saugar, R.drawable.donut, R.drawable.drink, R.drawable.hortalizas, R.drawable.pescados};
        comidas = new Comida[arrayNombreComidas.length];

        for (int i = 0; i < arrayNombreComidas.length; i++) {
            Comida comida = new Comida(arrayNombreComidas[i], arrayCategoriaComidas[i], idFotos[i]);
            comidas[i] = comida;
        }

        AdaptadorPersonalizado adaptador = new AdaptadorPersonalizado(this, comidas);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListaComidas.this, Producto.class);
                startActivity(i);
            }
        });

    }
}
