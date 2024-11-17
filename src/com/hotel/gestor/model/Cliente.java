package com.hotel.gestor.model;

import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private List<Reserva> reservas;  // Lista de reservas del cliente
    private Reserva reservaActual;    // Reserva actual del cliente, si existe

    public Cliente(int id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Reserva getReservaActual() {
        return reservaActual;
    }

    public void setReservaActual(Reserva reservaActual) {
        this.reservaActual = reservaActual;
    }

    @Override
    public String toString() {
        return "Cliente ID: " + id + " - Nombre: " + nombre + " - Teléfono: " + telefono + " - Email: " + email;
    }

    public String toDataString() {
        return id + "," + nombre + "," + telefono + "," + email;
    }

    public static Cliente fromDataString(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String nombre = parts[1];
        String telefono = parts[2];
        String email = parts[3];
        return new Cliente(id, nombre, telefono, email);
    }

    // Método para obtener un cliente por su ID
    public Cliente obtenerClientePorId(List<Cliente> clientes, int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;  // Si no se encuentra el cliente, devolvemos null
    }
}


