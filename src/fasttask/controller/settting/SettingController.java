
package fasttask.controller.settting;

import fasttask.data.system.FileAccess;

public class SettingController {
   
    static final String SUBLIME_TEXT_3_FILE = "data/files/directions/SublimeText3.txt";
    static final String SAVED_FILE = "data/files/directions/SavedDirectory.txt";
    static final String GENERATED_FILE = "data/files/directions/GeneratedDirectory.txt";
    static final String PYTHON_FILE = "data/files/directions/Python.txt";
    static final String JAVA_FILE = "data/files/directions/Java.txt";
    static final String JAVASCRIPT_FILE = "data/files/directions/JavaScript.txt";
    static final String C_FILE = "data/files/directions/C.txt";
    static final String C_PLUS_PLUS_FILE = "data/files/directions/CPlusPlus.txt";
    
    public static String getSublimeTextFolder() {
        return FileAccess.loadContent(SUBLIME_TEXT_3_FILE).trim();
    }

    public static void setSublimeTextFolder(String sublimeTextFolder) {
        FileAccess.savedContent(SUBLIME_TEXT_3_FILE, sublimeTextFolder);
    }

    public static String getSaveFolder() {
        return FileAccess.loadContent(SAVED_FILE).trim();
    }

    public static void setSaveFolder(String SaveFolder) {
        FileAccess.savedContent(SAVED_FILE, SaveFolder);
    }
    
    public static String getGeneratedFolder() {
        return FileAccess.loadContent(GENERATED_FILE).trim();
    }

    public static void setGeneratedFolder(String SaveFolder) {
        FileAccess.savedContent(GENERATED_FILE, SaveFolder);
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

    public void setJavaScriptFolder(String javaScriptFolder) {
        FileAccess.savedContent(JAVASCRIPT_FILE, javaScriptFolder);
    }
    
    public static String getCFolder() {
        return FileAccess.loadContent(C_FILE).trim();
    }

    public void setCFolder(String cFolder) {
        FileAccess.savedContent(C_FILE, cFolder);
    }
    
    public static String getCPlusPlusFolder() {
        return FileAccess.loadContent(C_PLUS_PLUS_FILE).trim();
    }

    public void setCPlusPlusFolder(String cPlusPlusScriptFolder) {
        FileAccess.savedContent(C_PLUS_PLUS_FILE, cPlusPlusScriptFolder);
    }
    
}
