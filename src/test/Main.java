package test;

import java.util.concurrent.Semaphore;
import trabajadores.Developer;


public class Main {
    public static void main(String[] args) {
        
        Semaphore mutex = new Semaphore(1);
        Developer dev1 = new Developer("guion",0.34f,1000,mutex,10);
        
        dev1.start();
    }
}
