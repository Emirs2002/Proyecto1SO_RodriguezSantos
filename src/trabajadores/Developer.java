package trabajadores;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author emirs
 */
public class Developer extends Thread {
    private String type;//acepta guion,sprite,nivel,programador,dlc
    private final float productionPerDay;
    private final int dayDuration;
    private float acumulado;
    private Semaphore mutex;
    private int paymentPerDay = 0;
    private final int paymentPerHour;
    private final Drive drive;
    
    

    public Developer(String type, float productionPerDay, int dayDuration, Semaphore mutex, int payment, Drive drive) {
        this.type = type;
        this.productionPerDay = productionPerDay;
        this.dayDuration = dayDuration;
        this.mutex = mutex;
        this.paymentPerHour = payment;
        this.drive = drive;
    }
    
    
    @Override
    public void run(){
        while(true){
            //aqui va el trabajo como tal
            
            work();
            
            try {
                sleep(this.dayDuration);
                
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
           
        }
    }
    
    public void work(){
 
        //produccion del dia
        this.acumulado += this.productionPerDay;
        
        this.paymentPerDay += 24 * this.paymentPerHour;//pago desarrollador
        
        //producto listo, guardar en drive
        if(this.acumulado >= 1){
            
            try {
//                se activa el semaforo
                this.mutex.acquire(1); //equivalente a wait
                
                this.drive.addToDrive(type,(int)this.acumulado);
                                
                this.acumulado = 0;
                
                this.mutex.release(); //equivalente a signal
                        
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        
        
        
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPaymentPerDay() {
        return paymentPerDay;
    }

    public void setPaymentPerDay(int paymentPerDay) {
        this.paymentPerDay = paymentPerDay;
    }
    
}
