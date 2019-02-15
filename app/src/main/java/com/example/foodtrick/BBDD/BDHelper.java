package com.example.foodtrick.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    String SQLCrearAli = "CREATE TABLE IF NOT EXISTS " + tabla2 + "(" + columna4 + " VARCHAR(30) PRIMARY KEY, " + columna5 + " INTEGER(30), " + columna6 + " INTEGER(30), " + columna7 + " INTEGER(30), " + columna8 + " INTEGER, FOREIGN KEY ("+columna8+") REFERENCES Categorias("+columna1+"))";
    String SQLCrearCat = "CREATE TABLE IF NOT EXISTS " + tabla1 + "(" + columna1 + " INTEGER PRIMARY KEY, " + columna2 + " VARCHAR(30), " + columna3 + " VARCHAR(300))";

    String SQLDeleteAli = "DROP TABLE IF EXISTS " + tabla2;
    String SQLDeleteCat = "DROP TABLE IF EXISTS " + tabla1;

    public BDHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLCrearCat);
        sqLiteDatabase.execSQL(SQLCrearAli);
        sqLiteDatabase.execSQL("INSERT INTO Categorias values (1,'Carne','Come Carne menos de 5 veces a la semana.')," +
                "(2,'Pescado','El pescado es muy buneo.')," +
                "(3,'Hortalizas','Del campo a tu casa.')," +
                "(4,'Bebidas','Desde agua a bebidas alcoholicas.')," +
                "(5,'Lacteos','Yogures, quesos, leche.')," +
                "(6,'Frutas','Pomelo <3.')," +
                "(7,'Postres','THE CAKE IS A LIE!.')," +
                "(8,'Pasta','Unos buenos rigoletis di el doctore hecter')");
        sqLiteDatabase.execSQL("INSERT INTO Alimentos values ('Lomo Embuchado',14,245,75,1)," +
                "('Solomillo Mercadona',145,89,32,1)," +
                "('Agua',0,0,0,4)," +
                "('Carne picada de Cerdo y Ternera',15,72,32,1)," +
                "('Pizza Casa Tarradellas Carbonara',405,9,82,8)," +
                "('Salmón Pescanoba',254,35,21,2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLDeleteAli);
        sqLiteDatabase.execSQL(SQLDeleteCat);
    }
}
