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

    public boolean isEstaReservada() { // Cambio el getter para seguir la convención booleana
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

    public void cambiarEstado() { // Cambio para alternar entre reservado y disponible
        this.estaReservada = !this.estaReservada;
    }

    @Override
    public String toString() {
        String estado = estaReservada ? "Ocupada" : "Disponible";
        return "Habitación " + numero + " - " + tipo + " (" + capacidad + " personas) - $" + precio + " - " + estado;
    }

    public String toDataString() {
        return id + "," + numero + "," + tipo + "," + capacidad + "," + precio + "," + estaReservada;
    }

    public static Habitacion fromDataString(String data) {
        String[] parts = data.split(",");

        // Verificar que la línea tiene 6 partes (como se espera)
        if (parts.length != 6) {
            System.err.println("Línea de datos no válida: " + data);
            return null;  // Si la línea no tiene el formato adecuado, devolvemos null
        }

        int id = parseIntSafe(parts[0]);
        int numero = parseIntSafe(parts[1]);
        String tipo = parts[2].trim(); // Asegurarse de eliminar espacios innecesarios
        int capacidad = parseIntSafe(parts[3]);
        double precio = parseDoubleSafe(parts[4]);
        boolean estaReservada = Boolean.parseBoolean(parts[5].trim());

        return new Habitacion(id, numero, tipo, capacidad, precio, estaReservada);
    }


    // Método seguro para parsear enteros, que devuelve 0 en caso de que haya un error
    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;  // Valor por defecto si no se puede parsear
        }
    }

    // Método seguro para parsear dobles, que devuelve 0.0 en caso de que haya un error
    private static double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;  // Valor por defecto si no se puede parsear
        }
    }


    // Método para obtener una habitación por su ID
    public static Habitacion obtenerHabitacionPorId(List<Habitacion> habitaciones, int id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null; // Si no se encuentra la habitación, devolvemos null
    }
}



