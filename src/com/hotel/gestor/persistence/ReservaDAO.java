package com.hotel.gestor.persistence;

import com.hotel.gestor.model.Cliente;
import com.hotel.gestor.model.Habitacion;
import com.hotel.gestor.model.Reserva;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDAO {

    private static final String ARCHIVO_RESERVAS = "reservas.txt";  // Nombre del archivo donde se guardarán las reservas.

    // Método para cargar las reservas desde el archivo
    private static final SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");  // Formato de fecha que se utilizará.

    // Método para cargar las reservas desde el archivo
    public List<Reserva> cargarReservas(List<Habitacion> habitaciones, List<Cliente> clientes) {
        List<Reserva> reservas = new ArrayList<>();
        File archivo = new File(ARCHIVO_RESERVAS);  // Se obtiene el archivo donde están las reservas.

        // Verificamos si el archivo existe
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {  // Usamos BufferedReader para leer el archivo línea por línea.
                String linea;
                while ((linea = reader.readLine()) != null) {  // Leemos cada línea del archivo.
                    // Suponemos que cada línea tiene el formato: idReserva, idHabitacion, idCliente, fechaReserva, fechaCheckIn, fechaCheckOut
                    String[] datos = linea.split(",");  // Separamos la línea por comas, asumiendo que el archivo está en formato CSV.

                    // Verificamos que la línea tenga 6 valores
                    if (datos.length == 6) {
                        try {
                            int id = Integer.parseInt(datos[0].trim());  // El primer valor es el ID de la reserva
                            int idHabitacion = Integer.parseInt(datos[1].trim());  // El segundo valor es el ID de la habitación
                            int idCliente = Integer.parseInt(datos[2].trim());  // El tercer valor es el ID del cliente

                            // Convertimos el String de la fecha de reserva a un objeto Date
                            Date fechaReserva = fechaFormat.parse(datos[3].trim());  // El cuarto valor es la fecha de la reserva

                            // Los valores de check-in y check-out también se convierten a Date
                            Date fechaCheckIn = fechaFormat.parse(datos[4].trim());  // El quinto valor es la fecha de check-in
                            Date fechaCheckOut = fechaFormat.parse(datos[5].trim());  // El sexto valor es la fecha de check-out

                            // Buscar la habitacion y el cliente correspondientes a sus IDs
                            Habitacion habitacion = habitacion.obtenerHabitacionPorId(habitaciones, idHabitacion);
                            Cliente cliente = cliente.obtenerClientePorId(clientes, idCliente);

                            // Verificamos que los objetos habitacion y cliente sean válidos
                            if (habitacion != null && cliente != null) {
                                // Creamos una nueva reserva con los datos leídos
                                reservas.add(new Reserva(id, habitacion, cliente, fechaReserva, fechaCheckIn, fechaCheckOut));
                            } else {
                                System.out.println("No se encontró la habitación o cliente con los ID: " + idHabitacion + ", " + idCliente);
                            }
                        } catch (Exception e) {
                            System.out.println("Error al convertir los datos: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Línea no válida: " + linea);  // Si la línea no tiene 6 valores, la ignoramos o podemos manejarla.
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  // Manejo básico de errores al leer el archivo.
            }
        }

        return reservas;  // Devolvemos la lista de reservas cargadas.
    }


    // Método para guardar las reservas en el archivo
    public void guardarReservas(List<Reserva> reservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_RESERVAS))) {
            for (Reserva reserva : reservas) {
                // Guardamos los datos de la reserva en formato CSV (idCliente, idHabitacion, fechaReserva)
                writer.write(reserva.getCliente().getId() + "," + reserva.getHabitacion().getId() + "," + reserva.getFechaReserva());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();  // Manejo básico de errores
        }
    }

    // Método para agregar una nueva reserva
    public void agregarReserva(Reserva nuevaReserva) {
        List<Reserva> reservas = cargarReservas();
        reservas.add(nuevaReserva);
        guardarReservas(reservas);
    }

    // Método para verificar si una habitación ya está reservada
    public boolean verificarReserva(int idHabitacion) {
        List<Reserva> reservas = cargarReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getId() == idHabitacion) {
                return true;  // Si ya existe una reserva para esta habitación, retornamos true
            }
        }
        return false;  // Si no se encuentra ninguna reserva, retornamos false
    }

    // Método para generar un ID único para cada nueva reserva
    public int generarId() {
        List<Reserva> reservas = cargarReservas();
        // Si no hay reservas, el ID será 1, de lo contrario, se incrementa el ID del último registro
        if (reservas.isEmpty()) {
            return 1;
        } else {
            // Se obtiene el ID de la última reserva y se le suma 1
            return reservas.get(reservas.size() - 1).getId() + 1;
        }
    }
}

