package models;

public class Productos {

    private int id, stock;
    private String nombre, marca, categoria;

    public Productos(int id, int stock, String nombre, String marca, String categoria) {
        this.id = id;
        this.stock = stock;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }
    
    
    
    
}
