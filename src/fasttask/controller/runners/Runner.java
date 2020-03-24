
package fasttask.controller.runners;

// Interfaz para ejecutar funciones desde diferentes lenguajes
public interface Runner {
    
    // Obtener información de la función.
    // Retorna:
    // - Nombre (Nombre del archivo)
    // - Descripción (Comentarios anteriores a la función) 
    // - Lenguaje (Extención del archivo)
    // - Nombres de las entradas (Nombres de los parametros de la función)
    public Object[] info(String dir);
    
    // Ejecutar función (Devuleve la salida y el error) 
    // Retorna:
    // - Salida de la función
    // - Error de ejecución
    public String[] run(String dir, String[] parameters);
    
}
