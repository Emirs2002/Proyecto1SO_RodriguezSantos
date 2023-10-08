
package trabajadores;

import java.util.concurrent.Semaphore;

/**
 *
 * @author emirs
 */
public class ProjectManager extends Thread {
    private final int deadline;
    private int daysLeft;
    private boolean isWorking;
    private final int dayDuration;
    private final int paymentPerHour;
    private int paymentPerDay = 0;
    private int fault=0;
    private Semaphore mutexStudio;
    

    public ProjectManager(int deadline, int paymentPerHour, int dayDuration, Semaphore mutexStudio) {
        this.daysLeft = deadline;
        this.deadline = deadline;
        this.isWorking = true;
        this.paymentPerHour = paymentPerHour;
        this.dayDuration = dayDuration;
        this.mutexStudio = mutexStudio;
    }
    
    @Override
    public void run(){
        while(true){
            System.out.println("pago manager inicio: " + this.paymentPerDay);
            
            //primeras 16 horas
            int hour = 0;
            while(hour < 16){ 
            
                try {
                    int timeSleep = (this.dayDuration/24)/2;//30 min
                    
                    
                    //mira streams
                    this.isWorking = false;
                    
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
                changeDeadline();
                this.isWorking = true;
                sleep((8*this.dayDuration)/24);
                
                this.paymentPerDay += 24 * this.paymentPerHour; //pago
                System.out.println("Pago manager final: " + this.paymentPerDay);
            
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
               
  
        }
    }
    
    
    public void changeDeadline(){
        if (this.daysLeft == 0){
            this.daysLeft = this.deadline; //esto lo tiene que hacer el director
            System.out.println("Nueva deadline: " + this.daysLeft);
        }else{
            this.daysLeft--;
            System.out.println("deadline: " + this.daysLeft);
        }
        
        
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
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
    
    public int getPaymentPerDay(){
        return paymentPerDay;
    }
    
    public void setPaymentPerDay(int payment){
        this.paymentPerDay = payment;
    }
}
