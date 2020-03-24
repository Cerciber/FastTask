
package fasttask.controller.view;

import fasttask.controller.runners.JavaRunner;
import fasttask.controller.runners.JavaScriptRunner;
import fasttask.controller.runners.PythonRunner;
import fasttask.controller.runners.Runner;
import fasttask.controller.system.FileController;
import java.io.File;

public class ViewController {
    
    FileController fileController;
    
    public ViewController() {
        
        fileController = new FileController();
        
    }
    
    // Obtener lista de funciones (Devuelve el nombre, la descripción, el lenguaje y el nombre de los parametros)
    // Retorna una lista dode cada elemento es:
    // - Nombre (Nombre del archivo)
    // - Descripción (Comentarios anteriores a la función) 
    // - Lenguaje (Extención del archivo)
    // - Nombres de las entradas (Nombres de los parametros de la función)
    public Object[][] getFunctionList(){
        
        File[] files = new File("Data/Saved").listFiles();  // Listar archivos
        Object[][] objects = new Object[files.length][4];   // Crear objectos de retorno para cada archivo
       
        // Para cada archivo
        for (int i = 0; i < objects.length; i++) {
            
            Runner runner = getRunner(files[i]);                        // Obtener runner del lenguaje detectado
            String content = fileController.loadContent(files[i]);      // Obtener codido contenido
            Object[] codeInfo = runner.info(content);                   // Obtener infromación del codigo
            objects[i] = new Object[]{files[i],                         // Obtener información del archivo y del codigo
                fileController.getName(files[i]), 
                                        codeInfo[0], 
                                        codeInfo[2], 
                                        codeInfo[1]};
            
        }
        
        return objects;
    }
    
    // Obtener runner del lenguaje del archivo
    public Runner getRunner(File dir){
        switch (fileController.getExtension(dir)) {
            case "java":
                return new JavaRunner();
            case "py":
                return new PythonRunner();
            case "js":
                return new JavaScriptRunner();
        }
        return null;
    }
    
}
