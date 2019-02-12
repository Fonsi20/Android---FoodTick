package com.example.foodtrick;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorPersonalizado extends ArrayAdapter {

    private Activity context;
    private Comida[] comida;

    public AdaptadorPersonalizado(Activity context, Comida[] comida) {
        super(context, R.layout.item_lista, comida);
        this.context = context;
        this.comida = comida;
    }

    static class ViewHolder {
        TextView txNombre;
        TextView txCategoria;
        ImageView imagen;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        if (fila == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            fila = layoutInflater.inflate(R.layout.item_lista, null);

            holder = new ViewHolder();

            holder.txNombre = (TextView) fila.findViewById(R.id.txNombre);
            holder.txCategoria = (TextView) fila.findViewById(R.id.txCategoria);
            holder.imagen = (ImageView) fila.findViewById(R.id.imgIcon);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder) fila.getTag();
        }

        fila.setBackgroundColor(context.getResources().getColor(R.color.white));

        holder.txNombre.setText(comida[position].getNombre());
        holder.txCategoria.setText(comida[position].getCategoria());
        holder.imagen.setImageResource(comida[position].getImg());

        return fila;
    }
}