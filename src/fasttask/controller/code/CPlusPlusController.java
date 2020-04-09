package fasttask.controller.code;

import fasttask.data.system.Directions;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CPlusPlusController extends CodeController {

    static final String C_PLUS_PLUS_GENERATED_FILE = Directions.getGeneratedFolder() + "/CPlusPlusGenerated/CPlusPlusGenerated.cpp";
    static final String C_PLUS_PLUS_GENERATED_DIRECTORY = Directions.getGeneratedFolder() + "/CPlusPlusGenerated";

    public CPlusPlusController(String direction) {
        super(direction);
    }

    @Override
    public String languaje() {
        return "C++";
    }

    @Override
    public Color color() {
        return FileAccess.getColor(Directions.getColorsFolder() + "\\CPlusPlus.txt");
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
        return ".*?construct[ ]*?(?:[^ ])*?[ ]*?[(](.*?)[)]";
    }

    @Override
    public void creteExecutable(String[] parameters) throws UnsupportedEncodingException, IOException {

        // Crear codigo del ejecutable
        String parametersString = Arrays.toString(parameters);
        String generatedCode = FileAccess.loadContent(direction) + "\n int main() { " + className() + "(" + parametersString.substring(1, parametersString.length() - 1) + ");" + "}";

        // Generar ejecutable
        FileAccess.deleteFilesInFolder(C_PLUS_PLUS_GENERATED_DIRECTORY);
        FileAccess.createFile(C_PLUS_PLUS_GENERATED_FILE);
        FileAccess.savedContent(C_PLUS_PLUS_GENERATED_FILE, generatedCode);

    }

    @Override
    public String runCommand() throws IOException {
        String name = FileAccess.getName(C_PLUS_PLUS_GENERATED_FILE);
        return "pushd \"" + new File(C_PLUS_PLUS_GENERATED_DIRECTORY).getAbsolutePath() + "\" "
                + "&& \"" + Directions.getCPlusPlusFolder() + "\\gcc\" " + FileAccess.getNameExtention(C_PLUS_PLUS_GENERATED_FILE) + " -g -o " + name + "  -lstdc++ "
                + "&& " + name;
    }

    @Override
    public void stop(CommandLine commandLine) throws IOException {
        super.stop(commandLine);
        System.out.println("taskkill /F /IM " + FileAccess.getName(C_PLUS_PLUS_GENERATED_FILE) + ".exe");
        new ProcessBuilder("cmd.exe", "/C", "taskkill /F /IM " + FileAccess.getName(C_PLUS_PLUS_GENERATED_FILE) + ".exe").start();
    }
    
    @Override
    public boolean isConfigurated() throws IOException {
        return new File(Directions.getCPlusPlusFolder() + "\\gcc.exe").exists();
    }

}
