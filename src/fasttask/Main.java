
package fasttask;

import fasttask.data.system.Constants;
import fasttask.data.system.Directions;
import fasttask.view.windows.Principal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        
        // Asignar vista de windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        
        // Asignar direcciones
        Directions.setConstants();
        
        // Iniciar ventana
        new Principal();
        
    }
    
}
