package trabajadores;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author emirs
 */
public class Director extends Thread {

    private ProjectManager manager;
    private Drive drive;
    private final int dayDuration;
    private final int paymentPerHour;
    private int paymentPerDay = 0;
    private boolean isWorking;
    private Semaphore mutexDrive;
    private Semaphore mutexStudio;

    public Director(ProjectManager manager, int dayDuration, int paymentPerHour, Drive drive, Semaphore mutexDrive, Semaphore mutexStudio) {
        this.manager = manager;
        this.dayDuration = dayDuration;
        this.paymentPerHour = paymentPerHour;
        this.drive = drive;
        this.isWorking = true;
        this.mutexDrive = mutexDrive;
        this.mutexStudio = mutexStudio;
    }

    @Override
    public void run() {
        while (true) {

            try {
                work();
                sleep(this.dayDuration);

                //pago
                this.paymentPerDay += 24 * this.paymentPerHour;
                System.out.println("total dia director" + this.paymentPerDay);

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public void work() {

        int daysLeft = this.manager.getDaysLeft();
        if (daysLeft == 0) {

            //mandar todos los juegos al desguace
            try {
//          se activa el semaforo para editar los juegos
                this.mutexDrive.acquire(1); //wait

                this.drive.launchGames();

                this.mutexDrive.release(); //signal
                System.out.println("juegos enviados");
                sleep(this.dayDuration); //spends 24 hours

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        } else {
            //hora random para checar que hace el PM
            int hour = getRandomHour();

            try {
                this.isWorking = true;
                System.out.println("director trabaja");
                sleep(hour);//trabaja normal hasta esta hora

                //revisa que hace el PM
                checkOnPM();
                int checkingMinutes = (25 * (this.dayDuration / 24)) / 60; //

                sleep(this.dayDuration - hour - checkingMinutes); //duerme el resto del tiempo del dia
                System.out.println("dia termina director");

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }

    }

    public void checkOnPM() {
        System.out.println("director va a chismear al PM");
        this.isWorking = false;
        int minute = 0;
        int faults = 0;

        //bucle para asegurar que lo vigile durante los 25 minutos
        while (minute < 25) {

            try {

                if (this.manager.isWorking() == true) {

                    System.out.println("pm esta trabajando");

                } else {
                    //penalizar al PM
                    faults++;
                    this.manager.setFault(this.manager.getFault() + 1);

                    System.out.println("PM VIENDO STREAMS");
                    System.out.println("FALTAS: " + this.manager.getFault());
                    
                }
                
                sleep((this.dayDuration / 24) / 60); //dormir un minuto
                minute++;

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }
        int descuentoPM = faults *50;
        this.manager.setPaymentPerDay(this.manager.getPaymentPerDay() - descuentoPM);
        
    }

    public int getRandomHour() {
        Random randNum = new Random();
        int randomHour = randNum.nextInt(980);
        return randomHour;
    }
}
