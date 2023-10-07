package trabajadores;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emirs
 */
public class ManejoArchivo {
    
    public void readTxt(String path){
        
    }
    
    public void writeTxt(String path, String linea){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        
        
        
    }
    
}
