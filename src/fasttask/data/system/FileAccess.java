package fasttask.data.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileAccess {

    // Cargar contenido de un archivo
    public static String loadContent(String dir) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        String content = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir), "UTF-8"));

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

        return content;
    }

    // Guardar contenido
    public static void savedContent(String dir, String code) throws IOException {

        // Crear objeto de lectura
        FileWriter writer = new FileWriter(new File(dir));

        // Crear objeto de lectura directa
        BufferedWriter br = new BufferedWriter(writer);

        // Escribir texto
        br.write(code);

        // Cerrar objeto escritura
        br.close();

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
    public static String getFolder(String dir) {
        File file = new File(dir);
        return file.getParent();
    }

    // Crear archivo
    public static boolean createFile(String dir) throws IOException {
        if (new File(dir).exists()) {
            return false;
        }
        new File(dir).createNewFile();
        return true;
    }

    // Borrar archivos en un directorio
    public static void deleteFilesInFolder(String dir) {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    // Copiar archivo
    public static boolean copyFile(String dir1, String dir2) throws IOException {
        boolean r = createFile(dir2);
        if (r) {
            savedContent(dir2, loadContent(dir1));
        }
        return r;
    }

    // Eliminar archivo
    public static void deleteFile(String dir) throws IOException {
        System.gc();
        org.apache.commons.io.FileDeleteStrategy.FORCE.delete(new File(dir));
    }

}
