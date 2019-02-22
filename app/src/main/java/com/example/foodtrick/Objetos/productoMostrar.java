package com.example.foodtrick.Objetos;

public class productoMostrar {

    private String nombre;
    private String categoria;
    private int img;
    private float grasas;
    private float hidratos;
    private float azucares;

    public productoMostrar() {
    }

    public productoMostrar(String nombre, String categoria, int img, float grasas, float hidratos, float azucares) {
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

    public float getGrasas() {
        return grasas;
    }

    public void setGrasas(float grasas) {
        this.grasas = grasas;
    }

    public float getHidratos() {
        return hidratos;
    }

    public void setHidratos(float hidratos) {
        this.hidratos = hidratos;
    }

    public float getAzucares() {
        return azucares;
    }

    public void setAzucares(float azucares) {
        this.azucares = azucares;
    }
}
