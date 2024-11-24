package com.hotel.gestor.controller;

import com.hotel.gestor.model.Cliente;
import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.model.Reserva;
import com.hotel.gestor.persistence.HabitacionDAO;
import com.hotel.gestor.persistence.ReservaDAO;

import java.util.Date;
import java.util.List;

public class ReservaController {
    private final ReservaDAO reservaDAO;
    private final HabitacionDAO habitacionDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
        this.habitacionDAO = new HabitacionDAO();
    }

    // Crear una reserva
    public void crearReserva(int idHabitacion, Cliente cliente, Date fechaReserva, Date fechaCheckIn, Date fechaCheckOut) {
        Habitacion habitacion = habitacionDAO.buscarPorId(idHabitacion);

        if (habitacion != null && !habitacion.isEstaReservada()) {
            Reserva reserva = new Reserva(
                    reservaDAO.generarId(),
                    habitacion,
                    cliente,
                    fechaReserva,
                    fechaCheckIn,
                    fechaCheckOut
            );

            habitacion.setEstaReservada(true);
            habitacion.setCliente(cliente);

            reservaDAO.agregarReserva(reserva);
            habitacionDAO.actualizarHabitacion(habitacion); // Actualizar el estado de la habitación
        } else {
            throw new IllegalStateException("La habitación no está disponible.");
        }
    }

    // Listar todas las reservas
    public List<Reserva> listarReservas() {
        return reservaDAO.cargarReservas();
    }

    // Verificar disponibilidad de una habitación
    public boolean verificarDisponibilidadHabitacion(int idHabitacion) {
        Habitacion habitacion = habitacionDAO.buscarPorId(idHabitacion);
        return habitacion != null && !habitacion.isEstaReservada();
    }

    // Cancelar una reserva
    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaDAO.buscarPorId(idReserva);

        if (reserva != null) {
            Habitacion habitacion = reserva.getHabitacion();
            habitacion.setEstaReservada(false);
            habitacion.setCliente(null);

            reservaDAO.eliminarReserva(idReserva);
            habitacionDAO.actualizarHabitacion(habitacion);
        } else {
            throw new IllegalArgumentException("La reserva con ID " + idReserva + " no existe.");
        }
    }
}




