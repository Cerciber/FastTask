package fasttask.data.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccess {

    // Cargar contenido de un archivo
    public static String loadContent(String dir) {

        String content = "";

        // Leer información de un archivo por lineas
        try {

            // Crear objeto de lectura
            FileReader reader = new FileReader(new File(dir));

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
            System.out.println(ex);
        }
        return content;
    }

    // Guardar contenido
    public static void savedContent(String dir, String code) {

        // Escribir en un archivo
        try {

            // Crear objeto de lectura
            FileWriter writer = new FileWriter(new File(dir));

            // Crear objeto de lectura directa
            BufferedWriter br = new BufferedWriter(writer);

            // Escribir texto
            br.write(code);

            // Cerrar objeto escritura
            br.close();

        } catch (IOException ex) {
        }

    }

    // Obtener nombre de un archivo
    public static String getName(String dir) {
        File file = new File(dir);
        int index = file.getName().lastIndexOf(".");
        if (index == -1) {
            return file.getName();
        }
        return file.getName().substring(0, index);
    }

    // Obtener extensión de un archivo
    public static String getExtension(String dir) {
        File file = new File(dir);
        int index = file.getName().lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return file.getName().substring(index + 1); 
    }
    
    // Obtener nombre con extensión de un archivo
    public static String getNameExtention(String dir) {
        File file = new File(dir);
        return file.getName();
    }
    
    // Obtener carpeta contenedora
    public static String getFolder(String dir){
        File file = new File(dir);
        return file.getParent();
    }

    // Crear archivo
    public static void createFile(String dir) {
        try {
            new File(dir).createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // Borrar archivos en un directorio
    public static void deleteFilesInFolder(String dir) {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    // Copiar archivo
    public static void copyFile(String dir1, String dir2) {
        createFile(dir2);
        savedContent(dir2, loadContent(dir1));
    }

    // Eliminar archivo
    public static void deleteFile(String dir) {
        System.gc();
        try {
            org.apache.commons.io.FileDeleteStrategy.FORCE.delete(new File(dir));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
