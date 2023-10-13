package test;

import interfaz.Pantalla;
import java.util.concurrent.Semaphore;
import trabajadores.*;

public class Main {

    public static void main(String[] args) {

        Pantalla pantalla = new Pantalla();

        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
        
    }

}
