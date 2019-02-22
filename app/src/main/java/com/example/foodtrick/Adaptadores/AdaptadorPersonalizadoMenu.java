package com.example.foodtrick.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodtrick.Objetos.Comida;
import com.example.foodtrick.Producto;
import com.example.foodtrick.R;

import java.util.ArrayList;

public class AdaptadorPersonalizadoMenu extends ArrayAdapter {

    private Activity context;
    private ArrayList<Comida> comida;

    public AdaptadorPersonalizadoMenu(Activity context, ArrayList<Comida> comida) {
        super(context, R.layout.item_menu, comida);
        this.context = context;
        this.comida = comida;
    }

    static class ViewHolder {
        TextView txNombre;
        TextView txCategoria;
        ImageView imagen;
        ImageButton btnAddCart;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        final AdaptadorPersonalizadoMenu.ViewHolder holder;

        if (fila == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();

            fila = layoutInflater.inflate(R.layout.item_menu, null);

            holder = new AdaptadorPersonalizadoMenu.ViewHolder();

            holder.txNombre = (TextView) fila.findViewById(R.id.txNombre);
            holder.txCategoria = (TextView) fila.findViewById(R.id.txCategoria);
            holder.imagen = (ImageView) fila.findViewById(R.id.imgIcon);
            holder.btnAddCart = (ImageButton) fila.findViewById(R.id.btnAñadirCarrito);

            fila.setTag(holder);
        } else {
            holder = (AdaptadorPersonalizadoMenu.ViewHolder) fila.getTag();
        }

        fila.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.txNombre.setText(comida.get(position).getNombre());
        holder.imagen.setImageResource(comida.get(position).getImg());
        if (comida.get(position).getCont() == 0) {
            holder.btnAddCart.setBackgroundResource(R.drawable.cart_shop);
        } else {
            holder.btnAddCart.setBackgroundResource(R.drawable.cart_shop_green);
        }

        holder.txNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Producto.class);
                i.putExtra("NombreDelProducto", holder.txNombre.getText().toString());
                context.startActivity(i);
            }
        });

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Producto.class);
                i.putExtra("NombreDelProducto", holder.txNombre.getText().toString());
                context.startActivity(i);
            }
        });

        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     remove(comida.get(position));
                                                 }
                                             }
        );

        return fila;
    }
}