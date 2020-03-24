
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
        pattern = Pattern.compile(".*?public[ ]*?(?:[^ ])*?[ ]*?[(](.*?)(?:,(.*?))[)]", Pattern.DOTALL);
        matcher = pattern.matcher(code);
        ArrayList<String> parameters = new ArrayList<>();
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                parameters.add(matcher.group(i).trim());
            }
        }
        
        return new Object[]{description, (String[]) parameters.toArray(new String[parameters.size()]), "Java"};
        
    }
    
    @Override
    public String[] run(String code, String[] parameters) {
        return new String[]{"", ""};
    }
    
}
