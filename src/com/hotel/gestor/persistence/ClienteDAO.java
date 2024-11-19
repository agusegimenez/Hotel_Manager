package com.hotel.gestor.persistence;

import com.hotel.gestor.model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String ARCHIVO_CLIENTES = "clientes.txt";

    // Método para cargar los clientes desde el archivo
    public List<Cliente> cargarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        File archivo = new File(ARCHIVO_CLIENTES);

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");

                    if (datos.length == 4) {
                        try {
                            int id = Integer.parseInt(datos[0].trim());
                            String nombre = datos[1].trim();
                            String email = datos[2].trim();
                            String telefono = datos[3].trim();
                            clientes.add(new Cliente(id, nombre, telefono, email));
                        } catch (NumberFormatException e) {
                            System.out.println("Error al convertir los datos del cliente: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Línea no válida en el archivo de clientes: " + linea);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }

    // Método para guardar los clientes en el archivo
    public void guardarClientes(List<Cliente> clientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getId() + "," + cliente.getNombre() + "," + cliente.getTelefono() + "," + cliente.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un nuevo cliente
    public void agregarCliente(Cliente nuevoCliente) {
        List<Cliente> clientes = cargarClientes();
        clientes.add(nuevoCliente);
        guardarClientes(clientes);
    }

    // Método para buscar un cliente por ID
    public Cliente obtenerClientePorId(int idCliente) {
        List<Cliente> clientes = cargarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null; // Si no se encuentra el cliente, se retorna null
    }
}

