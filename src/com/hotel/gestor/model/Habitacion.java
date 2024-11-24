package com.hotel.gestor.model;

import java.util.List;

public class Habitacion {
    private int id;
    private int numero;
    private String tipo; // Ejemplo: "Simple", "Doble", "Suite"
    private int capacidad;
    private double precio;
    private boolean estaReservada; // true si está ocupada, false si está disponible
    private Cliente cliente; // Cliente asociado si está ocupada

    public Habitacion(int id, int numero, String tipo, int capacidad, double precio, boolean estaReservada) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio = precio;
        this.estaReservada = estaReservada;
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

    public boolean isEstaReservada() {
        return estaReservada;
    }

    public void setEstaReservada(boolean estaReservada) {
        this.estaReservada = estaReservada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void cambiarEstado() {
        this.estaReservada = !this.estaReservada;
    }

    @Override
    public String toString() {
        String estado = estaReservada ? "Ocupada" : "Disponible";
        return "Habitación " + numero + " - " + tipo + " (" + capacidad + " personas) - $" + precio + " - " + estado;
    }

    public static Habitacion obtenerHabitacionPorId(List<Habitacion> habitaciones, int id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;
    }
}




