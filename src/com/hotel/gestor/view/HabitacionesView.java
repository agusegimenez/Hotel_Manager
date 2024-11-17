package com.hotel.gestor.view;

import com.hotel.gestor.model.Habitacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HabitacionesView extends JFrame {
    private JTextArea txtAreaHabitaciones;

    public HabitacionesView() {
        setTitle("Estado de Habitaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        txtAreaHabitaciones = new JTextArea();
        txtAreaHabitaciones.setEditable(false);
        add(new JScrollPane(txtAreaHabitaciones), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public void mostrarHabitaciones(List<Habitacion> habitaciones) {
        StringBuilder builder = new StringBuilder();
        for (Habitacion habitacion : habitaciones) {
            builder.append(habitacion.toString()).append("\n");
        }
        txtAreaHabitaciones.setText(builder.toString());
    }

}
