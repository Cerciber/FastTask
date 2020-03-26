
package Data;

public class ConfigInformation {
   
    private static String SaveFolder = "C:\\Users\\acer\\Documents\\FastTask\\Saved files";
    private static String pythonFolder = "C:\\Users\\acer\\AppData\\Local\\Programs\\Python\\Python37-32";
    private static String javaFolder = "C:\\Program Files\\Java\\jdk1.8.0_221\\bin";
    private static String javaScriptFolder = "C:\\Program Files\\nodejs";
    private static String sublimeTextFolder = "C:\\Program Files\\Sublime Text 3";

    public static String getSublimeTextFolder() {
        return sublimeTextFolder;
    }

    public static void setSublimeTextFolder(String sublimeTextFolder) {
        ConfigInformation.sublimeTextFolder = sublimeTextFolder;
    }

    public static String getSaveFolder() {
        return ConfigInformation.SaveFolder;
    }

    public static void setSaveFolder(String SaveFolder) {
        ConfigInformation.SaveFolder = SaveFolder;
    }

    public static String getPythonFolder() {
        return ConfigInformation.pythonFolder;
    }

    public static void setPythonFolder(String pythonFolder) {
        ConfigInformation.pythonFolder = pythonFolder;
    }

    public static String getJavaFolder() {
        return ConfigInformation.javaFolder;
    }

    public static void setJavaFolder(String javaFolder) {
        ConfigInformation.javaFolder = javaFolder;
    }

    public static String getJavaScriptFolder() {
        return ConfigInformation.javaScriptFolder;
    }

    public void setJavaScriptFolder(String javaScriptFolder) {
        ConfigInformation.javaScriptFolder = javaScriptFolder;
    }
    
    
    
}
