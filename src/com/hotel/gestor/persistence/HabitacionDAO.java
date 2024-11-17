package com.hotel.gestor.persistence;

import com.hotel.gestor.model.Habitacion;

import java.io.*;
import java.util.*;

public class HabitacionDAO {
    private static final String ARCHIVO_HABITACIONES = "habitaciones.txt";

    public List<Habitacion> cargarHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_HABITACIONES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                int numero = Integer.parseInt(datos[1]);
                String tipo = datos[2];
                int capacidad = Integer.parseInt(datos[3]);
                double precio = Double.parseDouble(datos[4]);
                String estado = datos[5];
                habitaciones.add(new Habitacion(id, numero, tipo, capacidad, precio, estado));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar habitaciones: " + e.getMessage());
        }
        return habitaciones;
    }

    public void guardarHabitaciones(List<Habitacion> habitaciones) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HABITACIONES))) {
            for (Habitacion habitacion : habitaciones) {
                bw.write(habitacion.getId() + ";" +
                        habitacion.getNumero() + ";" +
                        habitacion.getTipo() + ";" +
                        habitacion.getCapacidad() + ";" +
                        habitacion.getPrecio() + ";" +
                        habitacion.getEstado());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar habitaciones: " + e.getMessage());
        }
    }

    public Habitacion buscarPorId(int id) {
        List<Habitacion> habitaciones = cargarHabitaciones();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;
    }
}
