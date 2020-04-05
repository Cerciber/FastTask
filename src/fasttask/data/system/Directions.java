package fasttask.data.system;

import fasttask.Main;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Directions {

    public static String EDITOR_FILE;
    public static String SAVED_FILE;
    public static String GENERATED_FOLDER;
    public static String TEMPLTES_FOLDER;
    public static String PYTHON_FILE;
    public static String JAVA_FILE;
    public static String JAVASCRIPT_FILE;
    public static String C_PLUS_PLUS_FILE;

    // Asignar constantes de direcciones de acuerdo a si el programa de ejecuta desde el proyecto o desde la distribución
    public static void setConstants() {

        // Ruta base
        String base;

        // Si se esta ejecutado desde el proyecto
        if (Main.class.getResource("Main.class").toString().startsWith("file")) {
            base = "C:\\Users\\acer\\Documents\\FastTask";
            // Si se esta ejecutado desde la distribución
        } else {
            base = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6);
        }

        EDITOR_FILE = base + "\\configuration\\directions\\Editor.txt";
        SAVED_FILE = base + "\\configuration\\directions\\Saved.txt";
        GENERATED_FOLDER = base + "\\configuration\\generated";
        TEMPLTES_FOLDER = base + "\\configuration\\templates";
        PYTHON_FILE = base + "\\configuration\\directions\\Python.txt";
        JAVA_FILE = base + "\\configuration\\directions\\Java.txt";
        JAVASCRIPT_FILE = base + "\\configuration\\directions\\JavaScript.txt";
        C_PLUS_PLUS_FILE = base + "\\configuration\\directions\\CPlusPlus.txt";

    }

    public static String getEditorFile() throws UnsupportedEncodingException, IOException {
        return FileAccess.loadContent(EDITOR_FILE).trim();
    }

    public static void setEditorFile(String sublimeTextFolder) throws IOException {
        FileAccess.savedContent(EDITOR_FILE, sublimeTextFolder);
    }

    public static String getSaveFolder() throws UnsupportedEncodingException, IOException {
        return FileAccess.loadContent(SAVED_FILE).trim();
    }

    public static void setSaveFolder(String SaveFolder) throws IOException {
        FileAccess.savedContent(SAVED_FILE, SaveFolder);
    }

    public static String getGeneratedFolder() {
        return GENERATED_FOLDER;
    }

    public static String getTemplatesFolder() {
        return TEMPLTES_FOLDER;
    }

    public static String getPythonFolder() throws UnsupportedEncodingException, IOException {
        return FileAccess.loadContent(PYTHON_FILE).trim();
    }

    public static void setPythonFolder(String pythonFolder) throws IOException {
        FileAccess.savedContent(PYTHON_FILE, pythonFolder);
    }

    public static String getJavaFolder() throws UnsupportedEncodingException, IOException {
        return FileAccess.loadContent(JAVA_FILE).trim();
    }

    public static void setJavaFolder(String javaFolder) throws IOException {
        FileAccess.savedContent(JAVA_FILE, javaFolder);
    }

    public static String getJavaScriptFolder() throws UnsupportedEncodingException, IOException {
        return FileAccess.loadContent(JAVASCRIPT_FILE).trim();
    }

    public static void setJavaScriptFolder(String javaScriptFolder) throws IOException {
        FileAccess.savedContent(JAVASCRIPT_FILE, javaScriptFolder);
    }

    public static String getCPlusPlusFolder() throws UnsupportedEncodingException, IOException {
        return FileAccess.loadContent(C_PLUS_PLUS_FILE).trim();
    }

    public static void setCPlusPlusFolder(String cPlusPlusScriptFolder) throws IOException {
        FileAccess.savedContent(C_PLUS_PLUS_FILE, cPlusPlusScriptFolder);
    }

}
