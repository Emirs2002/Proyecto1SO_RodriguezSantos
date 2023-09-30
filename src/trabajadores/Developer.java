/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajadores;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

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
    private int totalPayment;
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
                //pago al empleado
//                sleep((41.6);
//                this.totalPayment += this.payment;
               
                
                sleep(this.dayDuration);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
           
        }
    }
    
    public void work(){
        this.acumulado += this.productionPerDay;
        if(this.acumulado >= 1){
            
            System.out.println(this.acumulado);
            
                //producto listo, guardar en drive
//            try {
                //se activa el semaforo
//                this.mutex.acquire(1); //equivalente a wait
//                
//                this.drive.addToDrive(type,1);
//                this.acumulado = 0;
//                
//                this.mutex.release(); //equivalente a signal
                        
//            } catch (InterruptedException ex) {
//                ex.printStackTrace(System.out);
//            }
            
        }
//        System.out.println(this.totalPayment);
        
    }
    
}
