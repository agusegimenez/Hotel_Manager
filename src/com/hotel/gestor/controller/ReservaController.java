package com.hotel.gestor.controller;

import com.hotel.gestor.model.Cliente;
import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.model.Reserva;
import com.hotel.gestor.persistence.HabitacionDAO;
import com.hotel.gestor.persistence.ReservaDAO;

import java.util.Date;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;
    private HabitacionDAO habitacionDAO;

    public ReservaController() {
        // Inicializamos los DAOs para manejar la persistencia
        this.reservaDAO = new ReservaDAO();
        this.habitacionDAO = new HabitacionDAO();
    }

    // Método para crear una reserva
    public void crearReserva(int numeroHabitacion, Cliente cliente, Date fechaReserva, String fechaCheckIn, String fechaCheckOut) {
        // Buscamos la habitación por el número
        Habitacion habitacion = habitacionDAO.buscarPorNumero(numeroHabitacion);

        // Verificamos si la habitación está disponible
        if (habitacion != null && habitacion.getEstado().equalsIgnoreCase("disponible")) {
            // Creamos la nueva reserva
            Reserva reserva = new Reserva(
                    reservaDAO.generarId(),  // Generamos un ID único para la reserva
                    habitacion,               // Asignamos la habitación seleccionada
                    cliente,                  // Asignamos el cliente
                    fechaReserva,
                    fechaCheckIn,             // Fecha de entrada
                    fechaCheckOut             // Fecha de salida
            );

            // Cambiamos el estado de la habitación a "ocupada"
            habitacion.cambiarEstado("ocupada");

            // Guardamos la reserva en el archivo
            reservaDAO.agregarReserva(reserva);

            // Actualizamos la lista de habitaciones
            habitacionDAO.guardarHabitaciones(habitacionDAO.cargarHabitaciones());
        } else {
            // Si la habitación no está disponible, lanzamos una excepción
            throw new IllegalStateException("Habitación no disponible.");
        }
    }

    // Método para listar todas las reservas
    public List<Reserva> listarReservas() {
        return reservaDAO.cargarReservas();
    }

    // Método para verificar si una habitación está reservada
    public boolean verificarDisponibilidadHabitacion(int numeroHabitacion) {
        return reservaDAO.verificarReserva(numeroHabitacion);
    }
}


