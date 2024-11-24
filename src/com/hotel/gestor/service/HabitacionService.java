package com.hotel.gestor.service;

import com.hotel.gestor.model.Habitacion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionService {
    private static final String ARCHIVO_HABITACIONES = "habitaciones.txt";
    private List<Habitacion> habitaciones;

    public HabitacionService() {
        habitaciones = generarHabitacionesIniciales();
    }

    public List<Habitacion> generarHabitacionesIniciales() {
        List<Habitacion> habitaciones = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            int numero = i; // Asignar el número de habitación igual al índice.
            String tipo;
            int capacidad;
            double precio;

            // Alternar tipos, capacidades y precios según el número de habitación.
            if (i % 3 == 1) {
                tipo = "Simple";
                capacidad = 1;
                precio = 50.0;
            } else if (i % 3 == 2) {
                tipo = "Doble";
                capacidad = 2;
                precio = 75.0;
            } else {
                tipo = "Suite";
                capacidad = 4;
                precio = 120.0;
            }

            boolean estaReservada = false; // Todas las habitaciones empiezan disponibles.
            Habitacion habitacion = new Habitacion(i, numero, tipo, capacidad, precio, estaReservada);
            habitaciones.add(habitacion);
        }

        return habitaciones;
    }


    public void guardarHabitaciones() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_HABITACIONES))) {
            for (Habitacion habitacion : habitaciones) {
                writer.write(habitacion.getNumero() + "," + habitacion.isEstaReservada());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
