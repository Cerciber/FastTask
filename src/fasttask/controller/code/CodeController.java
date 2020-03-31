
package fasttask.controller.code;

import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Clase abstracta para ejecutar funciones desde diferentes lenguajes
public abstract class CodeController {
    
    // Constantes
    static final boolean RUNNING = true;    // Corriendo
    static final boolean STOPED = false;    // Detenido
    
    // Atributos
    String direction;   // Dirección
    boolean state;      // Estado (RUNNING / STOPED)
    
    // Constructor
    public CodeController(String direction) {
        this.direction = direction;
    }
    
    // Metodos abstractos
    public abstract String classNameRE();                           // Expresión regular para obtener nombre de la clase
    public abstract String descriptionRE();                         // Expresión regular para obtener descripción del codigo
    public abstract String parametersRE();                          // Expresión regular para obtener parametros
    public abstract String languaje();                              // Nombre del lenguaje
    public abstract Color color();                                  // Color representativo del lenguaje
    public abstract void creteExecutable(String[] parameters);      // Crear archivo generado para ejecutar
    public abstract String runCommand();                            // Comando de consola para ejecutar archivo generado
    
    // Obtener nombre del archivo
    public String name() {
        return FileAccess.getName(direction);
    }
    
    // Obtener extensión del archivo
    public String extention() {
        return FileAccess.getExtension(direction);
    }   
    
    // Obtener dirección del archivo
    public String direction() {
        return direction;
    }   
    
    // Obtener nombre de la clase
    public String className() {
        String className = "";
        Pattern pattern = Pattern.compile(classNameRE(), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(FileAccess.loadContent(direction));
        if (matcher.find()) {
            className = matcher.group(1);
        } 
        return className;
    }
    
    // Obtener descripción del codigo
    public String description() {
        String description = "";
        Pattern pattern = Pattern.compile(descriptionRE(), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(FileAccess.loadContent(direction));
        while (matcher.find()) {
            if (matcher.group(4) == null) {
                description += matcher.group(2);
            } else {
                description += matcher.group(4);
            }
        }
        return description;
    }
    
    // Obtener parametros
    public String[] parameters() {
        String[] parameters;
        Pattern pattern = Pattern.compile(parametersRE(), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(FileAccess.loadContent(direction));
        if (matcher.find() && !matcher.group(1).trim().equals("")) {
            parameters = matcher.group(1).split(",");
        } else {
            parameters = new String[]{};
        }
        return parameters;
    }                               
    
    // Correr direccionando resultados a una consola
    public void run(String[] parameters, CommandLine commandLine) {
        
        new Thread() {

            @Override
            public void run() {

                try {
                    
                    state = RUNNING;
                    
                    // Crear codigo del ejecutable
                    creteExecutable(parameters);

                    // Ejecutar clase
                    String command = runCommand();
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", command);
                    Process process = processBuilder.start();

                    // Obtener resultados
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "ISO-8859-1"));
                    String aux = stdInput.readLine();
                    while (state == RUNNING && aux != null) {
                        commandLine.write(aux + "\n");
                        aux = stdInput.readLine();
                    }
                    
                    // Obtener error
                    if (state == RUNNING) {
                        BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(process.getErrorStream(), "ISO-8859-1"));
                        String aux2 = stdInput2.readLine();
                        while (aux2 != null) {
                            commandLine.writeError(aux2 + "\n");
                            aux2 = stdInput2.readLine();
                        }
                    }

                } catch (IOException e) {
                    System.out.println(e);
                }

            }

        }.start();
        
    }  
    
    // Detener ejecución direccionando resultados a una consola
    public void stop(CommandLine commandLine) {
        state = STOPED;
    }    
    
    // Obtener controlador del lenguaje del archivo
    public static CodeController getController(String dir) {
        switch (FileAccess.getExtension(dir)) {
            case "java":
                return new JavaController(dir);
            case "py":
                return new PythonController(dir);
            case "js":
                return new JavaScriptController(dir);
        }
        return null;
    }
    
}
