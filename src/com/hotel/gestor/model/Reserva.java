package com.hotel.gestor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private Date fechaReserva;  // Ahora es de tipo Date
    private Date fechaCheckIn;  // Ahora es de tipo Date
    private Date fechaCheckOut; // Ahora es de tipo Date

    // Constructor actualizado para usar Date en lugar de String para las fechas
    public Reserva(int id, Habitacion habitacion, Cliente cliente, Date fechaReserva, Date fechaCheckIn, Date fechaCheckOut) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaReserva = fechaReserva;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    @Override
    public String toString() {
        return "Reserva ID: " + id + "\n" +
                "Habitación: " + habitacion.toString() + "\n" +
                "Cliente: " + cliente.toString() + "\n" +
                "Fecha Reserva: " + fechaReserva.toString() + "\n" +  // Imprime la fecha como String
                "Check-In: " + fechaCheckIn.toString() + " - Check-Out: " + fechaCheckOut.toString();
    }

    // Método para convertir la reserva a formato String
    public String toDataString() {
        // Convertimos las fechas a String en el formato adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return id + "," + habitacion.getId() + "," + cliente.getId() + "," +
                dateFormat.format(fechaReserva) + "," + dateFormat.format(fechaCheckIn) + "," + dateFormat.format(fechaCheckOut);
    }

    // Método estático para crear una Reserva a partir de un String
    public static Reserva fromDataString(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        int habitacionId = Integer.parseInt(parts[1]);
        int clienteId = Integer.parseInt(parts[2]);

        // Usamos SimpleDateFormat para convertir las fechas en formato String a Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaReserva = null;
        Date fechaCheckIn = null;
        Date fechaCheckOut = null;

        try {
            fechaReserva = dateFormat.parse(parts[3]);
            fechaCheckIn = dateFormat.parse(parts[4]);
            fechaCheckOut = dateFormat.parse(parts[5]);
        } catch (Exception e) {
            e.printStackTrace();  // Manejo básico de errores en el parsing de fechas
        }

        // Crear objetos relacionados
        Habitacion habitacion = new Habitacion(habitacionId, 0,"N/A", 0, 0, false); // Placeholder
        Cliente cliente = new Cliente(clienteId, "N/A", "N/A", "N/A"); // Placeholder

        return new Reserva(id, habitacion, cliente, fechaReserva, fechaCheckIn, fechaCheckOut);
    }
}


