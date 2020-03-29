
package fasttask.controller.runners;

// Interfaz para ejecutar funciones desde diferentes lenguajes

import java.awt.Color;

public interface Runner {
    
    // Obtener información de la clase.
    // Retorna:
    // - Descripción (Comentarios anteriores a la clase) 
    // - Nombres de las entradas (Nombres de los parametros de la clase)
    // - Nombre del lenguaje
    public Object[] info(String code);
    
    // Ejecutar clase (Devuleve la salida y el error) 
    // Retorna:
    // - Salida en consola de la calse
    // - Error de ejecución
    public void run(String code, String[] parameters);
    
    // Devolver retornos actuales de la ejecución:
    // - Salida en consola de la calse
    // - Error de ejecución
    public String[] returns();
    
    // Parar ejecución
    public void stop();
    
    // Obtener color representativo del lenguaje
    public Color color();
    
}
