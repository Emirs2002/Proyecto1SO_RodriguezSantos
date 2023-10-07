package trabajadores;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alesc
 */
public class Nodo {
    protected Nodo pnext;
    protected Object data;
   

    public Nodo(Object data) {
        this.pnext = null;
        this.data = data;
    }
    

    /**
    * @return pnext
    */
    public Nodo getPnext() {
        return pnext;
    }

    /**
    * @param pnext, asigna pnext
    */
    public void setPnext(Nodo pnext) {
        this.pnext = pnext;
    }

    /**
    *@return nombre
    */
    public Object getData() {
        return data;
    }

    /**
    * @param data, asigna data
    */
    public void setData(Object data) {
        this.data = data;
    }
    
}
