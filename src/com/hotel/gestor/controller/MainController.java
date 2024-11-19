package com.hotel.gestor.controller;

import com.hotel.gestor.model.Cliente;
import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.model.Reserva;
import com.hotel.gestor.persistence.ArchivoManager;
import com.hotel.gestor.view.MainView;
import com.hotel.gestor.view.ReservaView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private List<Habitacion> habitaciones;
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    private ArchivoManager archivoManager;
    private MainView mainView;
    private ReservaView reservaView;
    private ReservaController reservaController;
    private HabitacionFactory habitacionFactory;

    public MainController() throws IOException {
        // Inicializamos las listas de habitaciones, clientes y reservas
        cargarDatos();
        // Inicializamos ArchivoManager y cargamos los datos desde los archivos
        archivoManager = new ArchivoManager();
        cargarDatos(); // Cargar datos al iniciar el programa

        // Creamos la vista principal y el controlador de reservas
        mainView = new MainView();
        reservaController = new ReservaController();

        // Asociamos la acción de los botones de la vista principal a los métodos del controlador
        mainView.addRegistrarReservaListener(e -> abrirVistaReserva());
        mainView.addMostrarHabitacionesListener(e -> {
            try {
                mostrarHabitaciones();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    // Inicia la vista principal
    public void iniciar() {
        mainView.setVisible(true);
    }

    // Método para abrir la vista de reservas
    public void abrirVistaReserva() {
        reservaView.setVisible(true);
    }

    // Método para mostrar todas las habitaciones (disponibles y reservadas)
    public void mostrarHabitaciones() throws IOException {
        // Asegurémonos de que las habitaciones están cargadas correctamente desde el archivo
        habitaciones = archivoManager.cargarHabitaciones();

        StringBuilder sb = new StringBuilder("Listado de Habitaciones:\n");
        for (Habitacion h : habitaciones) {
            sb.append("Habitación: ").append(h.getNumero())
                    .append(" - Estado: ").append(h.isEstaReservada() ? "Reservada" : "Disponible")
                    .append("\n");
        }
        mainView.mostrarHabitaciones(sb.toString());
    }

    // Método para guardar los datos al cerrar el programa
    public void guardarDatos() throws IOException {
        archivoManager.guardarDatos(habitaciones, clientes, reservas);
    }

    // Método para cargar los datos al iniciar el programa
    public void cargarDatos() throws IOException {
        // Asegurémonos de que se carguen correctamente las habitaciones, clientes y reservas
        habitaciones = archivoManager.cargarHabitaciones();
        clientes = archivoManager.cargarClientes();
        reservas = archivoManager.cargarReservas();
    }
}



