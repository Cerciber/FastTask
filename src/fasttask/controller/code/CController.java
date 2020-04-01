
package fasttask.controller.code;

import fasttask.controller.settting.SettingController;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.File;
import java.util.Arrays;

public class CController extends CodeController {
    
    static final String C_GENERATED_FILE = SettingController.getGeneratedFolder() + "\\CGenerated\\CGenerated.c";
    static final String C_GENERATED_DIRECTORY = SettingController.getGeneratedFolder() + "\\CGenerated";

    public CController(String direction) {
        super(direction);
    }

    @Override
    public String languaje() {
        return "C";
    }

    @Override
    public Color color() {
        return new Color(0, 150, 150);
    }

    @Override
    public String classNameRE() {
        return "void[ ].*?(.*?)[(]";
    }

    @Override
    public String descriptionRE() {
        return "(/[*](.*?)[*]/)|(//(.*?)\n)";
    }

    @Override
    public String parametersRE() {
        return ".*?void[ ]*?" + className() +  "[ ]*?[(](.*?)[)]";
    }

    @Override
    public void creteExecutable(String[] parameters) {

        // Crear codigo del ejecutable
        String parametersString = Arrays.toString(parameters);
        String generatedCode = FileAccess.loadContent(direction) + "\n int main() { " + className() + "(" + parametersString.substring(1, parametersString.length() - 1) + ");" + "}";

        // Generar ejecutable
        FileAccess.deleteFilesInFolder(C_GENERATED_DIRECTORY);
        FileAccess.createFile(C_GENERATED_FILE);
        FileAccess.savedContent(C_GENERATED_FILE, generatedCode);

    }

    @Override
    public String runCommand() {
        String name = FileAccess.getName(C_GENERATED_FILE);
        return "pushd \"" + new File(C_GENERATED_DIRECTORY).getAbsolutePath() + "\" "
                + "&& \"" + SettingController.getCPlusPlusFolder() + "\\gcc\" " + FileAccess.getNameExtention(C_GENERATED_FILE) + " -o " + name + " "
                + "&& " + name;
    }
    
}
