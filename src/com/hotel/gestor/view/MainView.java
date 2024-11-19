package com.hotel.gestor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton btnReservarHabitacion;
    private JButton btnVerHabitaciones;
    private JTextArea areaHabitaciones;

    public MainView() {
        setTitle("Gestión de Reservas de Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        btnReservarHabitacion = new JButton("Reservar Habitación");
        btnVerHabitaciones = new JButton("Ver Habitaciones");
        panelBotones.add(btnReservarHabitacion);
        panelBotones.add(btnVerHabitaciones);

        areaHabitaciones = new JTextArea();
        areaHabitaciones.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaHabitaciones);

        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public void addRegistrarReservaListener(ActionListener actionListener) {
        btnReservarHabitacion.addActionListener(actionListener);
    }

    public void addMostrarHabitacionesListener(ActionListener actionListener) {
        btnVerHabitaciones.addActionListener(actionListener);
    }

    public void mostrarHabitaciones(String habitaciones) {
        areaHabitaciones.setText(habitaciones);
    }
}



