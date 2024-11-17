package com.hotel.gestor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton btnReservarHabitacion;
    private JButton btnVerHabitaciones;

    public MainView() {
        setTitle("Gestión de Reservas de Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(2, 1));

        btnReservarHabitacion = new JButton("Reservar Habitación");
        btnVerHabitaciones = new JButton("Ver Habitaciones");

        add(btnReservarHabitacion);
        add(btnVerHabitaciones);

        setLocationRelativeTo(null);
    }

    public void setReservarHabitacionAction(ActionListener actionListener) {
        btnReservarHabitacion.addActionListener(actionListener);
    }

    public void setVerHabitacionesAction(ActionListener actionListener) {
        btnVerHabitaciones.addActionListener(actionListener);
    }
}

