package trabajadores;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author alesc
 */
public class Integrator extends Thread
{
    private final float productionPerDay;
    private final int dayDuration;
    private float acumulado;
    private Semaphore mutex;
    private int paymentPerDay = 0;
    private final int paymentPerHour;
    private final Drive drive;
    private final Game companyName;
    private final String type;
    public boolean exit = false;

    

    public Integrator(float productionPerDay, int dayDuration, Semaphore mutex, int payment, Drive drive, Game companyName) {
        this.productionPerDay = productionPerDay;
        this.dayDuration = dayDuration;
        this.mutex = mutex;
        this.paymentPerHour = payment;
        this.drive = drive;
        this.companyName = companyName;
        this.type= "integrador";

    }
    
    
    @Override
    public void run(){
        while(!exit){
            //aqui va el trabajo como tal
            
            work();
            
            try {
                sleep(this.dayDuration);
                
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
           
        }
    }
    
    public void stopThread(){
        exit = true;
    }
    
    public void work(){
 
        //produccion del dia
        this.acumulado += this.productionPerDay;
        
        this.paymentPerDay = 24 * this.paymentPerHour;//pago integrador
        
        //producto listo, guardar en drive
        if(this.acumulado >= 1){
            
            try {
//                se activa el semaforo
                this.mutex.acquire(1); //wait
                System.out.println("");
                System.out.println("integrador entro drive");
                int cantidadPartes = this.drive.glueParts(this.companyName);
                this.drive.addToDrive("integrador", cantidadPartes);
                
                this.acumulado = 0;
                
                this.mutex.release(); //signal
                        
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        
    }
    
    public String getType() {
        return type;
    }

    public int getPaymentPerDay() {
        return paymentPerDay;
    }

    public void setPaymentPerDay(int paymentPerDay) {
        this.paymentPerDay = paymentPerDay;
    }
    
}
