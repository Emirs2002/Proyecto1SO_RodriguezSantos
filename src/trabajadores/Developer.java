package trabajadores;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emirs
 */
public class Developer extends Thread {
    private String type;//acepta guion,sprite,nivel,programador,dlc
    private float productionPerDay;
    private int dayDuration;
    private float acumulado;
    private Semaphore mutex;
    private int totalPayment = 0;
    private int payment;
    

    public Developer(String type, float productionPerDay, int dayDuration, Semaphore mutex, int payment) {
        this.type = type;
        this.productionPerDay = productionPerDay;
        this.dayDuration = dayDuration;
        this.mutex = mutex;
        this.payment = payment;
    }
    
    
    @Override
    public void run(){
        while(true){
            //aqui va el trabajo como tal
            
            work();
            
            try {
                sleep(this.dayDuration);
                System.out.println("dia termina");
                
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
           
        }
    }
    
    public void work(){
      
        //pago al desarrollador
        int x = 0;

        while(x<24){
            try {
                sleep((long) (41.6667));
                this.totalPayment += this.payment;
                ++x;
                
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        
        System.out.println( this.totalPayment);
        
        
        //produccion del dia
        this.acumulado += this.productionPerDay;
        
        if(this.acumulado >= 1){
            
            //producto listo, guardar en drive
            
            System.out.println("guion hecho: " + Math.round(this.acumulado));
            
//            try {
                //se activa el semaforo
//                this.mutex.acquire(1); //equivalente a wait
//                
//                this.drive.addToDrive(type,1);
            this.acumulado = 0;
//                
//                this.mutex.release(); //equivalente a signal
                        
//            } catch (InterruptedException ex) {
//                ex.printStackTrace(System.out);
//            }
            
        }
        
           
        
        
    }
    
}
