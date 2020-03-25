package fasttask.controller.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {

    // Cargar contenido de un archivo
    public String loadContent(File dir) {

        String content = "";

        // Leer información de un archivo por lineas
        try {

            // Crear objeto de lectura
            FileReader reader = new FileReader(dir);

            // Crear objeto de lectura directa
            BufferedReader br = new BufferedReader(reader);

            while (true) {

                // leer linea
                String valor = br.readLine();

                // Verificar si se ha llegado al final del archivo
                if (valor == null) {
                    break;
                } else {
                    content += valor + "\n";
                }

            }

        } catch (IOException ex) {
        }

        return content;
    }

    // Guardar contenido
    public void savedContent(File dir, String code){
        
        // Escribir en un archivo
        try {

            // Crear objeto de escritura
            FileWriter writer = new FileWriter(dir);
            
            // Crear objeto de escritura directa
            BufferedWriter br = new BufferedWriter(writer);
            
            // Escribir texto
            br.write(code);
            
            // Cerrar objeto escritura
            br.close();

        } catch (IOException ex) {
        }
        
    }
    
    // Obtener nombre de un archivo
    public String getName(File dir) {
        int index = dir.getName().lastIndexOf(".");
        if (index == -1) {
            return dir.getName();
        }
        return dir.getName().substring(0, index);
    }
    
    // Obtener extensión de un archivo
    public String getExtension(File dir) {
        try {
            int index = dir.getCanonicalPath().lastIndexOf(".");
            if (index == -1) {
                return "";
            }
            return dir.getCanonicalPath().substring(index + 1);
        } catch (IOException ex) {
            return "";
        }
    }
    
    // Crear archivo
    public void createFile(File dir){
        try {
            dir.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    // Borrar archivos en un directorio
    public void deleteFilesInFolder(File dir){
        File[] files = dir.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

}
