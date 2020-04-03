
package fasttask.controller.code;

import java.io.BufferedWriter;

public interface CommandLine {
    
    static final boolean DEFAULT = true; 
    static final boolean ERROR = false; 
    
    // Escribir información en consola
    public void write(String text, boolean type);
    
    // Leer información en consola
    public void read(BufferedWriter writer);
    
    // Acciones al iniciar proceso
    public void onRun();
    
    // Acciones al finalizar proceso
    public void onFinished();
    
}
