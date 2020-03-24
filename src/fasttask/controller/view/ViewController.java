
package fasttask.controller.view;

public class ViewController {
    
    // Obtener lista de funciones (Devuelve el nombre, la descripción, el lenguaje y el nombre de los parametros)
    // Retorna una lista dode cada elemento es:
    // - Nombre (Nombre del archivo)
    // - Descripción (Comentarios anteriores a la función) 
    // - Lenguaje (Extención del archivo)
    // - Nombres de las entradas (Nombres de los parametros de la función)
    public Object[][] getFunctionList(){
        return new Object[][]{
            {"Función1", "Realizar una operación", "java", new String[]{"param1, param2, paramN"}},
            {"Función1", "Realizar una operación", "java", new String[]{"param1, param2, paramN"}},
            {"Función1", "Realizar una operación", "java", new String[]{"param1, param2, paramN"}}
        };
    }
    
}
