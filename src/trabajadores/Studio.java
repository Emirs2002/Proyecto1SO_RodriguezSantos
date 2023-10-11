package trabajadores;

import interfaz.Pantalla;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

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
    private final int numGuionista;
    private final int numSpriter;
    private final int numNiveler;
    private final int numSystem;
    private final int numDlc;
    private final int numIntegrador;
    private final Semaphore mutexDrive;
    private final Semaphore mutexCounter;
    private final Drive drive;
    private final Game game;
    private ProjectManager manager;
    private Director director;
    private final float[] produccionArr;
    private DayCounter counter;

    public Studio(int standardPrice, int dlcPrice, int deadline, int size, int numGuionista, int numSpriter, int numNiveler, int numSystem, int numDlc, int numIntegrador, Game game, int dayDuration, Semaphore mutexCounter, Semaphore mutexDrive, float[] produccionArr) {
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
        this.mutexDrive = mutexDrive;
        this.mutexCounter = mutexCounter;
        this.drive = new Drive(standardPrice,dlcPrice);
        this.dayDuration = dayDuration;
        this.produccionArr = produccionArr;

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
                System.out.println("COSTO OPERATIVO TOTAL: " + this.costo);

                //beneficio
                
                this.benefit += this.director.getBenefit();
                System.out.println("DIRECTOR BENEFIT: "+this.director.getBenefit());
                this.director.setBenefit(0); //para que al obtener un beneficio este no se sume por el resto de dias, solo una vez
                System.out.println("BENEFICIO JUEGOS: "+ this.benefit);
                
                
                //utilidad
                this.utilidad += (this.benefit - this.costo);
                System.out.println("UTILIDAD ESTUDIO: " + this.utilidad);
                
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
            System.out.println("dlcer creado");
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numIntegrador; i++) {
            Integrator integrador = new Integrator(0.5f, this.dayDuration, this.mutexDrive, 25, this.drive, this.game);
            Nodo nodito = new Nodo(integrador);
            this.workerList.addAtEnd(nodito);
        }

        //inicializar counter, project manager y director
        this.counter = new DayCounter(this.deadline);
        this.manager = new ProjectManager(20, this.dayDuration, this.mutexCounter, this.counter);
        this.director = new Director(this.manager, this.dayDuration, 50, this.drive, this.mutexDrive, this.mutexCounter, this.counter);
    }

    public void startWorkers() {

        createWorkList();
        //setSpinner(spinnerGuion, spinnerSprite, spinnerNiveles, spinnerProgramadores, spinnerDLCs, spinnerIntegradores);

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

        //pruebas lista
//        System.out.println("ANTES ELIMINAR");
//        System.out.println(workers.ObtenerInfo());
//        
//        System.out.println("");
//        workers.deleteNode("nivel");
//        System.out.println("DESPUES ELIMINAR");
//        System.out.println(workers.ObtenerInfo());
//        
//        
    }

//    public void setSpinner(javax.swing.JSpinner spinnerGuion, javax.swing.JSpinner spinnerSprite, javax.swing.JSpinner spinnerNiveles, javax.swing.JSpinner spinnerProgramadores, javax.swing.JSpinner spinnerDLCs, javax.swing.JSpinner spinnerIntegradores)
//    {
//        spinnerGuion.setValue(this.numGuionista);
//        spinnerSprite.setValue(this.numSpriter);
//        spinnerNiveles.setValue(this.numNiveler);
//        spinnerProgramadores.setValue(this.numSystem);
//        spinnerDLCs.setValue(this.numDlc);
//        spinnerIntegradores.setValue(this.numIntegrador);
//    }
//    
    public void addDeveloper(String type, int prodPosition) {
        if (this.workerList.getTamanho() < size) {
            Developer dev = new Developer(type, this.produccionArr[prodPosition], this.dayDuration, this.mutexDrive, 10, this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
    }

    public void addIntegrator() {
        if (this.workerList.getTamanho() < size) {
            Integrator inte = new Integrator(0.5f, this.dayDuration, this.mutexDrive, 25, this.drive, this.game);
            Nodo nodito = new Nodo(inte);
            this.workerList.addAtEnd(nodito);
        }
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

            temp = workers.proximoNodo(temp);
        }

        //pago project manager y director
        totalPayment += (this.manager.getPaymentPerDay() + this.director.getPaymentPerDay());

        return totalPayment;
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

}
