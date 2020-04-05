
package fasttask.controller.code;

import java.io.BufferedWriter;

public interface CommandLine {
    
    static final boolean DEFAULT = true; 
    static final boolean ERROR = false; 
    
    // Al detectar escritura información en consola
    public void write(String text, boolean type);
    
    // Leer información en consola
    public void read(BufferedWriter writer);
    
    // Acciones al finalizar proceso
    public void onFinished();
    
    // Acciones al obtener un error de entrada y salida
    public void onIOException(int state);
    
}
