package com.hotel.gestor.controller;

import com.hotel.gestor.controller.ReservaController;
import com.hotel.gestor.model.Cliente;
import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.model.Reserva;
import com.hotel.gestor.persistence.ArchivoManager;
import com.hotel.gestor.view.MainView;
import com.hotel.gestor.view.ReservaView;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private List<Habitacion> habitaciones;
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    private ArchivoManager archivoManager;
    private MainView mainView;
    private ReservaController reservaController;

    public MainController() {
        // Inicializamos las listas de habitaciones, clientes y reservas.
        habitaciones = new ArrayList<>();
        clientes = new ArrayList<>();
        reservas = new ArrayList<>();

        // Cargamos los datos de los archivos de persistencia.
        archivoManager = new ArchivoManager(habitaciones, clientes, reservas);
        archivoManager.cargarDatos(); // Cargar datos al inicio

        // Creamos la vista principal y el controlador de reservas
        mainView = new MainView();
        reservaController = new ReservaController(habitaciones, clientes, reservas);

        // Asociamos la acción de los botones de la vista principal a los métodos del controlador
        mainView.addRegistrarReservaListener(e -> abrirVistaReserva());
        mainView.addMostrarHabitacionesListener(e -> mostrarHabitacionesDisponibles());
    }

    // Inicia la vista principal
    public void iniciar() {
        mainView.setVisible(true);
    }

    // Método para abrir la vista de reservas
    private void abrirVistaReserva() {
        reservaController.iniciarReserva();
    }

    // Método para mostrar las habitaciones disponibles
    private void mostrarHabitacionesDisponibles() {
        // Podrías agregar un método en MainView para mostrar las habitaciones disponibles
        StringBuilder sb = new StringBuilder();
        for (Habitacion h : habitaciones) {
            sb.append("Habitación: ").append(h.getNumero())
                    .append(" - Estado: ").append(h.isReservada() ? "Reservada" : "Disponible")
                    .append("\n");
        }
        mainView.mostrarHabitaciones(sb.toString());
    }

    // Método para guardar los datos de las reservas y habitaciones en el archivo
    public void guardarDatos() {
        archivoManager.guardarDatos();
    }
}

