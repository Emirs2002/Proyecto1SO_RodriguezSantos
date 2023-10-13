package trabajadores;

import interfaz.Pantalla;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


/**
 *
 * @author alesc
 */
public class Studio extends Thread {

    private final int dayDuration;
    private int standardPrice;
    private int dlcPrice;
    private int costo = 0;
    private int utilidad = 0;
    private int benefit = 0;
    private int deadline;
    private final int size;
    private Lista workerList;
    private  int numGuionista;
    private  int numSpriter;
    private  int numNiveler;
    private  int numSystem;
    private  int numDlc;
    private  int numIntegrador;
    private final Semaphore mutexDrive;
    private final Semaphore mutexCounter;
    private final Drive drive;
    private final Game game;
    private ProjectManager manager;
    private Director director;
    private final float[] produccionArr;
    private DayCounter counter;
    public javax.swing.JLabel directorStatus;
    public javax.swing.JTextField standardLabel;
    public javax.swing.JTextField dlcLabel;
    public javax.swing.JTextField deadlineField;
    public javax.swing.JTextField faltas;
    public javax.swing.JLabel pm;
    public javax.swing.JTextField beneficio;
    public javax.swing.JTextField ganaciaReal;
    public javax.swing.JTextField gasto;
    
    


    public Studio(int standardPrice, int dlcPrice, int deadline, int size, int numGuionista, int numSpriter, int numNiveler, int numSystem, int numDlc, int numIntegrador, Game game, int dayDuration, float[] produccionArr, 
            javax.swing.JTextField guiones, javax.swing.JTextField sprites, javax.swing.JTextField niveles, javax.swing.JTextField sistemas, javax.swing.JTextField dlcs, javax.swing.JTextField standardGames, javax.swing.JTextField dlcGames, 
            javax.swing.JLabel directorStatus, javax.swing.JTextField standardLabel, javax.swing.JTextField dlcLabel, javax.swing.JTextField deadlineField, javax.swing.JTextField faltas, javax.swing.JLabel pm,
            javax.swing.JTextField beneficio, javax.swing.JTextField gananciaReal, javax.swing.JTextField gasto) {
        this.standardPrice = standardPrice;
        this.dlcPrice = dlcPrice;
        this.deadline = deadline;
        this.size = size;
        this.workerList = new Lista(size);
        this.numGuionista = numGuionista;
        this.numSpriter = numSpriter;
        this.numNiveler = numNiveler;
        this.numSystem = numSystem;
        this.numDlc = numDlc;
        this.numIntegrador = numIntegrador;
        this.game = game;
        this.mutexDrive = new Semaphore(1);
        this.mutexCounter = new Semaphore(1);
        this.drive = new Drive(standardPrice, dlcPrice, guiones, sprites, niveles, sistemas, dlcs, standardGames, dlcGames);
        this.dayDuration = dayDuration;
        this.produccionArr = produccionArr;
        this.directorStatus = directorStatus;
        this.standardLabel = standardLabel;
        this.dlcLabel = dlcLabel;
        this.deadlineField = deadlineField;
        this.faltas = faltas;
        this.pm = pm;
        this.beneficio = beneficio;
        this.ganaciaReal = gananciaReal;
        this.gasto = gasto;

    }

