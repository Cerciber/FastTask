
package fasttask.data.system;

import fasttask.Main;
import fasttask.data.system.FileAccess;

public class Directions {
   
    
    static final String EDITOR_FILE = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\directions\\Editor.txt";
    static final String SAVED_FILE = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\directions\\Saved.txt";
    static final String GENERATED_FOLDER = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\generated";
    static final String TEMPLTES_FOLDER = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\templates";
    static final String PYTHON_FILE = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\directions\\Python.txt";
    static final String JAVA_FILE = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\directions\\Java.txt";
    static final String JAVASCRIPT_FILE = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\directions\\JavaScript.txt";
    static final String C_PLUS_PLUS_FILE = "C:\\Users\\acer\\Documents\\FastTask\\configuration\\directions\\CPlusPlus.txt";
    
    // Direcciones para distribución
    /*static final String EDITOR_FILE = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\directions\\Editor.txt";
    static final String SAVED_FILE = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\directions\\Saved.txt";
    static final String GENERATED_FOLDER = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\generated";
    static final String TEMPLTES_FOLDER = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\templates";
    static final String PYTHON_FILE = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\directions\\Python.txt";
    static final String JAVA_FILE = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\directions\\Java.txt";
    static final String JAVASCRIPT_FILE = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\directions\\JavaScript.txt";
    static final String C_PLUS_PLUS_FILE = FileAccess.getFolder(Main.class.getProtectionDomain().getCodeSource().getLocation().toString().replace("%20", " ")).substring(6) + "\\configuration\\directions\\CPlusPlus.txt";
    */
    
    public static String getEditorFile() {
        return FileAccess.loadContent(EDITOR_FILE).trim();
    }

    public static void setEditorFile(String sublimeTextFolder) {
        FileAccess.savedContent(EDITOR_FILE, sublimeTextFolder);
    }

    public static String getSaveFolder() {
        return FileAccess.loadContent(SAVED_FILE).trim();
    }

    public static void setSaveFolder(String SaveFolder) {
        FileAccess.savedContent(SAVED_FILE, SaveFolder);
    }
    
    public static String getGeneratedFolder() {
        return GENERATED_FOLDER;
    }
    
    public static String getTemplatesFolder() {
        return TEMPLTES_FOLDER;
    }
    
    public static String getPythonFolder() {
        return FileAccess.loadContent(PYTHON_FILE).trim();
    }

    public static void setPythonFolder(String pythonFolder) {
        FileAccess.savedContent(PYTHON_FILE, pythonFolder);
    }

    public static String getJavaFolder() {
        return FileAccess.loadContent(JAVA_FILE).trim();
    }

    public static void setJavaFolder(String javaFolder) {
        FileAccess.savedContent(JAVA_FILE, javaFolder);
    }

    public static String getJavaScriptFolder() {
        return FileAccess.loadContent(JAVASCRIPT_FILE).trim();
    }

    public static void setJavaScriptFolder(String javaScriptFolder) {
        FileAccess.savedContent(JAVASCRIPT_FILE, javaScriptFolder);
    }
    
    public static String getCPlusPlusFolder() {
        return FileAccess.loadContent(C_PLUS_PLUS_FILE).trim();
    }

    public static void setCPlusPlusFolder(String cPlusPlusScriptFolder) {
        FileAccess.savedContent(C_PLUS_PLUS_FILE, cPlusPlusScriptFolder);
    }
    
}
