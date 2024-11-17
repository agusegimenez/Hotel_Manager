package com.hotel.gestor.controller;

import com.hotel.gestor.model.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class HabitacionFactory {
    private static int contadorId = 1; // Contador para asignar ids únicos a las habitaciones

    public static List<Habitacion> generarHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String[] tipos = {"Simple", "Doble", "Suite"};

        for (int i = 1; i <= 20; i++) {
            String tipo = tipos[i % 3];
            int capacidad = tipo.equals("Suite") ? 4 : (tipo.equals("Doble") ? 2 : 1);
            double precio = tipo.equals("Suite") ? 300 : (tipo.equals("Doble") ? 150 : 80);

            // Crear una nueva habitación con el id generado por el contador
            Habitacion habitacion = new Habitacion(contadorId++, i, tipo, capacidad, precio, "disponible");

            habitaciones.add(habitacion);
        }
        return habitaciones;
    }
}

