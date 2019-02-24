package com.example.foodtrick.BBDD;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;

import com.example.foodtrick.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BDHelper extends SQLiteOpenHelper {


    static final String tabla1 = "Categorias";
    static final String tabla2 = "Alimentos";
    static final String columna1 = "id";
    static final String columna2 = "nombreC";
    static final String columna3 = "descripcion";
    static final String columna4 = "nombreA";
    static final String columna5 = "grasa";
    static final String columna6 = "azucar";
    static final String columna7 = "hidratos";
    static final String columna8 = "cat";
    static final String columna9 = "img360pro";
    static final String columna10 = "imgpro";
    static final String columna11 = "menu";

    String SQLCrearCat = "CREATE TABLE IF NOT EXISTS " + tabla1 + "(" + columna1 + " INTEGER PRIMARY KEY, " + columna2 + " VARCHAR(30), " + columna3 + " VARCHAR(300))";
    String SQLCrearAli = "CREATE TABLE IF NOT EXISTS " + tabla2 + "(" + columna4 + " VARCHAR(30) PRIMARY KEY, " + columna5 + " FLOAT(30), " + columna6 + " FLOAT(30), " + columna7 + " FLOAT(30), "
            + columna8 + " INTEGER, " + columna10 + " BLOB," + columna9 + " BLOB," + columna11 + " INTEGER, FOREIGN KEY (" + columna8 + ") REFERENCES Categorias(" + columna1 + "))";

    String SQLDeleteAli = "DROP TABLE IF EXISTS " + tabla2;
    String SQLDeleteCat = "DROP TABLE IF EXISTS " + tabla1;

    public BDHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLCrearCat);
        sqLiteDatabase.execSQL(SQLCrearAli);

        //Cereales y Derivados
        sqLiteDatabase.execSQL("UPDATE " + tabla2 + " SET " + columna9 + "= '" + R.drawable.donut360 + "', " + columna10 + " = '" + R.drawable.donut + "' WHERE " + columna4 + " ='DONUTS';");
        sqLiteDatabase.execSQL("UPDATE " + tabla2 + " SET " + columna9 + "= '" + R.drawable.ricewjite360 + "', " + columna10 + " = '" + R.drawable.ricewhite + "' WHERE " + columna4 + " ='ARROZ BLANCO';");
        sqLiteDatabase.execSQL("UPDATE " + tabla2 + " SET " + columna9 + "= '" + R.drawable.oats360 + "', " + columna10 + " = '" + R.drawable.aots + "' WHERE " + columna4 + " ='AVENA';");
        sqLiteDatabase.execSQL("UPDATE " + tabla2 + " SET " + columna9 + "= '" + R.drawable.ensaimada360 + "', " + columna10 + " = '" + R.drawable.ensaimada + "' WHERE " + columna4 + " ='ENSAIMADA';");
        sqLiteDatabase.execSQL("UPDATE " + tabla2 + " SET " + columna9 + "= '" + R.drawable.lasana360 + "', " + columna10 + " = '" + R.drawable.lasana + "' WHERE " + columna4 + " ='LASAÑA';");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLDeleteAli);
        sqLiteDatabase.execSQL(SQLDeleteCat);
    }
}
