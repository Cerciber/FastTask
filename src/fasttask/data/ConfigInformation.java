
package fasttask.data;

import fasttask.controller.system.FileController;

public class ConfigInformation {
   
    public static String getSublimeTextFolder() {
        return new FileController().loadContent("src/fasttask/data/SublimeText3.txt").trim();
    }

    public static void setSublimeTextFolder(String sublimeTextFolder) {
        new FileController().savedContent("src/fasttask/data/SublimeText3.txt", sublimeTextFolder);
    }

    public static String getSaveFolder() {
        return new FileController().loadContent("src/fasttask/data/ClassesDirectory.txt").trim();
    }

    public static void setSaveFolder(String SaveFolder) {
        new FileController().savedContent("src/fasttask/data/ClassesDirectory.txt", SaveFolder);
    }

    public static String getPythonFolder() {
        return new FileController().loadContent("src/fasttask/data/Python.txt").trim();
    }

    public static void setPythonFolder(String pythonFolder) {
        new FileController().savedContent("src/fasttask/data/Python.txt", pythonFolder);
    }

    public static String getJavaFolder() {
        return new FileController().loadContent("src/fasttask/data/Java.txt").trim();
    }

    public static void setJavaFolder(String javaFolder) {
        new FileController().savedContent("src/fasttask/data/Java.txt", javaFolder);
    }

    public static String getJavaScriptFolder() {
        return new FileController().loadContent("src/fasttask/data/JavaScript.txt").trim();
    }

    public void setJavaScriptFolder(String javaScriptFolder) {
        new FileController().savedContent("src/fasttask/data/JavaScript.txt", javaScriptFolder);
    }
    
    
    
}
