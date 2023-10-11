package test;


import java.util.concurrent.Semaphore;
import trabajadores.*;
import interfaz.Pantalla;



public class Main {
    public static void main(String[] args) {
        
        Pantalla pantalla = new Pantalla();

        Drive drive = new Drive();
        Semaphore mutexDrive = new Semaphore(1);
        Semaphore mutexCounter = new Semaphore(1);
        Game game1 = new Game(2, 2, 2, 2, 2, 2);
        float[] produccionArr = {0.34f,0.34f,2,3,0.34f};
        
        
        Studio studio1 = new Studio(600,700,9,13,4,2,
                3,1,1,2,game1,1000,mutexCounter,mutexDrive, drive, produccionArr);
        studio1.startWorkers();
       
        
       }


   }


