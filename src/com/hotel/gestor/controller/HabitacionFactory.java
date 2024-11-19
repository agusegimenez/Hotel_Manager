package com.hotel.gestor.controller;

import com.hotel.gestor.model.Habitacion;

import java.io.*;
import java.util.*;

public class HabitacionFactory {
    private static final String FILE_PATH = "habitaciones.txt"; // Ruta del archivo
    private static Random random = new Random();

    // Lista de tipos de habitaciones posibles
    private static final String[] TIPOS = {"Simple", "Doble", "Suite"};

    // Genera y guarda un número determinado de habitaciones aleatorias en el archivo
    public static void generarHabitaciones() throws IOException {
        List<Habitacion> habitacionesExistentes = cargarHabitacionesExistentes();
        Set<Integer> idsExistentes = new HashSet<>();
        for (Habitacion habitacion : habitacionesExistentes) {
            idsExistentes.add(habitacion.getId());
        }

        List<Habitacion> habitacionesNuevas = new ArrayList<>();
        int idCounter = habitacionesExistentes.size() + 1;

        // Generar habitaciones hasta que tengamos la cantidad deseada sin duplicar
        while (habitacionesNuevas.size() < 20) {
            Habitacion habitacion = generarHabitacionAleatoria(idCounter++, idsExistentes);
            if (habitacion != null) {
                habitacionesNuevas.add(habitacion);
            }
        }

        // Guardamos las habitaciones generadas en el archivo
        guardarHabitacionesEnArchivo(habitacionesExistentes, habitacionesNuevas);
    }

    // Genera una habitación aleatoria, pero la valida para evitar duplicados
    private static Habitacion generarHabitacionAleatoria(int id, Set<Integer> idsExistentes) {
        int numero;
        // Generar un número único para la habitación
        do {
            numero = random.nextInt(100) + 1; // Generar número aleatorio entre 1 y 100
        } while (idsExistentes.contains(numero)); // Repetir si el número ya existe en el conjunto

        // Ahora ya tenemos un número único
        String tipo = TIPOS[random.nextInt(TIPOS.length)]; // Tipo aleatorio
        int capacidad = random.nextInt(3) + 1; // Capacidad aleatoria entre 1 y 3 personas
        double precio = 50 + (random.nextInt(100) * 1.0); // Precio aleatorio entre 50 y 150
        boolean estaReservada = random.nextBoolean(); // Estado aleatorio (reservada o disponible)

        return new Habitacion(id, numero, tipo, capacidad, precio, estaReservada);
    }

    // Guarda las habitaciones existentes y las nuevas en el archivo
    private static void guardarHabitacionesEnArchivo(List<Habitacion> habitacionesExistentes, List<Habitacion> habitacionesNuevas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Primero, escribe las habitaciones existentes
            for (Habitacion habitacion : habitacionesExistentes) {
                writer.write(habitacion.toDataString());
                writer.newLine();
            }

            // Luego, escribe las nuevas habitaciones
            for (Habitacion habitacion : habitacionesNuevas) {
                writer.write(habitacion.toDataString());
                writer.newLine();
            }
        }
    }

    // Carga las habitaciones existentes desde el archivo
    private static List<Habitacion> cargarHabitacionesExistentes() throws IOException {
        List<Habitacion> habitaciones = new ArrayList<>();
        File archivo = new File(FILE_PATH);

        // Verificar si el archivo está vacío
        if (archivo.length() == 0) {
            System.out.println("El archivo está vacío. Generando habitaciones...");
            generarHabitaciones();  // Llamar al método para generar habitaciones aleatorias
            return cargarHabitacionesExistentes();  // Volver a cargar después de generar
        }

        // Si el archivo no está vacío, cargamos las habitaciones
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            habitaciones.add(Habitacion.fromDataString(line));
        }
        reader.close();
        return habitaciones;
    }
}


