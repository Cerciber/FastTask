package fasttask.controller.code;

import fasttask.controller.settting.SettingController;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaController extends CodeController implements Runner {

    static final String JAVA_GENERATED_FILE = "src/fasttask/data/files/generated/JavaGenerated/JavaGenerated.java";
    static final String JAVA_GENERATED_DIRECTORY = "src/fasttask/data/files/generated/JavaGenerated";

    boolean stop;

    public JavaController(String direction) {
        super(direction);
    }

    @Override
    public Object[] info(String code) {

        // Leer descripcción
        Pattern pattern = Pattern.compile("(/[*](.*?)[*]/)|(//(.*?)\n)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(code);
        String description = "";
        while (matcher.find()) {
            if (matcher.group(4) == null) {
                description += matcher.group(2);
            } else {
                description += matcher.group(4);
            }
        }

        // Leer parametros
        pattern = Pattern.compile(".*?public[ ]*?(?:[^ ])*?[ ]*?[(](.*?)[)]", Pattern.DOTALL);
        matcher = pattern.matcher(code);
        String[] parameters;
        if (matcher.find() && !matcher.group(1).trim().equals("")) {
            parameters = matcher.group(1).split(",");
        } else {
            parameters = new String[]{};
        }

        return new Object[]{description, parameters, "Java"};

    }

    @Override
    public void run(String code, String[] parameters, CommandLine commandLine) {

        new Thread() {

            @Override
            public void run() {

                // Leer nombre de la clase
                Pattern pattern = Pattern.compile("class[ ].*?(.*?)[{]", Pattern.DOTALL);
                Matcher matcher = pattern.matcher(code);
                String nameClass;
                if (matcher.find()) {
                    nameClass = matcher.group(1);
                } else {
                    nameClass = "";
                }

                // Crear codigo del ejecutable
                String parametersString = Arrays.toString(parameters);
                String generatedCode = code + "\n class __Main {public static void main(String[] args) { new " + nameClass + "(" + parametersString.substring(1, parametersString.length() - 1) + ")" + ";}}";

                // Generar ejecutable
                FileAccess.deleteFilesInFolder(JAVA_GENERATED_DIRECTORY);
                FileAccess.createFile(JAVA_GENERATED_FILE);
                FileAccess.savedContent(JAVA_GENERATED_FILE, generatedCode);

                try {

                    // Ejecutar clase
                    String command = "pushd " + SettingController.getJavaFolder() + " "
                            + "&& javac \"" + new File(JAVA_GENERATED_FILE).getAbsolutePath() + "\" "
                            + "&& pushd \"" + new File(JAVA_GENERATED_DIRECTORY).getAbsolutePath() + "\""
                            + "&& java __Main";
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", command);
                    Process process = processBuilder.start();

                    // Obtener resultados
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "ISO-8859-1"));
                    String aux = stdInput.readLine();
                    System.out.println(aux);
                    while (!stop && aux != null) {
                        commandLine.write(aux + "\n");
                        aux = stdInput.readLine();
                        System.out.println(aux);
                    }
                    if (!stop) {
                        BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(process.getErrorStream(), "ISO-8859-1"));
                        String aux2 = stdInput2.readLine();
                        System.out.println(aux);
                        while (aux2 != null) {
                            System.out.println(process);
                            commandLine.writeError(aux2 + "\n");
                            aux2 = stdInput2.readLine();
                            System.out.println(aux);
                        }
                    }

                } catch (IOException e) {
                    System.out.println(e);
                }

            }

        }.start();

    }

    @Override
    public void stop() {
        stop = true;
    }

    @Override
    public Color color() {
        return new Color(210, 15, 15);
    }

    @Override
    public String classNameRE() {
        return "class[ ].*?(.*?)[{]";
    }

    @Override
    public String descriptionRE() {
        return "(/[*](.*?)[*]/)|(//(.*?)\n)";
    }

    @Override
    public String parametersRE() {
        return ".*?public[ ]*?(?:[^ ])*?[ ]*?[(](.*?)[)]";
    }

    @Override
    public String languaje() {
        return "Java";
    }

    @Override
    public void creteExecutable(String[] parameters) {

        // Crear codigo del ejecutable
        String parametersString = Arrays.toString(parameters);
        String generatedCode = FileAccess.loadContent(direction) + "\n class __Main {public static void main(String[] args) { new " + className() + "(" + parametersString.substring(1, parametersString.length() - 1) + ")" + ";}}";

        // Generar ejecutable
        FileAccess.deleteFilesInFolder(JAVA_GENERATED_DIRECTORY);
        FileAccess.createFile(JAVA_GENERATED_FILE);
        FileAccess.savedContent(JAVA_GENERATED_FILE, generatedCode);

    }

    @Override
    public String runCommand() {
        return "pushd " + SettingController.getJavaFolder() + " "
                + "&& javac \"" + new File(JAVA_GENERATED_FILE).getAbsolutePath() + "\" "
                + "&& pushd \"" + new File(JAVA_GENERATED_DIRECTORY).getAbsolutePath() + "\""
                + "&& java __Main";
    }

}
