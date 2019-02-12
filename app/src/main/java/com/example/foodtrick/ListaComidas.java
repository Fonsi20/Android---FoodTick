package com.example.foodtrick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListaComidas extends AppCompatActivity {

    private ListView lv;
    private  Comida[] comidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comidas);

        lv = (ListView) findViewById(R.id.lvComidas);

        String[] arrayNombreComidas = getResources().getStringArray(R.array.nombrecesC);
        String[] arrayCategoriaComidas = getResources().getStringArray(R.array.categoriasC);
        Integer[] idFotos =new Integer[]{R.drawable.carne, R.drawable.pescados, R.drawable.pasta, R.drawable.procesados, R.drawable.saugar, R.drawable.donut, R.drawable.drink, R.drawable.hortalizas, R.drawable.pescados};
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
                Toast.makeText(ListaComidas.this, "Elección: " + comidas[position].getNombre(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
