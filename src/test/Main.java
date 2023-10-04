package test;


import java.util.concurrent.Semaphore;
import trabajadores.Developer;
import trabajadores.Drive;

import interfaz.Pantalla;



public class Main {
    public static void main(String[] args) {
        
        Pantalla pantalla = new Pantalla();

        Drive drive = new Drive();
        Semaphore mutex = new Semaphore(1);
        Developer dev1 = new Developer("guion",0.34f,1000,mutex,10, drive);
        Developer dev2 = new Developer("guion",0.34f,1000,mutex,10,drive);
        
        dev1.start();
        dev2.start();

    }
}
