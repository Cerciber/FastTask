package fasttask.controller.code;

import fasttask.data.system.Directions;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.File;
import java.util.Arrays;

public class JavaController extends CodeController {

    static final String JAVA_GENERATED_FILE = Directions.getGeneratedFolder() + "\\JavaGenerated\\JavaGenerated.java";
    static final String JAVA_GENERATED_DIRECTORY = Directions.getGeneratedFolder() + "\\JavaGenerated";

    public JavaController(String direction) {
        super(direction);
    }

    @Override
    public String languaje() {
        return "Java";
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
        return "pushd " + Directions.getJavaFolder() + " "
                + "&& javac \"" + new File(JAVA_GENERATED_FILE).getAbsolutePath() + "\" "
                + "&& pushd \"" + new File(JAVA_GENERATED_DIRECTORY).getAbsolutePath() + "\""
                + "&& java __Main";
    }

}
