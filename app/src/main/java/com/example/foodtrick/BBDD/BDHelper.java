package com.example.foodtrick.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodtrick.R;

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

    String SQLCrearAli = "CREATE TABLE IF NOT EXISTS " + tabla2 + "(" + columna4 + " VARCHAR(30) PRIMARY KEY, " + columna5 + " INTEGER(30), " + columna6 + " INTEGER(30), " + columna7 + " INTEGER(30), " + columna8 + " INTEGER, " + columna10 + " BLOB," + columna9 + " BLOB, FOREIGN KEY (" + columna8 + ") REFERENCES Categorias(" + columna1 + "))";
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
        sqLiteDatabase.execSQL("INSERT INTO Alimentos values ('Lomo Embuchado',14,245,75,1,'0','0')," +
                "('Solomillo Mercadona',145,89,32,1,'0','" + R.drawable.carne + "')," +
                "('Costilla de Ternera',105,21,455,1,'0','0')," +
                "('Agua',0,0,0,4,'0','0')," +
                "('Leche Asturian Desnatada',36,45,10,5,'0','0')," +
                "('Queso Gran Capitan Semicurado',55,11,45,5,'0','0')," +
                "('Manzana Golden',0,0,0,6,'0','0')," +
                "('Platano de Canarias',20,0,0,6,'0','0')," +
                "('Naranja Valenciana',0,40,0,6,'0','0')," +
                "('Kiwi',0,0,0,6,'0','0')," +
                "('Vino Rioja',40,25,99,4,'0','0')," +
                "('Nestea',200,10,45,4,'0','0')," +
                "('Crema Cataalana',740,620,205,7,'0','0')," +
                "('Donut',740,620,205,7,'0','0')," +
                "('Pastel de Zanahoria',740,620,205,7,'0','0')," +
                "('Calabazin',70,60,5,3,'0','0')," +
                "('Pastel de Calabaza',740,620,205,7,'0','0')," +
                "('Filloas',740,620,205,7,'0','0')," +
                "('Bizcocho',740,620,205,7,'0','0')," +
                "('Gelatina',740,620,205,7,'0','0')," +
                "('Helado Magnum',740,620,205,7,'0','0')," +
                "('Coca-Cola',354,200,100,4,'0','0')," +
                "('Estrella Galicia',87,60,19,4,'0','0')," +
                "('Carne picada de Cerdo y Ternera',15,72,32,1,'0','0')," +
                "('Pizza Casa Tarradellas Carbonara',405,9,82,8,'0','0')," +
                "('Salmón Pescanoba',254,35,21,2,'0','0')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLDeleteAli);
        sqLiteDatabase.execSQL(SQLDeleteCat);
    }
}
