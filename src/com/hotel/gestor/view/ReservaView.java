package com.hotel.gestor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaView extends JFrame {
    private JTextField txtNumeroHabitacion;
    private JTextField txtNombreCliente;
    private JTextField txtTelefonoCliente;
    private JTextField txtEmailCliente;
    private JTextField txtFechaCheckIn;
    private JTextField txtFechaCheckOut;
    private JButton btnReservar;

    public ReservaView() {
        setTitle("Reservar Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));

        // Campos del formulario
        add(new JLabel("Número de Habitación:"));
        txtNumeroHabitacion = new JTextField();
        add(txtNumeroHabitacion);

        add(new JLabel("Nombre del Cliente:"));
        txtNombreCliente = new JTextField();
        add(txtNombreCliente);

        add(new JLabel("Teléfono del Cliente:"));
        txtTelefonoCliente = new JTextField();
        add(txtTelefonoCliente);

        add(new JLabel("Email del Cliente:"));
        txtEmailCliente = new JTextField();
        add(txtEmailCliente);

        add(new JLabel("Fecha Check-In (YYYY-MM-DD):"));
        txtFechaCheckIn = new JTextField();
        add(txtFechaCheckIn);

        add(new JLabel("Fecha Check-Out (YYYY-MM-DD):"));
        txtFechaCheckOut = new JTextField();
        add(txtFechaCheckOut);

        btnReservar = new JButton("Reservar");
        add(new JLabel()); // Espacio vacío
        add(btnReservar);

        setLocationRelativeTo(null);
    }

    public String getNumeroHabitacion() {
        return txtNumeroHabitacion.getText();
    }

    public String getNombreCliente() {
        return txtNombreCliente.getText();
    }

    public String getTelefonoCliente() {
        return txtTelefonoCliente.getText();
    }

    public String getEmailCliente() {
        return txtEmailCliente.getText();
    }

    public String getFechaCheckIn() {
        return txtFechaCheckIn.getText();
    }

    public String getFechaCheckOut() {
        return txtFechaCheckOut.getText();
    }

    public void setReservarAction(ActionListener actionListener) {
        btnReservar.addActionListener(actionListener);
    }
}

