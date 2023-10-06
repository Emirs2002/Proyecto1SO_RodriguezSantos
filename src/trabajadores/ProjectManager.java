package trabajadores;

/**
 *
 * @author emirs
 */
public class ProjectManager extends Thread {
    private int deadline;
    private int daysLeft;
    private boolean isWorking;
    private final int dayDuration;
    private final int paymentPerHour;

    public ProjectManager(int deadline, int daysLeft, int paymentPerHour, int dayDuration) {
        this.deadline = deadline;
        this.daysLeft = daysLeft;
        this.isWorking = true;
        this.paymentPerHour = paymentPerHour;
        this.dayDuration = dayDuration;
    }
    
    @Override
    public void run(){
        while(true){
            
//            if()
            
//            work();
            
            try {
                sleep(this.dayDuration);
                System.out.println("dia termina");
                System.out.println(" ");
                
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
           
        }
    }
    
    
}
