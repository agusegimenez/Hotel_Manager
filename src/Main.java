import com.hotel.gestor.controller.HabitacionFactory;
import com.hotel.gestor.controller.MainController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainController controller = new MainController();
        controller.iniciar();
    }
}