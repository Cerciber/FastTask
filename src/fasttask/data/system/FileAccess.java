package fasttask.data.system;

import fasttask.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileAccess {

    // Cargar contenido de un archivo
    public static String loadContent(String dir) {

        String content = "";

        // Leer información de un archivo por lineas
        try {

            // Crear objeto de lectura
            FileReader reader;

            // Crear objeto de lectura directa
            BufferedReader br;
            if (dir.startsWith("data")) {
                br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(dir)));
            } else {
                reader = new FileReader(new File(dir));
                br = new BufferedReader(reader);
            }

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
            FileWriter writer;

            // Crear objeto de lectura directa
            BufferedWriter br;
            if (dir.startsWith("data")) {
                br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(Main.class.getResource(dir).toURI()))));
            } else {
                writer = new FileWriter(new File(dir));
                br = new BufferedWriter(writer);
            }

            // Escribir texto
            br.write(code);

            // Cerrar objeto escritura
            br.close();

        } catch (IOException ex) {
        } catch (URISyntaxException ex) {
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
