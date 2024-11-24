package com.hotel.gestor.controller;

import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.persistence.ArchivoManager;
import com.hotel.gestor.service.HabitacionService;
import com.hotel.gestor.view.MainView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<Habitacion> habitaciones;
    private MainView mainView;
    private ArchivoManager archivoManager;
    private HabitacionService habitacionService;

    public MainController() throws IOException {
        archivoManager = new ArchivoManager();
        habitacionService = new HabitacionService();
        habitaciones = habitacionService.generarHabitacionesIniciales(); // Cargar datos al iniciar el programa

        mainView = new MainView();

        // Asociar acciones a los botones
        mainView.addRegistrarReservaListener(e -> abrirVistaReserva());
        mainView.addMostrarHabitacionesListener(e -> mostrarHabitaciones());
    }

    public void iniciar() {
        mainView.setVisible(true);
    }

    private void abrirVistaReserva() {
        JOptionPane.showMessageDialog(mainView, "Funcionalidad de reserva no implementada.");
    }

    private void mostrarHabitaciones() {
        try {
            // Generar habitaciones aleatorias utilizando el servicio
            List<Habitacion> habitaciones = habitacionService.generarHabitacionesIniciales();

            // Mostrar las habitaciones en la vista principal
            mainView.mostrarHabitaciones(formatearListadoHabitaciones(habitaciones));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainView, "Error al mostrar las habitaciones: " + e.getMessage());
        }
    }

    private String formatearListadoHabitaciones(List<Habitacion> habitaciones) {
        StringBuilder sb = new StringBuilder();
        for (Habitacion h : habitaciones) {
            sb.append(h.toString()).append("\n");
        }
        return sb.toString();
    }
}




