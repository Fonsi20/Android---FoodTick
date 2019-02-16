package com.example.foodtrick.Objetos;

public class Categoria {
    private String nombre, descripcion;
    private int id;

    public Categoria(String nombre, String descripcion, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
    }

    public Categoria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
