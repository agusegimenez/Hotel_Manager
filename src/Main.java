import com.hotel.gestor.controller.MainController;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.iniciar();

        // Simular flujo del programa
        controller.generarHabitacionesAleatorias();
        controller.mostrarHabitaciones();
        controller.gestionarReservas();

        // Guardar datos al final
        controller.guardarDatos();
    }
}