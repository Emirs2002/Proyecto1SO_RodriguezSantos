package interfaz;

import trabajadores.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emirs
 */
public class ManejoArchivo1 {

    public String[] readTxt(String path) {
        String[] studioArr = new String[15];
        String tempString = "";

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String lectura = br.readLine(); //se lee la primera linea

            //se guarda toda la info del txt en el string
            while (lectura != null) {

                if (lectura.equals("Bethesda") || lectura.equals("SquareEnix")) {
                } else {
                    tempString += lectura;

                }

                lectura = br.readLine();
            }

            br.close(); //termina el flujo de lectura

            //convertir string en un array identificador,valor
            String[] tempArr = tempString.split(";");

            //Añadir a array final
            String tempDatos;
            String[] tempDatosArr;
            for (int i = 0; i < tempArr.length; i++) {
                tempDatos = tempArr[i];
                tempDatosArr = tempDatos.split(",");

                studioArr[i] = tempDatosArr[1]; //se incluye el numero al arreglo final
                //indice 0 es el identificador
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        return studioArr;
    }

    public void writeTxt(String path, String[] contenido) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }

        try {

            PrintWriter pw = new PrintWriter(file);

            pw.println("Dia," + contenido[0] + ";");

            //para Bethesda
            pw.println("Bethesda");
            pw.println("guionista," + contenido[1] + ";");
            pw.println("sprites," + contenido[2] + ";");
            pw.println("niveles," + contenido[3] + ";");
            pw.println("programador," + contenido[4] + ";");
            pw.println("dlc," + contenido[5] + ";");
            pw.println("integrador," + contenido[6] + ";");
            pw.println("deadline," + contenido[7] + ";");

            //para SquareEnix
            pw.println("SquareEnix");
            pw.println("guionista," + contenido[8] + ";");
            pw.println("sprites," + contenido[9] + ";");
            pw.println("niveles," + contenido[10] + ";");
            pw.println("programador," + contenido[11] + ";");
            pw.println("dlc," + contenido[12] + ";");
            pw.println("integrador," + contenido[13] + ";");
            pw.println("deadline," + contenido[14] + ";");

            pw.close(); //se guarda el archivo en disco

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);

        }
        
    }
    
   
}