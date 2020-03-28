package fasttask.controller.view;

import fasttask.data.ConfigInformation;
import fasttask.controller.runners.JavaRunner;
import fasttask.controller.runners.JavaScriptRunner;
import fasttask.controller.runners.PythonRunner;
import fasttask.controller.runners.Runner;
import fasttask.controller.system.FileController;
import fasttask.view.Principal;
import fasttask.view.Principal;
import fasttask.view.RunClass;
import fasttask.view.RunClass;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ViewController {

    FileController fileController;
    Principal principal;
    public ArrayList<RunClass> activedClasses;

    public ViewController(Principal principal) {

        fileController = new FileController();
        this.principal = principal;
        activedClasses = new ArrayList<>();

    }

    // Obtener lista de clases (Devuelve el nombre, la descripción, el lenguaje y el nombre de los parametros)
    // Retorna una lista dode cada elemento es:
    // - Nombre (Nombre del archivo)
    // - Descripción (Comentarios anteriores a la función) 
    // - Lenguaje (Extención del archivo)
    // - Nombres de las entradas (Nombres de los parametros de la función)
    public Object[][] getClassList(String filter) {

        File[] files = new File(ConfigInformation.getSaveFolder()).listFiles(new FilenameFilter() {  // Listar archivos que cumplan el filtro
            public boolean accept(File dir, String name) {
                return name.toLowerCase().contains(filter.toLowerCase());
            }
        });  
        Object[][] objects = new Object[files.length][4];         // Crear objectos de retorno para cada archivo

        // Para cada archivo
        for (int i = 0; i < objects.length; i++) {

            Runner runner = getRunner(files[i].getAbsolutePath());                        // Obtener runner del lenguaje detectado
            String content = fileController.loadContent(files[i].getAbsolutePath());      // Obtener codido contenido
            Object[] codeInfo = runner.info(content);                   // Obtener infromación del codigo
            objects[i] = new Object[]{files[i].getAbsolutePath(), // Obtener información del archivo y del codigo
                fileController.getName(files[i].getAbsolutePath()),
                codeInfo[0],
                codeInfo[2],
                codeInfo[1]};

        }

        return objects;
    }

    // Obtener runner del lenguaje del archivo
    public Runner getRunner(String dir) {
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

    // Ejecutar clase y obtener retornos
    public String[] runClass(String dir, String[] parameters) {

        Runner runner = getRunner(dir);                             // Obtener runner del lenguaje detectado
        String content = fileController.loadContent(dir);           // Obtener codido contenido
        String[] returns = runner.run(content, parameters);         // Obtener infromación del codigo

        return returns;
    }

    // Añadir clase a la lista
    public void addClass(String dir) {
        fileController.copyFile(dir, ConfigInformation.getSaveFolder() + "\\" + fileController.getName(dir) + "." + fileController.getExtension(dir));
    }

    // Eliminar clase de la lista
    public void removeClass(String dir) {
        fileController.deleteFile(dir);
    }
    
    // Agregar ventana de ejecución activa
    public void addActivedClass(RunClass runClass){
       activedClasses.add(runClass);
    }
    
    // Agregar ventana de ejecución activa
    public void removeActivedClass(RunClass runClass){
        
       // Remover clases activas con la misma ruta
        for (int i = activedClasses.size() - 1; i >= 0; i--) {
            if (activedClasses.get(i).direction.equals(runClass.direction)) {
                activedClasses.get(i).dispatchEvent(new WindowEvent(activedClasses.get(i).frame, WindowEvent.WINDOW_CLOSING));
                activedClasses.remove(activedClasses.get(i));
            }
        }
       
       // Actualizar lista
       principal.setFunctionList();
       
    }

}
