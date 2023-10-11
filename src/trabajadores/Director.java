package trabajadores;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author emirs
 */
public class Director extends Thread {

    private ProjectManager manager;
    private final Drive drive;
    private final int dayDuration;
    private final int paymentPerHour;
    private int paymentPerDay = 0;
    private boolean isWorking;
    private Semaphore mutexDrive;
    private Semaphore mutexStudio;
    private DayCounter counter;

    public Director(ProjectManager manager, int dayDuration, int paymentPerHour, Drive drive, Semaphore mutexDrive, Semaphore mutexStudio, DayCounter counter) {
        this.manager = manager;
        this.dayDuration = dayDuration;
        this.paymentPerHour = paymentPerHour;
        this.drive = drive;
        this.isWorking = true;
        this.mutexDrive = mutexDrive;
        this.mutexStudio = mutexStudio;
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {

            work();

            //pago
            this.paymentPerDay += 24 * this.paymentPerHour;
            System.out.println("total dia director" + this.paymentPerDay);

        }
    }

    public void work() {

        int daysLeft = this.counter.getDaysLeft();//checar estado del counter
        
        if (daysLeft == 0) {

            //mandar todos los juegos a las tiendas
            launchGames();
            
            //reestablecer deadline
            updateDeadline();

        } else {
            
            //hora random para checar que hace el PM
            int hour = getRandomHour();

            try {
                this.isWorking = true;
                System.out.println("director trabaja");
                sleep(hour);//trabaja normal hasta esta hora

                //revisa que hace el PM
                checkOnPM();

                //dormir al pm el resto del tiempo del dia
                int checkingMinutes = (25 * (this.dayDuration / 24)) / 60; //25 min

                sleep(this.dayDuration - hour - checkingMinutes); 
                System.out.println("dia termina director");

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }

    }

    public void checkOnPM() {
        System.out.println("director va a chismear al PM");
        this.isWorking = false;

        //Lo vigila durante 25 minutos
        try {

            if (this.manager.isWorking() == true) {
                System.out.println("");
                System.out.println("PM TRABAJANDO");

            } else {
                //penalizar al PM
                this.manager.setFault(this.manager.getFault() + 1);
                this.manager.setPaymentPerDay(this.manager.getPaymentPerDay() - 50);

                System.out.println("");
                System.out.println("PM VIENDO STREAMS");
                System.out.println("FALTAS: " + this.manager.getFault());

            }

            sleep((25 * (this.dayDuration / 24)) / 60); //dormir 25 min

        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }

    }
    
    public void launchGames(){
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
    }
    
    public void updateDeadline(){
        try {
//          semaforo para contador
            this.mutexStudio.acquire(1);

            this.counter.updateCounter("director");
                
            this.mutexStudio.release();

        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public int getRandomHour() {
        Random randNum = new Random();
        int randomHour = randNum.nextInt(980);
        return randomHour;
    }
}
