package fasttask.controller.code;

import fasttask.data.system.Directions;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class PythonController extends CodeController {

    static final String PYTHON_GENERATED_FILE = Directions.getGeneratedFolder() + "\\PythonGenerated\\PythonGenerated.py";
    static final String PYTHON_GENERATED_DIRECTORY = Directions.getGeneratedFolder() + "\\generated\\PythonGenerated";

    public PythonController(String direction) {
        super(direction);
    }
    
    @Override
    public String languaje() {
        return "Python";
    }
    
    @Override
    public Color color() {
        return FileAccess.getColor(Directions.getColorsFolder() + "\\Python.txt");
    }

    @Override
    public String classNameRE() {
        return "class[ ].*?(.*?)(?:[(]|[:])";
    }

    @Override
    public String descriptionRE() {
        return "('''(.*?)''')|(#(.*?)\n)";
    }

    @Override
    public String parametersRE() {
        return ".*?__init__[ ]*?[(](?:(?:.*?)),(.*?)[)]:";
    }

    @Override
    public void creteExecutable(String[] parameters) throws UnsupportedEncodingException, IOException {
        
        // Crear codigo del ejecutable
        String parametersString = Arrays.toString(parameters);
        String generatedCode = FileAccess.loadContent(direction) + "\n" + className() + "(" + parametersString.substring(1, parametersString.length() - 1) + ")";

        // Generar ejecutable
        FileAccess.savedContent(PYTHON_GENERATED_FILE, generatedCode);
        
    }

    @Override
    public String runCommand() throws IOException {
        return Directions.getPythonFolder() + "\\python.exe \"" + new File(PYTHON_GENERATED_FILE).getAbsoluteFile().toString() + "\"";
    }
    
    @Override
    public boolean isConfigurated() throws IOException {
        return new File(Directions.getPythonFolder() + "\\python.exe").exists();
    }

}
