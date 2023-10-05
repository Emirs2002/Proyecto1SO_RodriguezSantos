package test;


import java.util.concurrent.Semaphore;
import trabajadores.Developer;
import trabajadores.Drive;
import trabajadores.Integrator;
import trabajadores.Game;

import interfaz.Pantalla;



public class Main {
    public static void main(String[] args) {
        
        Pantalla pantalla = new Pantalla();

        Drive drive = new Drive();
        Semaphore mutex = new Semaphore(1);
        Game game1 = new Game(2, 2, 2, 2, 2, 2);
        Developer dev1 = new Developer("guion",1,1000,mutex,10, drive);
        Developer dev2 = new Developer("nivel",1,1000,mutex,10,drive);
        Developer dev3 = new Developer("sprite",1,1000,mutex,10,drive);
        Developer dev4 = new Developer("programador",1,1000,mutex,10,drive);
        Developer dev5 = new Developer("dlc",1,1000,mutex,10,drive);
        Integrator integrador1 = new Integrator(0.5f, 1000, mutex, 10, drive, game1);
        dev1.start();
        dev2.start();
        dev3.start();
        dev4.start();
        dev5.start();
        integrador1.start();

    }
}