    @Override
    public void run() {
        startWorkers(); //iniciar los trabajadores
        try {
            sleep(this.dayDuration);
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }

        //administracion del estudio
        while (true) {

            try {
                //costo operativo
                this.costo += payAllWorkers();
                this.gasto.setText(Integer.toString(costo/1000));

                //beneficio
                this.benefit += this.director.getBenefit();
                this.beneficio.setText(Integer.toString(benefit/1000));
                
                //utilidad
                if(this.director.getBenefit() > 0){
                    this.utilidad = (this.benefit - this.costo);
                    this.director.setBenefit(0); //para que al obtener un beneficio este no se sume por el resto de dias, solo una vez
                    this.ganaciaReal.setText(Integer.toString(utilidad/1000));
                }
                
                sleep(this.dayDuration); //dormir un dia
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public void createWorkList() {

        for (int i = 0; i < this.numGuionista; i++) {
            Developer dev = new Developer("guion", this.produccionArr[0], this.dayDuration, this.mutexDrive, 10, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numNiveler; i++) {
            Developer dev = new Developer("nivel", this.produccionArr[1], this.dayDuration, this.mutexDrive, 13, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numSpriter; i++) {
            Developer dev = new Developer("sprite", this.produccionArr[2], this.dayDuration, this.mutexDrive, 20, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numSystem; i++) {
            Developer dev = new Developer("programador", this.produccionArr[3], this.dayDuration, this.mutexDrive, 8, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numDlc; i++) {
            Developer dev = new Developer("dlc", this.produccionArr[4], this.dayDuration, this.mutexDrive, 17, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numIntegrador; i++) {
            Integrator integrador = new Integrator(0.5f, this.dayDuration, this.mutexDrive, 25, this.drive, this.game);
            Nodo nodito = new Nodo(integrador);
            this.workerList.addAtEnd(nodito);
        }

        //inicializar counter, project manager y director
        this.counter = new DayCounter(this.deadline, this.deadlineField);
        this.manager = new ProjectManager(20, this.dayDuration, this.mutexCounter, this.counter, pm);
        this.director = new Director(this.manager, this.dayDuration, 50, this.drive, this.mutexDrive, this.mutexCounter, this.counter, this.directorStatus, this.standardLabel, this.dlcLabel, this.faltas);
    }

    public void startWorkers() {

        createWorkList();

        int i = 0;
        Lista workers = this.workerList;
        Nodo temp = workers.getPfirst();

        while (i < workers.getTamanho()) {

            Thread dev = (Thread) temp.getData();
            dev.start();
            temp = workers.proximoNodo(temp);
            i++;
        }

        this.manager.start();
        this.director.start();

    }

    public void setTextfields(javax.swing.JTextField spinnerGuion, javax.swing.JTextField spinnerSprite, javax.swing.JTextField spinnerNiveles, javax.swing.JTextField spinnerProgramadores, javax.swing.JTextField spinnerDLCs, javax.swing.JTextField spinnerIntegradores) {
        spinnerGuion.setText(Integer.toString(this.numGuionista));
        spinnerSprite.setText(Integer.toString(this.numSpriter));
        spinnerNiveles.setText(Integer.toString(this.numNiveler));
        spinnerProgramadores.setText(Integer.toString(this.numNiveler));
        spinnerDLCs.setText(Integer.toString(this.numDlc));
        spinnerIntegradores.setText(Integer.toString(this.numIntegrador));
    }
    
    

    public int addDeveloper(String type, int prodPosition, int payment) {
        int add = 0;
        if (this.workerList.getTamanho() < size) {
            Developer dev = new Developer(type, this.produccionArr[prodPosition], this.dayDuration, this.mutexDrive, payment, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
            dev.start();
            add += 1;
           
        }
        
        return add;
    }

    public int addIntegrator() {
        int add = 0;
        if (this.workerList.getTamanho() < size) {
            Integrator inte = new Integrator(0.5f, this.dayDuration, this.mutexDrive, 25, this.drive, this.game);
            Nodo nodito = new Nodo(inte);
            this.workerList.addAtEnd(nodito);
            inte.start();
            add+=1;
        }
        return add;
    }

    public int payAllWorkers() {

        //recorrer lista de trabajadores
        Lista workers = this.workerList;
        Nodo temp = workers.getPfirst();
        int totalPayment = 0;

        for (int i = 0; i < workers.getTamanho(); i++) {

            Thread dev = (Thread) temp.getData();

            if (dev.getClass().getSimpleName().equals("Developer")) {
                totalPayment += ((Developer) dev).getPaymentPerDay();
            } else {
                totalPayment += ((Integrator) dev).getPaymentPerDay();
            }
            
            if(workers.proximoNodo(temp)!= null)
            {
                temp = workers.proximoNodo(temp);
            }else{
                break;
            }
        }

        //pago project manager y director
        totalPayment += (this.manager.getPaymentPerDay() + this.director.getPaymentPerDay());

        return totalPayment; //para que el numero se vea bonito
    }

    //GETTERS Y SETTERS
    public int getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(int standardPrice) {
        this.standardPrice = standardPrice;
    }

    public int getDlcPrice() {
        return dlcPrice;
    }

    public void setDlcPrice(int dlcPrice) {
        this.dlcPrice = dlcPrice;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public Lista getWorkerList() {
        return workerList;
    }

    public void setWorkerList(Lista workerList) {
        this.workerList = workerList;
    }

    public int getNumGuionista() {
        return numGuionista;
    }

    public int getNumSpriter() {
        return numSpriter;
    }

    public int getNumNiveler() {
        return numNiveler;
    }

    public int getNumSystem() {
        return numSystem;
    }

    public int getNumDlc() {
        return numDlc;
    }

    public int getNumIntegrador() {
        return numIntegrador;
    }

    public int getSize() {
        return size;
    }

    public void setNumGuionista(int numGuionista) {
        this.numGuionista = numGuionista;
    }

    public void setNumSpriter(int numSpriter) {
        this.numSpriter = numSpriter;
    }

    public void setNumNiveler(int numNiveler) {
        this.numNiveler = numNiveler;
    }

    public void setNumSystem(int numSystem) {
        this.numSystem = numSystem;
    }

    public void setNumDlc(int numDlc) {
        this.numDlc = numDlc;
    }

    public void setNumIntegrador(int numIntegrador) {
        this.numIntegrador = numIntegrador;
    }
    
    
    
}
