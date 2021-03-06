package com.example.foodtrick;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.foodtrick.Adaptadores.AdaptadorPersonalizadoMenu;
import com.example.foodtrick.BBDD.BDHelper;
import com.example.foodtrick.Objetos.Comida;
import com.example.foodtrick.Objetos.productoMostrar;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase DBComidas;
    private ListView lvMenuC;

    ArrayList<Comida> ComidaList;
    ArrayList<productoMostrar> pMostrar;
    private Button btnVaciarCesta, btnCalcular;
    private LinearLayout loNadaCesta;
    private int contadorSaludable = 0;
    private float totalAzucar = 0f;
    private float totalHidratos = 0f;
    private float totalGrasas = 0f;
    int contadorSaludableGrasas = 0;
    int contadorSaludableAzucar = 0;
    int contadorSaludableSal = 0;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        abrirBBDD();

        lvMenuC = (ListView) findViewById(R.id.lvMenu);
        btnVaciarCesta = (Button) findViewById(R.id.btnVaciarCesta);
        btnCalcular = (Button) findViewById(R.id.btnCalcularSaludable);
        loNadaCesta = (LinearLayout) findViewById(R.id.loNadaCesta);

        consultarListaComidasMenu();

        if (ComidaList == null || ComidaList.size() == 0) {
            loNadaCesta.setVisibility(View.VISIBLE);
            lvMenuC.setVisibility(View.GONE);
        } else {
            loNadaCesta.setVisibility(View.GONE);
            lvMenuC.setVisibility(View.VISIBLE);
            AdaptadorPersonalizadoMenu adaptador = new AdaptadorPersonalizadoMenu(this, ComidaList);
            lvMenuC.setAdapter(adaptador);
        }
        btnVaciarCesta.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                int i = 0;
                //abrirBBDD();

                while (ComidaList.size() > i) {
                    String nombre = ComidaList.get(i).getNombre();
                    int conta = ComidaList.get(i).getCont();

                    if (conta != 0) {
                        Log.i("productoNUEVO", nombre);
                        DBComidas.execSQL("UPDATE Alimentos SET menu=0 where nombreA='" + nombre + "'");
                    }
                    i++;
                }
                lvMenuC.setAdapter(null);
                consultarListaComidasMenu();
                AdaptadorPersonalizadoMenu adaptador2 = new AdaptadorPersonalizadoMenu(MenuActivity.this, ComidaList);
                lvMenuC.setAdapter(adaptador2);
                loNadaCesta.setVisibility(View.VISIBLE);
                lvMenuC.setVisibility(View.GONE);
                btnCalcular.setTextColor(getResources().getColor(R.color.black));
                btnCalcular.setText(R.string.esbueno);
                btnCalcular.setBackgroundResource(R.drawable.boton_redondo);

            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                totalAzucar = 0f;
                totalHidratos = 0f;
                totalGrasas = 0f;
                contadorSaludable = 0;

                if (ComidaList.size() == 0 || ComidaList == null) {

                    DBComidas.execSQL("UPDATE Alimentos SET menu=0");

                    lvMenuC.setAdapter(null);
                    consultarListaComidasMenu();
                    AdaptadorPersonalizadoMenu adaptador2 = new AdaptadorPersonalizadoMenu(MenuActivity.this, ComidaList);
                    lvMenuC.setAdapter(adaptador2);

                    loNadaCesta.setVisibility(View.VISIBLE);
                    lvMenuC.setVisibility(View.GONE);
                    btnCalcular.setTextColor(getResources().getColor(R.color.black));
                    btnCalcular.setText(R.string.esbueno);
                    btnCalcular.setBackgroundResource(R.drawable.boton_redondo);

                    AlertDialog.Builder ventana = new AlertDialog.Builder(MenuActivity.this);
                    ventana.setTitle(R.string.titulodialog);
                    ventana.setMessage(R.string.textodialog);
                    ventana.setIcon(R.drawable.foodtick);
                    ventana.show();

                } else {

                    consultarListaComidaMenuDatos();

                    int count = lvMenuC.getAdapter().getCount();

                    for (int i = 0; i < count; i++) {
                        ViewGroup row = (ViewGroup) lvMenuC.getChildAt(i);
                        EditText edValor = (EditText) row.findViewById(R.id.txCategoria);
                        //  Get your controls from this ViewGroup and perform your task on them =)

                        if (!edValor.getText().toString().equals("")) {
                            Log.i("productoNUEVO", "Valor que escribí: " + edValor.getText().toString());

                            float azucar = pMostrar.get(i).getAzucares();
                            float grasas = pMostrar.get(i).getGrasas();
                            float hidratos = pMostrar.get(i).getHidratos();

                            azucar = (Float.parseFloat(edValor.getText().toString()) * azucar) / 100;
                            grasas = (Float.parseFloat(edValor.getText().toString()) * grasas) / 100;
                            hidratos = (Float.parseFloat(edValor.getText().toString()) * hidratos) / 100;

                            pMostrar.get(i).setAzucares(azucar);
                            pMostrar.get(i).setGrasas(grasas);
                            pMostrar.get(i).setHidratos(hidratos);

                        }

                    }

                    for (productoMostrar com : pMostrar) {
                        if (!com.getCategoria().equals("Frutas")) {
                            totalAzucar += com.getAzucares();
                            totalGrasas += com.getGrasas();
                            totalHidratos += com.getHidratos();
                        }
                    }

                    if (totalGrasas < 3) {
                        contadorSaludableGrasas = 0;
                    } else if (totalGrasas >= 3 && totalGrasas <= 20) {
                        contadorSaludableGrasas = 1;
                    } else if (totalGrasas > 20) {
                        contadorSaludableGrasas = 2;
                    }


                    if (totalHidratos < 0.3) {
                        contadorSaludableSal = 0;
                    } else if (totalHidratos >= 0.3f && totalHidratos <= 1.5f) {
                        contadorSaludableSal = 1;
                    } else if (totalHidratos > 1.5f) {
                        contadorSaludableSal = 2;
                    }


                    if (totalAzucar < 5) {
                        contadorSaludableAzucar = 0;
                    } else if (totalAzucar >= 5 && totalAzucar <= 10) {
                        contadorSaludableAzucar = 1;
                    } else if (totalAzucar > 10) {
                        contadorSaludableAzucar = 2;
                    }


                    AlertDialog.Builder ventanatotales = new AlertDialog.Builder(MenuActivity.this);
                    ventanatotales.setTitle("Total:");
                    ventanatotales.setMessage("El total de grasas es: " + String.valueOf(totalGrasas) + "\nEl total de azucar es: " + String.valueOf(totalAzucar)+ "\nEl total de sal es: " + String.valueOf(totalHidratos));
                    ventanatotales.setIcon(R.drawable.foodtick);
                    ventanatotales.show();

                    if (contadorSaludableAzucar == 1 && contadorSaludableSal == 1 && contadorSaludableGrasas == 1) {
                        btnCalcular.setText(R.string.tencuidado);
                        btnCalcular.setBackgroundResource(R.drawable.boton_maybesaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 2 && contadorSaludableGrasas == 2) {
                        btnCalcular.setText(R.string.noessaludable);
                        btnCalcular.setBackgroundResource(R.drawable.boton_nosaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 0 && contadorSaludableSal == 0 && contadorSaludableGrasas == 0) {
                        btnCalcular.setText(R.string.essaludable);
                        btnCalcular.setBackgroundResource(R.drawable.boton_saludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 0 && contadorSaludableSal == 0 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 0 && contadorSaludableSal == 1 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 1 && contadorSaludableSal == 0 && contadorSaludableGrasas == 0) {
                        btnCalcular.setText(R.string.tencuidado);
                        btnCalcular.setBackgroundResource(R.drawable.boton_maybesaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 0 && contadorSaludableSal == 1 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 0 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 1 && contadorSaludableGrasas == 0) {
                        btnCalcular.setText(R.string.tencuidado);
                        btnCalcular.setBackgroundResource(R.drawable.boton_maybesaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 1 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 2 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 1 && contadorSaludableGrasas == 2) {
                        btnCalcular.setText(R.string.noessaludable);
                        btnCalcular.setBackgroundResource(R.drawable.boton_nosaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 2 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 2 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 2 && contadorSaludableSal == 1 && contadorSaludableGrasas == 2) {
                        btnCalcular.setText(R.string.noessaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));
                        btnCalcular.setBackgroundResource(R.drawable.boton_nosaludable);

                    } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 0 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 2 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 0 && contadorSaludableGrasas == 2) {
                        btnCalcular.setText(R.string.tencuidado);
                        btnCalcular.setBackgroundResource(R.drawable.boton_maybesaludable);
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));

                    } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 2 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 2 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 2 && contadorSaludableSal == 0 && contadorSaludableGrasas == 2) {
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));
                        btnCalcular.setText(R.string.noessaludable);
                        btnCalcular.setBackgroundResource(R.drawable.boton_nosaludable);

                    } else if (contadorSaludableAzucar == 2 && contadorSaludableSal == 1 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 1 && contadorSaludableSal == 2 && contadorSaludableGrasas == 0 || contadorSaludableAzucar == 0 && contadorSaludableSal == 1 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 2 && contadorSaludableSal == 0 && contadorSaludableGrasas == 1 || contadorSaludableAzucar == 1 && contadorSaludableSal == 0 && contadorSaludableGrasas == 2 || contadorSaludableAzucar == 0 && contadorSaludableSal == 2 && contadorSaludableGrasas == 1) {
                        btnCalcular.setTextColor(getResources().getColor(R.color.white));
                        btnCalcular.setText(R.string.noessaludable);
                        btnCalcular.setBackgroundResource(R.drawable.boton_nosaludable);

                    }
                }
            }
        });
    }


    private void abrirBBDD() {
        BDname = "Comidas";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        DBComidas = bdhelper.getWritableDatabase();
    }

    private void consultarListaComidaMenuDatos() {

        productoMostrar proCom = null;
        pMostrar = new ArrayList<productoMostrar>();

        Cursor cursor = DBComidas.rawQuery("select a.nombreA,c.nombreC,a.hidratos,a.azucar,a.grasa,a.imgpro from Alimentos as a inner join Categorias as c on a.cat=c.id where a.menu=1", null);

        if (cursor.moveToFirst()) {
            do {
                proCom = new productoMostrar();
                proCom.setNombre(cursor.getString(0));
                proCom.setCategoria(cursor.getString(1));

                //Para ver si tiene una imagen para la lista
                int valor = cursor.getInt(5);
                if (valor == 0) {
                    proCom.setImg(R.drawable.fondoprodudefualt);
                } else {
                    proCom.setImg(cursor.getInt(5));
                }

                proCom.setHidratos(cursor.getInt(2));
                proCom.setAzucares(cursor.getInt(3));
                proCom.setGrasas(cursor.getInt(4));

                pMostrar.add(proCom);

            } while (cursor.moveToNext());
        }

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

        abrirBBDD();
        int i = 0;
        consultarListaComidasMenu();

        while (ComidaList.size() > i) {
            String nombre = ComidaList.get(i).getNombre();
            int conta = ComidaList.get(i).getCont();

            if (conta != 0) {
                DBComidas.execSQL("UPDATE Alimentos SET menu=1 where nombreA='" + nombre + "'");
            } else {
                DBComidas.execSQL("UPDATE Alimentos SET menu=0 where nombreA='" + nombre + "'");
            }

            i++;
        }
        DBComidas.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        abrirBBDD();
        consultarListaComidasMenu();

    }
}
