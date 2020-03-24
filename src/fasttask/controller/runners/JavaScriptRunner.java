
package fasttask.controller.runners;

public class JavaScriptRunner implements Runner {

    @Override
    public Object[] info(String dir) {
        return new Object[]{};
    }
    
    @Override
    public String[] run(String dir, String[] parameters) {
        return new String[]{"", ""};
    }

}
