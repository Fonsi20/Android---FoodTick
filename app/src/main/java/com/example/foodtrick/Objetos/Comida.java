package com.example.foodtrick.Objetos;

import java.io.Serializable;

public class Comida implements Serializable {
    private String nombre;
    private String categoria;
    private int img, cont;

    public Comida(String nombre, String categoria, int img, int cont) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.img = img;
        this.cont = cont;
    }

    public Comida() {
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}