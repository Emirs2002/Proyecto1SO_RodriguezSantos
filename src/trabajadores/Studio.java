/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajadores;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alesc
 */
public class Studio {
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
    private Drive drive;
//    private ProjectManager pm;
//    private Director director;
//    private Developer guionista;
//    private Developer nivelista;
//    private Developer artista;
//    private Developer programador;
//    private Developer dlc;
//    private Integrator integrator;

//    public Studio(int standardPrice, int DLCprice, int deadline, ProjectManager pm, Director director, int size, Developer guionista, Developer nivelista, Developer artista, Developer programador, Developer dlc, Integrator integrator) {
//        this.standardPrice = standardPrice;
//        this.DLCprice = DLCprice;
//        this.deadline = deadline;
////        this.pm = pm;
////        this.director = director;
//        this.size = size;
////        this.guionista = guionista;
////        this.nivelista = nivelista;
////        this.artista = artista;
////        this.programador = programador;
////        this.dlc = dlc;
////        this.integrator = integrator;
//    }

    public Studio(int standardPrice, int DLCprice, int deadline, int size, Lista workerList, int numGuionista, int numSpriter, int numNiveler, int numSystem, int numDlc, int numIntegrador) {
        this.standardPrice = standardPrice;
        this.DLCprice = DLCprice;
        this.deadline = deadline;
        this.size = size;
        this.workerList = workerList;
        this.numGuionista = numGuionista;
        this.numSpriter = numSpriter;
        this.numNiveler = numNiveler;
        this.numSystem = numSystem;
        this.numDlc = numDlc;
        this.numIntegrador = numIntegrador;
    }
    
    public void createWorkList(){
        
        for (int i = 0; i < this.numSpriter; i++) {
            Developer dev = new Developer("sprite",1,1000,this.mutexDrive,10,this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numGuionista; i++) {
            Developer dev = new Developer("guion",1,1000,this.mutexDrive,20,this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
        for (int i = 0; i < this.numNiveler; i++) {
            Developer dev = new Developer("nivel",1,1000,this.mutexDrive,40,this.drive);
            Nodo nodito = new Nodo(dev);
            this.workerList.addAtEnd(nodito);
        }
         
    }
    
    public void startWorkers(){
        
        createWorkList();
        
        int i=0;
        Lista workers = this.workerList;
        Nodo temp = workers.getPfirst();
        while(i < workers.getTamanho()){
            
            Developer dev = (Developer) temp.getData();
            dev.start();
            temp = workers.proximoNodo(temp);
            i++;
        }
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
