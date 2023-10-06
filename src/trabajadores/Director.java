package trabajadores;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Semaphore mutex;

    public Director(ProjectManager manager, int dayDuration, int paymentPerHour, int daysleft, Drive drive, Semaphore mutex) {
        this.manager = manager;
        this.dayDuration = dayDuration;
        this.paymentPerHour = paymentPerHour;
        this.drive = drive;
        this.isWorking = true;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            work();
            sleep(this.dayDuration);

            //pago
            this.paymentPerDay += 24 * this.paymentPerHour;
            System.out.println("total dia manager" + this.paymentPerDay);

        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void work() {

        int daysLeft = this.manager.getDaysLeft();
        if (daysLeft == 0) {
            //mandar todos los juegos al desguace

        } else {
            //hora random para checar que hace el PM
            int hour = getRandomHour();

            try {
                this.isWorking = true;
                sleep(hour);//trabaja normal hasta esta hora

                //revisa que hace el PM
                checkOnPM();
                sleep(this.dayDuration - hour); //se consume el resto del tiempo del dia
                System.out.println("dia termina director");

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }

    }

    public void checkOnPM() {
        this.isWorking = false;
        int minute = 0;
        
        //bucle para asegurar que lo vigile durante los 25 minutos
        while (minute < 25) {

            try {

                if (this.manager.isWorking()) {

                    System.out.println("pm esta trabajando");

                } else {

                    //penalizar al PM
                    this.manager.setFault(this.manager.getFault() + 1);
                    int descuentoPM = this.manager.getPaymentPerDay() - 50; //restar de su salario
                    this.manager.setPaymentPerDay(descuentoPM);

                    System.out.println("Descuento " + descuentoPM);
                }

                sleep((this.dayDuration / 24) / 60); //dormir un minuto
                minute++;
                
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }
    }

    public int getRandomHour() {
        Random randNum = new Random();
        int randomHour = randNum.nextInt(980);
        return randomHour;
    }
}
