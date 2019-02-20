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

public class AdaptadorPersonalizado extends ArrayAdapter {

    private Activity context;
    private ArrayList<Comida> comida;

    public AdaptadorPersonalizado(Activity context, ArrayList<Comida> comida) {
        super(context, R.layout.item_lista, comida);
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
        final ViewHolder holder;

        if (fila == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();

            fila = layoutInflater.inflate(R.layout.item_lista, null);

            holder = new ViewHolder();

            holder.txNombre = (TextView) fila.findViewById(R.id.txNombre);
            holder.txCategoria = (TextView) fila.findViewById(R.id.txCategoria);
            holder.imagen = (ImageView) fila.findViewById(R.id.imgIcon);
            holder.btnAddCart = (ImageButton) fila.findViewById(R.id.btnAñadirCarrito);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder) fila.getTag();
        }

        fila.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.txNombre.setText(comida.get(position).getNombre());
        holder.txCategoria.setText("Mas información");
        holder.imagen.setImageResource(comida.get(position).getImg());
        if (comida.get(position).getCont() == 0) {
            holder.btnAddCart.setBackgroundResource(R.drawable.cart_shop);
        } else {
            holder.btnAddCart.setBackgroundResource(R.drawable.cart_shop_green);
        }

        holder.txCategoria.setOnClickListener(new View.OnClickListener() {
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
                                                     if (comida.get(position).getCont() == 0) {
                                                         comida.get(position).setCont(1);
                                                         holder.btnAddCart.setBackgroundResource(R.drawable.cart_shop_green);
                                                     } else if (comida.get(position).getCont() == 1) {
                                                         comida.get(position).setCont(0);
                                                         holder.btnAddCart.setBackgroundResource(R.drawable.cart_shop);
                                                     }
                                                 }
                                             }
        );

        return fila;
    }
}