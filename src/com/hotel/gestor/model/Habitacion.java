package com.hotel.gestor.model;

import java.util.List;

public class Habitacion {
    private int id;
    private int numero;
    private String tipo; // Ejemplo: "Simple", "Doble", "Suite"
    private int capacidad;
    private double precio;
    private String estado; // "disponible" o "ocupada"
    private Cliente cliente;  // Cliente asociado si está ocupada

    public Habitacion(int id, int numero, String tipo, int capacidad, double precio, String estado) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio = precio;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Habitación " + numero + " - " + tipo + " (" + capacidad + " personas) - $" + precio + " - " + estado;
    }

    public String toDataString() {
        return id + "," + tipo + "," + capacidad + "," + precio + "," + estado;
    }

    public static Habitacion fromDataString(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        int numero = Integer.parseInt(parts[1]);
        String tipo = parts[2];
        int capacidad = Integer.parseInt(parts[3]);
        double precio = Double.parseDouble(parts[4]);
        String estado = parts[5];
        return new Habitacion(id, numero, tipo, capacidad, precio, estado);
    }

    // Método para obtener una habitación por su ID
    private Habitacion obtenerHabitacionPorId(List<Habitacion> habitaciones, int id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;  // Si no se encuentra la habitación, devolvemos null
    }
}


