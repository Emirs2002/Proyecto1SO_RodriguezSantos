package test;

import interfaz.Pantalla;
import java.util.concurrent.Semaphore;
import trabajadores.*;

public class Main {

    public static void main(String[] args) {

        Pantalla pantalla = new Pantalla();

        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);

//        Semaphore mutexDrive = new Semaphore(1);
//        Semaphore mutexCounter = new Semaphore(1);
//        Game gameB = new Game(2, 2, 2, 2, 2, 2);
//        float[] productionArrB = {0.34f, 0.34f, 2, 3, 0.34f};
//
//        Studio studio1 = new Studio(350000, 700000, 9, 13, 2, 1,
//                2, 2, 2, 3, gameB, 1000, mutexCounter, mutexDrive, productionArrB);
//        
//        studio1.start();

//        String[] contenido = {"1500", "1","3","1","2","1","4","17","2","1","1","2","2","4","10"};
//        String path = "OperativosProyecto1.txt";
//        ManejoArchivo file = new ManejoArchivo();
//        file.writeTxt(path, contenido);
//        
//        String[] nya = file.readTxt(path);
//        for (int i = 0; i < nya.length; i++) {
//            System.out.println(nya[i]);
//        }
        
    }

}
