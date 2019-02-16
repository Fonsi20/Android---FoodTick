package com.example.foodtrick.Objetos;

public class productoMostrar {

    private String nombre;
    private String categoria;
    private int img;
    private int grasas;
    private int hidratos;
    private int azucares;

    public productoMostrar() {
    }

    public productoMostrar(String nombre, String categoria, int img, int grasas, int hidratos, int azucares) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.img = img;
        this.grasas = grasas;
        this.hidratos = hidratos;
        this.azucares = azucares;
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

    public int getGrasas() {
        return grasas;
    }

    public void setGrasas(int grasas) {
        this.grasas = grasas;
    }

    public int getHidratos() {
        return hidratos;
    }

    public void setHidratos(int hidratos) {
        this.hidratos = hidratos;
    }

    public int getAzucares() {
        return azucares;
    }

    public void setAzucares(int azucares) {
        this.azucares = azucares;
    }
}
