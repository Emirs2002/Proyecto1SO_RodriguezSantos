package trabajadores;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

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
    private int benefit = 0;
    public ImageIcon trabajando;
    public ImageIcon chismeando;
    public javax.swing.JLabel directorStatus;
    public javax.swing.JTextField juegosStandard;
    public javax.swing.JTextField juegosDLC;
    public javax.swing.JTextField faltas;


    

    public Director(ProjectManager manager, int dayDuration, int paymentPerHour, Drive drive, Semaphore mutexDrive, Semaphore mutexStudio,
            DayCounter counter, javax.swing.JLabel directorStatus,  javax.swing.JTextField juegosS, javax.swing.JTextField juegosD, javax.swing.JTextField faltas) {
        this.manager = manager;
        this.dayDuration = dayDuration;
        this.paymentPerHour = paymentPerHour;
        this.drive = drive;
        this.isWorking = true;
        this.mutexDrive = mutexDrive;
        this.mutexStudio = mutexStudio;
        this.counter = counter;
        this.trabajando = new ImageIcon(getClass().getResource("/imagenes/DIRECTOR_WORKING.png"));
        this.chismeando = new ImageIcon(getClass().getResource("/imagenes/DIRECTOR_CHISMEANDO.png"));
        this.directorStatus = directorStatus;
        this.juegosStandard = juegosS;
        this.juegosDLC = juegosD;
        this.faltas = faltas;
        
    }

    @Override
    public void run() {
        while (true) {

            work();

            //pago
            this.paymentPerDay = 24 * this.paymentPerHour;

        }
    }

    public void work() {

        int daysLeft = this.counter.getDaysLeft();//checar estado del counter
        
        if (daysLeft == 0) {
            this.isWorking = true;
            this.directorStatus.setIcon(this.trabajando);
                
            //mandar todos los juegos a las tiendas
            launchGames();
            
//            tarda 24 horas
            try {
                sleep(this.dayDuration);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
            
            //reestablecer deadline
            updateDeadline();
            
            
        } else {
            
            //hora random para checar que hace el PM
            int hour = getRandomHour();

            try {
                this.isWorking = true;
                this.directorStatus.setIcon(this.trabajando);
//                this.directorStatus.setText("trabajando");
                System.out.println("");
                System.out.println("director trabaja");
                sleep(hour);//trabaja normal hasta esta hora
                
                

                //revisa que hace el PM
                checkOnPM();

                //dormir al pm el resto del tiempo del dia
                int checkingMinutes = (25 * (this.dayDuration / 24)) / 60; //25 min

                sleep(this.dayDuration - hour - checkingMinutes); 
                System.out.println("");
                System.out.println("dia termina director");

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }

    }

    public void checkOnPM() {
        System.out.println("director va a chismear al PM");
        this.isWorking = false;
        this.directorStatus.setIcon(this.chismeando);
//        this.directorStatus.setText("chismneando");

        //Lo vigila durante 25 minutos
        try {

            if (this.manager.isWorking() == true) {
                System.out.println("");
                System.out.println("PM TRABAJANDO");

            } else {
                //penalizar al PM
                this.manager.setFault(this.manager.getFault() + 1);
                this.faltas.setText(Integer.toString(this.manager.getFault()));
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

                this.benefit = this.drive.launchGames();

                this.mutexDrive.release(); //signal
                
                this.juegosStandard.setText(Integer.toString(this.drive.standardGame));
                this.juegosDLC.setText(Integer.toString(this.drive.dlcGame));
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

    public int getPaymentPerDay() {
        return paymentPerDay;
    }

    public void setPaymentPerDay(int paymentPerDay) {
        this.paymentPerDay = paymentPerDay;
    }

    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }
    
    
}
