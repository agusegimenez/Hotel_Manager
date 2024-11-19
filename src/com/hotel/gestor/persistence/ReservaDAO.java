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

    private static final String ARCHIVO_RESERVAS = "reservas.txt";
    private static final SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Método para cargar las reservas desde el archivo
    public List<Reserva> cargarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        File archivo = new File(ARCHIVO_RESERVAS);

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length == 6) {
                        try {
                            int id = Integer.parseInt(datos[0].trim());
                            int idHabitacion = Integer.parseInt(datos[1].trim());
                            int idCliente = Integer.parseInt(datos[2].trim());
                            Date fechaReserva = fechaFormat.parse(datos[3].trim());
                            Date fechaCheckIn = fechaFormat.parse(datos[4].trim());
                            Date fechaCheckOut = fechaFormat.parse(datos[5].trim());

                            Habitacion habitacion = obtenerHabitacionPorId(idHabitacion);
                            Cliente cliente = obtenerClientePorId(idCliente);

                            if (habitacion != null && cliente != null) {
                                reservas.add(new Reserva(id, habitacion, cliente, fechaReserva, fechaCheckIn, fechaCheckOut));
                            } else {
                                System.out.println("No se encontró la habitación o cliente con los ID: " + idHabitacion + ", " + idCliente);
                            }
                        } catch (Exception e) {
                            System.out.println("Error al convertir los datos: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Línea no válida: " + linea);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return reservas;
    }

    // Método para guardar las reservas en el archivo
    public void guardarReservas(List<Reserva> reservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_RESERVAS))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.getId() + "," +
                        reserva.getHabitacion().getId() + "," +
                        reserva.getCliente().getId() + "," +
                        fechaFormat.format(reserva.getFechaReserva()) + "," +
                        fechaFormat.format(reserva.getFechaCheckIn()) + "," +
                        fechaFormat.format(reserva.getFechaCheckOut()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            if (reserva.getHabitacion().getId() == idHabitacion) {
                return true;
            }
        }
        return false;
    }

    // Método para obtener una habitación por ID
    private Habitacion obtenerHabitacionPorId(int idHabitacion) {
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<Habitacion> habitaciones = habitacionDAO.cargarHabitaciones();

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == idHabitacion) {
                return habitacion;
            }
        }
        return null;
    }

    // Método para obtener un cliente por ID
    private Cliente obtenerClientePorId(int idCliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.cargarClientes();

        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    // Método para generar un ID único para cada nueva reserva
    public int generarId() {
        List<Reserva> reservas = cargarReservas();
        if (reservas.isEmpty()) {
            return 1;
        } else {
            return reservas.get(reservas.size() - 1).getId() + 1;
        }
    }

    // Nuevo método: buscar una reserva por ID
    public Reserva buscarPorId(int idReserva) {
        List<Reserva> reservas = cargarReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getId() == idReserva) {
                return reserva;
            }
        }
        return null; // Devuelve null si no encuentra la reserva
    }

    // Nuevo método: eliminar una reserva por ID
    public void eliminarReserva(int idReserva) {
        List<Reserva> reservas = cargarReservas();
        reservas.removeIf(reserva -> reserva.getId() == idReserva); // Eliminamos la reserva con el ID especificado
        guardarReservas(reservas); // Guardamos la lista actualizada
    }
}



