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
    public void crearReserva(int idHabitacion, Cliente cliente, Date fechaReserva, Date fechaCheckIn, Date fechaCheckOut) {
        // Buscamos la habitación por el ID
        Habitacion habitacion = habitacionDAO.buscarPorId(idHabitacion);

        // Verificamos si la habitación está disponible
        if (habitacion != null && !habitacion.isEstaReservada()) {
            // Creamos la nueva reserva
            Reserva reserva = new Reserva(
                    reservaDAO.generarId(),  // Generamos un ID único para la reserva
                    habitacion,               // Asignamos la habitación seleccionada
                    cliente,                  // Asignamos el cliente
                    fechaReserva,
                    fechaCheckIn,             // Fecha de entrada
                    fechaCheckOut             // Fecha de salida
            );

            // Cambiamos el estado de la habitación a "reservada" y asignamos el cliente
            habitacion.setEstaReservada(true);
            habitacion.setCliente(cliente);

            // Guardamos la reserva en el archivo
            reservaDAO.agregarReserva(reserva);

            // Actualizamos la lista de habitaciones para reflejar el cambio de estado
            List<Habitacion> habitacionesActualizadas = habitacionDAO.cargarHabitaciones();
            habitacionDAO.guardarHabitaciones(habitacionesActualizadas);
        } else {
            // Si la habitación no está disponible, lanzamos una excepción
            throw new IllegalStateException("La habitación no está disponible para la reserva.");
        }
    }

    // Método para listar todas las reservas
    public List<Reserva> listarReservas() {
        return reservaDAO.cargarReservas();
    }

    // Método para verificar si una habitación está disponible
    public boolean verificarDisponibilidadHabitacion(int idHabitacion) {
        Habitacion habitacion = habitacionDAO.buscarPorId(idHabitacion);
        return habitacion != null && !habitacion.isEstaReservada();
    }

    // Método para cancelar una reserva
    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaDAO.buscarPorId(idReserva);

        if (reserva != null) {
            // Obtenemos la habitación asociada a la reserva y la liberamos
            Habitacion habitacion = reserva.getHabitacion();
            habitacion.setEstaReservada(false);
            habitacion.setCliente(null);

            // Eliminamos la reserva
            reservaDAO.eliminarReserva(idReserva);

            // Actualizamos la lista de habitaciones para reflejar el cambio de estado
            List<Habitacion> habitacionesActualizadas = habitacionDAO.cargarHabitaciones();
            habitacionDAO.guardarHabitaciones(habitacionesActualizadas);
        } else {
            throw new IllegalArgumentException("La reserva con ID " + idReserva + " no existe.");
        }
    }
}



