package com.example.foodtrick;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.foodtrick.BBDD.BDHelper;

public class MainActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase DBComidas;

    private ImageButton btnCarne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCarne = (ImageButton) findViewById(R.id.btnCarne);

        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();

        btnCarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaComidas.class);
                startActivity(i);
            }
        });
        DBComidas.close();
    }
}
