package com.hotel.gestor.persistence;

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
        try (BufferedReader reader = new BufferedReader(new FileReader(HABITACIONES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                habitaciones.add(Habitacion.fromDataString(linea));
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
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVAS_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                reservas.add(Reserva.fromDataString(linea));
            }
        }
        return reservas;
    }
}
