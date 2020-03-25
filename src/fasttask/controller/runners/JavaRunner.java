
package fasttask.controller.runners;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRunner implements Runner {

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
        pattern = Pattern.compile(".*?public[ ]*?(?:[^ ])*?[ ]*?[(](.*?)[)]", Pattern.DOTALL);
        matcher = pattern.matcher(code);
        String[] parameters;
        if (matcher.find()) {
            parameters = matcher.group(1).split(",");
        } else {
            parameters = new String[]{};
        }
        
        return new Object[]{description, parameters, "Java"};
        
    }
    
    @Override
    public String[] run(String code, String[] parameters) {
        return new String[]{"", ""};
    }
    
}
