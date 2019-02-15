package com.example.foodtrick.Objetos;

public class Comida {
    private String nombre;
    private String categoria;
    private int img;

    public Comida(String nombre, String categoria, int img) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.img = img;
    }

    public Comida() {
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