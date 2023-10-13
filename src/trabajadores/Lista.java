package trabajadores;


import javax.swing.JOptionPane;

/**
 *
 * @author alesc
 */
public class Lista {
    
    private Nodo pfirst;
    private Nodo plast;
    private int tamanho;
    private int limit;

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
    public Lista(int limit) {
        this.pfirst = null;
        this.tamanho = 0;
        this.limit = limit;
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

                print += temp.getData()+ "\n";
                
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
                
                if((temp.getData().getClass().getSimpleName()).equals("Developer")){
                    
                    print += ((Developer) temp.getData()).getType() + "\n";
                    
                }else{
                    print += ((Integrator) temp.getData()).getType() + "\n";
                    
                }
                
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
    
    //BORRAR
    public void deleteNode(String type){
        if (!isEmpty()){
            Nodo temp = this.getPfirst();
            Nodo nodoBorrar = Buscar(type);
            Nodo nodoProximo = nodoBorrar.getPnext();
            
             if((nodoBorrar.getData().getClass().getSimpleName()).equals("Developer")){
                    
                    ((Developer) nodoBorrar.getData()).stopThread();
                    
                    
                }else{
                    ((Integrator) nodoBorrar.getData()).stopThread();                    
                }
            
            int actual = this.getIndex(nodoBorrar);
            
            if(actual>0){
                int anterior = actual - 1;
                for(int i = 0; i <= anterior ; i++){
                    if(i == anterior && nodoBorrar.getPnext() != null)
                    {
                        temp.setPnext(nodoProximo);                        
                        nodoBorrar.setPnext(null);                       
                    } 
                    
                temp = temp.getPnext();
                }

                tamanho -= 1;
            }else{
                deleteAtStart();
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"La lista está vacía");
        }
    }
    
    //AÑADIR AL FINAL
    public void addAtEnd(Nodo nodito){
        
        if(this.tamanho < this.limit){
            if(!this.isEmpty()){ 
                Nodo temp = plast;
                temp.setPnext(nodito);
                plast = nodito;
            }
            else{
                pfirst = plast = nodito;
            }

            tamanho++; 
        
        }else{
            JOptionPane.showMessageDialog(null,"Numero de trabajadores excedido");
        }
        
               
        
    }

    //BUSCAR ELEMENTO EN LA LISTA 
    public Nodo Buscar(String type){
        if(isEmpty()){
            JOptionPane.showMessageDialog(null,"Lista vacía");
            return null;
        }else{
            Nodo temp = pfirst;
            for (int i = 0; i < this.getTamanho(); i++) {
                
                if((temp.getData().getClass().getSimpleName()).equals("Developer")){
                    
                    if((((Developer)temp.getData()).getType()).equals(type)){
                        return temp;
                    }
                    else{
                        temp = temp.getPnext();
                    }
                }else{
                    return temp;
                    
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
