
package trabajadores;

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
    

    public ProjectManager(int deadline, int paymentPerHour, int dayDuration) {
        this.daysLeft = deadline;
        this.deadline = deadline;
        this.isWorking = true;
        this.paymentPerHour = paymentPerHour;
        this.dayDuration = dayDuration;
    }
    
    @Override
    public void run(){
        while(true){
            
            //primeras 16 horas
            int hour = 0;
            while(hour < 16){ 
            
                try {

                    //mira streams
                    this.isWorking = false;
                    System.out.println("viendo streams");
                    sleep((this.dayDuration/24)/2); //30 min

                    //trabaja
                    this.isWorking = true;
                    System.out.println("trabajandooo");
                    sleep((this.dayDuration/24)/2); //30 min
                    
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
                
                System.out.println("dia termina manager");
                this.paymentPerDay += 24 * this.paymentPerHour; //pago
                System.out.println("total dia manager" + this.paymentPerDay);
            
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
               
  
        }
    }
    
    
    public void changeDeadline(){
        if (this.daysLeft == 0){
            this.daysLeft = this.deadline;
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
