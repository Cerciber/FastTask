package fasttask.controller.code;

import fasttask.data.system.Directions;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MatLabController extends CodeController {

    static final String MATHLAB_GENERATED_EXCECUTABLE = Directions.getGeneratedFolder() + "\\MatLabGenerated\\MatLabGenerated.m";
    static final String MATHLAB_GENERATED_LOG = Directions.getGeneratedFolder() + "\\MatLabGenerated\\log.txt";
    static final String MATHLAB_GENERATED_LOG_TRANSFORMED = Directions.getGeneratedFolder() + "\\MatLabGenerated\\log2.txt";
    static final String MATHLAB_GENERATED_DIRECTORY = Directions.getGeneratedFolder() + "\\MatLabGenerated";

    public MatLabController(String direction) {
        super(direction);
    }

    @Override
    public String languaje() {
        return "MatLab";
    }

    @Override
    public Color color() {
        return new Color(0, 0, 150);
    }

    @Override
    public String classNameRE() {
        return "classdef[ ].*?(.*?)[ \n\r\t]";
    }

    @Override
    public String descriptionRE() {
        return "([%][{](.*?)[%][}])|(%(.*?)\n)";
    }

    @Override
    public String parametersRE() throws IOException {
        return ".*?[=][ ]*?" + className() +  "[ ]*?[(](.*?)[)]";
    }

    @Override
    public void creteExecutable(String[] parameters) throws UnsupportedEncodingException, IOException {

        // Crear codigo del ejecutable
        String parametersString = Arrays.toString(parameters);
        String generatedCode = FileAccess.loadContent(direction);
        String generatedExcecutable = className() + "(" + parametersString.substring(1, parametersString.length() - 1) + ")" + ";";      
        
        // Generar ejecutable
        FileAccess.deleteFilesInFolder(MATHLAB_GENERATED_DIRECTORY);
        FileAccess.createFile(MATHLAB_GENERATED_DIRECTORY + "\\" + className() + ".m");
        FileAccess.createFile(MATHLAB_GENERATED_EXCECUTABLE);
        FileAccess.savedContent(MATHLAB_GENERATED_DIRECTORY + "\\" + className() + ".m", generatedCode);
        FileAccess.savedContent(MATHLAB_GENERATED_EXCECUTABLE, generatedExcecutable);

    }

    @Override
    public String runCommand() throws IOException {
        return "pushd \"" + new File(MATHLAB_GENERATED_DIRECTORY).getAbsolutePath() + "\" "
                + "&& matlab -nodesktop -nosplash -nojvm -minimize -wait -r "
                + "\"run('" + new File(MATHLAB_GENERATED_EXCECUTABLE).getAbsolutePath() + "');exit;\" "
                + "-logfile \"" + new File(MATHLAB_GENERATED_LOG).getAbsolutePath() + "\" "
                + "&& more +4 \"" + new File(MATHLAB_GENERATED_LOG).getAbsolutePath() + "\" "
                + "> \"" + new File(MATHLAB_GENERATED_LOG_TRANSFORMED).getAbsolutePath() + "\" "
                + "&& more \"" + new File(MATHLAB_GENERATED_LOG_TRANSFORMED).getAbsolutePath() + "\"";
    }
    
    @Override
    public boolean isConfigurated() throws IOException {
        return new File(Directions.getMatLabFolder()+ "\\matlab.exe").exists();
    }

}
