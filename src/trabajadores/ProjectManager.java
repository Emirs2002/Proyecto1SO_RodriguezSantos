package trabajadores;

import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;

/**
 *
 * @author emirs
 */
public class ProjectManager extends Thread {

    private boolean isWorking;
    private final int dayDuration;
    private final int paymentPerHour;
    private int paymentPerDay = 0;
    private int fault = 0;
    private Semaphore mutexStudio;
    private DayCounter counter;
    public ImageIcon trabajando;
    public ImageIcon flojeando;
    public javax.swing.JLabel pmStatus;

    public ProjectManager(int paymentPerHour, int dayDuration, Semaphore mutexStudio, DayCounter counter, javax.swing.JLabel pm) {
        this.isWorking = true;
        this.paymentPerHour = paymentPerHour;
        this.dayDuration = dayDuration;
        this.mutexStudio = mutexStudio;
        this.counter = counter;
        this.trabajando = new ImageIcon(getClass().getResource("/imagenes/PMWORKING.png"));
        this.flojeando = new ImageIcon(getClass().getResource("/imagenes/pmnotworking.png"));
        this.pmStatus = pm;
    }

    @Override
    public void run() {
        while (true) {

            //primeras 16 horas
            int hour = 0;
            while (hour < 16) {

                try {
                    int timeSleep = (this.dayDuration / 24) / 2;//30 min

                    //mira streams
                    this.isWorking = false;
                    this.pmStatus.setIcon(flojeando);

                    sleep(timeSleep);

                    //trabaja
                    this.isWorking = true;
                    sleep(timeSleep);

                    hour++;
                } catch (InterruptedException ex) {
                    ex.printStackTrace(System.out);
                }

            }

            //ultimas 8 horas
            try {
                this.isWorking = true;
                changeDaysLeft();
                this.pmStatus.setIcon(trabajando);
                sleep((8 * this.dayDuration) / 24);

                this.paymentPerDay = 24 * this.paymentPerHour; //pago

            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }

        }
    }

    public void changeDaysLeft() {
        try {
//               Actualizar el counter
            this.mutexStudio.acquire(1);

            this.counter.updateCounter("manager");

            this.mutexStudio.release();

        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public int getFault() {
        return fault;
    }

    public void setFault(int fault) {
        this.fault = fault;
    }

    public int getPaymentPerDay() {
        return paymentPerDay;
    }

    public void setPaymentPerDay(int payment) {
        this.paymentPerDay = payment;
    }
}
