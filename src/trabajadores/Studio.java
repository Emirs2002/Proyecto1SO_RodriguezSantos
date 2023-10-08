package trabajadores;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alesc
 */
public class Studio {
    private int dayDuration;
    private int standardPrice;
    private int DLCprice;
    private int costo;
    private int utilidad;
    private int benefit;
    private int deadline;
    private int size;
    private Lista workerList;
    private int numGuionista;
    private int numSpriter;
    private int numNiveler;
    private int numSystem;
    private int numDlc;
    private int numIntegrador;
    private Semaphore mutexDrive;
    private Semaphore mutexCounter;
    private Drive drive;
    private Game game;
    private ProjectManager manager;
    private Director director;

    public Studio(int standardPrice, int DLCprice, int deadline, int size,int numGuionista, int numSpriter, int numNiveler, int numSystem, int numDlc, int numIntegrador, Game game, int dayDuration, Semaphore mutexCounter, Semaphore mutexDrive, Drive drive) {
        this.standardPrice = standardPrice;
        this.DLCprice = DLCprice;
        this.deadline = deadline;
        this.size = size;
        this.workerList = new Lista();
        this.numGuionista = numGuionista;
        this.numSpriter = numSpriter;
        this.numNiveler = numNiveler;
        this.numSystem = numSystem;
        this.numDlc = numDlc;
        this.numIntegrador = numIntegrador;
        this.game=game;
        this.mutexDrive = mutexDrive;
        this.mutexCounter = mutexCounter;
        this.drive = drive;
        this.dayDuration = dayDuration;
        
    }
    
    public void createWorkList(){
        
        for (int i = 0; i < this.numSpriter; i++) { //pasar productionperday por parametro
            Developer dev = new Developer("sprite",1,1000,this.mutexDrive,10,this.drive);
            Nodo nodito = new Nodo(dev);
            System.out.println("spriter creado");
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numGuionista; i++) {
            Developer dev = new Developer("guion",1,1000,this.mutexDrive,20,this.drive);
            Nodo nodito = new Nodo(dev);
            System.out.println("guionista creado");
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numNiveler; i++) {
            Developer dev = new Developer("nivel",1,1000,this.mutexDrive,40,this.drive);
            Nodo nodito = new Nodo(dev);
            System.out.println("niveler creado");
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numSystem; i++) {
            Developer dev = new Developer("programador",1,1000,this.mutexDrive,10,this.drive);
            Nodo nodito = new Nodo(dev);
            System.out.println("programador creado");
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numDlc; i++) {
            Developer dev = new Developer("dlc",1,1000,this.mutexDrive,10,this.drive);
            Nodo nodito = new Nodo(dev);
            System.out.println("dlcer creado");
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numIntegrador; i++) {
            Integrator integrador = new Integrator(0.5f, 1000, this.mutexDrive, 10, this.drive, this.game);
            Nodo nodito = new Nodo(integrador);
            System.out.println("integrador creado");
            this.workerList.addAtEnd(nodito);
        }
        
        this.manager = new ProjectManager(this.deadline, 20, this.dayDuration,this.mutexCounter);
        this.director = new Director(this.manager, this.dayDuration,50,this.drive,this.mutexDrive,this.mutexCounter);
    }
    
    public void startWorkers(){
        
        createWorkList();
        
        int i=0;
        Lista workers = this.workerList;
        Nodo temp = workers.getPfirst();
        while(i < workers.getTamanho()){
            
            Thread dev = (Thread)temp.getData();
//            System.out.println(dev.getClass().getSimpleName() );
            dev.start();
            temp = workers.proximoNodo(temp);
            i++;
        }
        
        this.manager.start();
        this.director.start();
        
        workers.imprimirLista();
    }

    public int getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(int standardPrice) {
        this.standardPrice = standardPrice;
    }

    public int getDLCprice() {
        return DLCprice;
    }

    public void setDLCprice(int DLCprice) {
        this.DLCprice = DLCprice;
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
    
    
    
    
    
}