package fasttask.controller.code;

import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Clase abstracta para ejecutar funciones desde diferentes lenguajes
public abstract class CodeController {

    // Constantes de estado
    public static final int CREATING_EXCECUTABLE = 1;   // Indicador de que se está creando el ejecutable
    public static final int GETTING_COMMAND = 2;        // Indicador de que se está obteniendo el comando de ejecución
    public static final int RUNNING_COMMAND = 3;        // Indicador de que se está corriendo el comando
    public static final int READING_CONSOLE = 4;        // Indicador de que se está leyendo y escribiendo en consola
    public static final int STOPED = 5;                 // Indicador de que no se esta procesando una ejecución

    // Atributos
    String direction;   // Dirección
    int state;          // Estado

    // Constructor
    public CodeController(String direction) {
        this.direction = direction;
        this.state = STOPED;
    }

    // Metodos abstractos
    public abstract String languaje();                              // Nombre del lenguaje

    public abstract Color color();                                  // Color representativo del lenguaje

    public abstract String classNameRE();                           // Expresión regular para obtener nombre de la clase

    public abstract String descriptionRE();                         // Expresión regular para obtener descripción del codigo

    public abstract String parametersRE() throws IOException;       // Expresión regular para obtener parametros

    public abstract void creteExecutable(String[] parameters) throws UnsupportedEncodingException, IOException;      // Crear archivo generado para ejecutar

    public abstract String runCommand() throws IOException;         // Comando de consola para ejecutar archivo generado

    // Obtener nombre del archivo
    public String name() {
        return FileAccess.getName(direction);
    }

    // Obtener extensión del archivo
    public String extention() {
        return FileAccess.getExtension(direction);
    }
    
    // Obtener nombre y extensión del archivo
    public String nameExtention() {
        return FileAccess.getNameExtention(direction);
    }

    // Obtener dirección del archivo
    public String direction() {
        return direction;
    }

    // Obtener nombre de la clase
    public String className() throws UnsupportedEncodingException, IOException {
        String className = "";
        Pattern pattern = Pattern.compile(classNameRE(), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(FileAccess.loadContent(direction));
        if (matcher.find()) {
            className = matcher.group(1);
        }
        return className;
    }

    // Obtener descripción del codigo
    public String description() throws UnsupportedEncodingException, IOException {
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
    public String[] parameters() throws UnsupportedEncodingException, IOException {
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
                    
                    // Crear codigo del ejecutable
                    state = CREATING_EXCECUTABLE;
                    creteExecutable(parameters);

                    // Obtener comando
                    state = GETTING_COMMAND;
                    String command = runCommand();
                    System.out.println(command);
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", command);
                    
                    // Ejecutar codigo
                    state = RUNNING_COMMAND;
                    Process process = processBuilder.start();

                    // Obtener resultados
                    state = READING_CONSOLE;
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                    BufferedWriter  stdOutput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), "UTF-8"));
                    String aux;
                    commandLine.read(stdOutput);
                    while (state == READING_CONSOLE && (aux = stdInput.readLine()) != null) {
                        commandLine.write(aux + "\n", CommandLine.DEFAULT);
                    }

                    // Obtener error
                    if (state == READING_CONSOLE) {
                        BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
                        String aux2 = stdInput2.readLine();
                        while (aux2 != null) {
                            commandLine.write(aux2 + "\n", CommandLine.ERROR);
                            aux2 = stdInput2.readLine();
                        }
                    }

                } catch (IOException e) {
                    commandLine.onIOException(state);
                }
                
                state = STOPED;
                commandLine.onFinished();

            }

        }.start();

    }

    // Detener ejecución direccionando resultados a una consola
    public void stop(CommandLine commandLine) throws IOException {
        state = STOPED;
    }

    // Obtener estado
    public int getState(){
        return state;
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
            case "c":
                return new CController(dir);
            case "cpp":
                return new CPlusPlusController(dir);
        }
        return null;
    }
    
    // Verificar si el lenguaje está soportado
    public static boolean isSupported(String dir) {
        switch (FileAccess.getExtension(dir)) {
            case "java":
            case "py":
            case "js":
            case "c":
            case "cpp":
                return true;
                
        }
        return false;
    }

}
