
package fasttask.controller.runners;

import fasttask.controller.system.FileController;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaScriptRunner implements Runner {

    @Override
    public Object[] info(String code) {

        // Leer descripcción
        Pattern pattern = Pattern.compile("(/[*](.*?)[*]/)|(//(.*?)\n)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(code);
        String description = "";
        while (matcher.find()) {
            if (matcher.group(4) == null)
                description += matcher.group(2);
            else
                description += matcher.group(4);
        } 
        
        // Leer parametros
        pattern = Pattern.compile(".*?constructor[ ]*?[(](.*?)[)]", Pattern.DOTALL);
        matcher = pattern.matcher(code);
        String[] parameters;
        if (matcher.find()) {
            parameters = matcher.group(1).split(",");
        } else {
            parameters = new String[]{};
        }
        
        return new Object[]{description, parameters, "JavaScript"};
        
    }
    
    @Override
    public String[] run(String code, String[] parameters) {
        
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
        String generatedCode = code + "\n" + "new " + nameClass + "(" + parametersString.substring(1, parametersString.length() - 1) + ");";
        
        // Generar ejecutable
        FileController fileController = new FileController();
        fileController.savedContent(new File("Data/Generated/JavaScriptGenerated.js"), generatedCode);
        
        try {
            
            // Ejecutar clase
            String command = "pushd C:\\Program Files\\nodejs "
                    + "&& node \"C:\\Users\\acer\\Google Drive\\CESAR\\6. PORTAFOLIO\\FastTask\\FastTask\\Data\\Generated\\JavaScriptGenerated.js\" ";
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", command);
            processBuilder.environment().put(code, code);
            Process proc = processBuilder.start();
            
            // Obtener resultados
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream(), "ISO-8859-1"));
            String aux = stdInput.readLine();
            String result1 = "";
            while (aux != null) {
                result1 += aux + "\n";
                aux = stdInput.readLine();
            }
            BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc.getErrorStream(), "ISO-8859-1"));
            aux = stdInput2.readLine();
            String result2 = "";
            while (aux != null) {
                result2 += aux + "\n";
                aux = stdInput2.readLine();
            }
            return new String[]{result1, result2};
            
        } catch (IOException e) {
            System.out.println(e);
        }
        return new String[]{"", ""};
        
        
    }

}
