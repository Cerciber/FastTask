
package fasttask.controller.code;

public interface CommandLine {
    
    static final boolean DEFAULT = true; 
    static final boolean ERROR = false; 
    
    // Escribir información en consola
    public void write(String text, boolean type);
    
    // Leer información en consola
    public String read(String text);
    
}
