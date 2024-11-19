package com.hotel.gestor.persistence;

import com.hotel.gestor.controller.HabitacionFactory;
import com.hotel.gestor.model.Cliente;
import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.model.Reserva;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoManager {

    private static final String HABITACIONES_FILE = "habitaciones.txt";
    private static final String CLIENTES_FILE = "clientes.txt";
    private static final String RESERVAS_FILE = "reservas.txt";

    // Guardar habitaciones en un archivo
    public static void guardarHabitaciones(List<Habitacion> habitaciones) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HABITACIONES_FILE))) {
            for (Habitacion habitacion : habitaciones) {
                writer.write(habitacion.toDataString());
                writer.newLine();
            }
        }
    }

    // Cargar habitaciones desde un archivo
    public static List<Habitacion> cargarHabitaciones() throws IOException {
        List<Habitacion> habitaciones = new ArrayList<>();
        crearArchivoSiNoExiste(HABITACIONES_FILE);  // Crear archivo si no existe

        try (BufferedReader reader = new BufferedReader(new FileReader(HABITACIONES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Procesar la línea y crear la habitación
                Habitacion habitacion = Habitacion.fromDataString(linea);
                if (habitacion == null) {
                    System.out.println("Línea de datos no válida: " + linea);
                } else {
                    habitaciones.add(habitacion);
                }
            }

            // Si el archivo está vacío o no tiene datos válidos, generar habitaciones nuevas
            if (habitaciones.isEmpty()) {
                HabitacionFactory habitacionFactory = new HabitacionFactory();
                habitacionFactory.generarHabitaciones();
            }
        }

        return habitaciones;
    }


    // Guardar clientes en un archivo
    public static void guardarClientes(List<Cliente> clientes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_FILE))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.toDataString());
                writer.newLine();
            }
        }
    }

    // Cargar clientes desde un archivo
    public static List<Cliente> cargarClientes() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        crearArchivoSiNoExiste(CLIENTES_FILE);  // Crear archivo si no existe
        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                clientes.add(Cliente.fromDataString(linea));
            }
        }
        return clientes;
    }

    // Guardar reservas en un archivo
    public static void guardarReservas(List<Reserva> reservas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVAS_FILE))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.toDataString());
                writer.newLine();
            }
        }
    }

    // Cargar reservas desde un archivo
    public static List<Reserva> cargarReservas() throws IOException {
        List<Reserva> reservas = new ArrayList<>();
        crearArchivoSiNoExiste(RESERVAS_FILE);  // Crear archivo si no existe
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVAS_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                reservas.add(Reserva.fromDataString(linea));
            }
        }
        return reservas;
    }

    // Método para guardar los datos en archivos de texto (.txt)
    public void guardarDatos(List<Habitacion> habitaciones, List<Cliente> clientes, List<Reserva> reservas) throws IOException {
        guardarHabitaciones(habitaciones);
        guardarClientes(clientes);
        guardarReservas(reservas);
    }

    // Método para cargar los datos desde archivos de texto (.txt)
    public void cargarDatos() throws IOException {
        // Cargar habitaciones
        cargarHabitaciones();

        // Cargar clientes
        cargarClientes();

        // Cargar reservas
        cargarReservas();
    }

    // Método para crear un archivo si no existe
    private static void crearArchivoSiNoExiste(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

