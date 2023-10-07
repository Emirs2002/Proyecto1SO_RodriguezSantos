package trabajadores;


import javax.swing.JOptionPane;

/**
 *
 * @author ERodríguez
 * @version 27/06/22
 */
public class Lista {
    
    private Nodo pfirst;
    private Nodo plast;
    private int tamanho;

//GETTERS Y SETTERS 
    
    /**
    *@return pfirst
    */
    public Nodo getPfirst() {
        return pfirst;
    }
   
    /**
    * @param pfirst, asigna pfirst
    */
    public void setPfirst(Nodo pfirst) {
        this.pfirst = pfirst;
    }
   
    /**
    *@return tamanho
    */
    public int getTamanho() {
        return tamanho;
    }
   
    /**
    * @param tamanho, asigna tamanho
    */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    /**
    *@return plast
    */
    public Nodo getPlast() {
        return plast;
    }
   
    /**
    * @param plast, asigna plast
    */
    public void setPlast(Nodo plast) {
        this.plast = plast;
    }


 //CONSTRUCTOR
    public Lista() {
        this.pfirst = null;
        this.tamanho = 0;    
    }
 
 //FUNCIONES PRIMITIVAS
    public boolean isEmpty(){
        return pfirst == null;
    }  
    
    public Nodo primerNodo(Nodo posicion){
        return pfirst;
    }
    
    public Nodo ultimoNodo(Nodo posicion){
        return null;
    }
    
    public void vaciarLista(){
        pfirst = null;
        tamanho = 0;
    }
    
    //OBTENER EL PROXIMO NODO
    public Nodo proximoNodo(Nodo  enlace){
        if(enlace.getPnext() != null){
            enlace =enlace.getPnext();
            return enlace;                    
        }
        else{
            return null;
        }
    }
   
    //IMPRIMIR
    public void imprimirLista(){
        Nodo temp = pfirst;
        if (this.isEmpty()){
            JOptionPane.showMessageDialog(null, "La lista está vacía");
        }
        else{
            
                
            String print = "";             

            for (int i = 0; i< this.getTamanho(); i++ ){               

                print += temp.getData();
                
                temp = proximoNodo(temp);               
                
               }
            JOptionPane.showMessageDialog(null,print);
   
            }
        }
    
     //Obtener info
    public String ObtenerInfo(){
        Nodo temp = pfirst;
        if (this.isEmpty()){
            JOptionPane.showMessageDialog(null, "La lista está vacía");
        }
        else{
                    
            String print = "";             

            for (int i = 0; i< this.getTamanho(); i++ ){               

                print += temp.getData() + "\n";
                
                temp = proximoNodo(temp);               
                
               }
            return print;
   
            }
        return null;
        }
    

    //ELIMINA AL INICIO
    public void deleteAtStart(){
        if (!isEmpty()){
            pfirst = pfirst.getPnext();
            tamanho -= 1;
        }else{
            JOptionPane.showMessageDialog(null,"La lista está vacía");
        }
    }
    
    //AÑADIR AL FINAL
    public void addAtEnd(Nodo nodito){
        
        if(!this.isEmpty()){ 
            Nodo temp = plast;
            temp.setPnext(nodito);
            plast = nodito;
        }
        else{
            pfirst = plast = nodito;
        }
        
        tamanho++;        
        
    }

    //BUSCAR ELEMENTO EN LA LISTA 
    public Nodo Buscar(Object valor){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null,"Lista vacía");
            return null;
        }else{
            Nodo temp = pfirst;
            for (int i = 0; i < this.getTamanho(); i++) {
                
                if(valor== temp.getData()){
                    return temp;
                }
                else{
                    temp = temp.getPnext();
                }
            }
        }
            return null;
    }
   
    //ÍNDICE DE LA LISTA
    public int getIndex(Nodo nodito){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Lista vacía");
            return -1;
        }else{
            Nodo temp = pfirst;
            int cont = 0;
            while(temp != null){
                
                if (nodito == temp){
                     return cont;
                     
                }else{
                    temp = temp.getPnext();
                    cont ++;
                }
                
            } return cont;
        }
        
    }
    
    //OBTENER NODO POR INDICE
    public Nodo getNodo(int posicion){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Lista vacía");
            return null;
        }else if(posicion >= this.tamanho){
            JOptionPane.showMessageDialog(null,"Error");
            return null;
        }else{
            Nodo temp = pfirst;
            
            for (int i = 0; i < posicion; i++) {
                temp = temp.getPnext();
            }return temp;
        }
    }
    
}
