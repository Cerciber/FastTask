
package fasttask.view.components;

import java.awt.Color;
import javax.swing.JPanel;

public interface Frameable {
    
    // Tipos de posición inicial
    public static final int CENTER = 0;         // Iniciar en el centro de la pantalla
    public static final int RANDOM = 1;         // Iniciar en una posición aleatoria
    
    // Tipos de cierre
    public static final int CLOSE_ACTUAL = 0;   // Crear ventana actual solamente
    public static final int CLOSE_PROGRAM = 1;  // Finalizar aplicación
    
    // Atributos
    public String title();      // Titulo de la ventana
    public Color color();       // Color de la ventana
    public JPanel content();    // Obtener contendio
    
    // Configuraciones
    public int typeStartLocation(); // Posición inicial
    public int typeClose();         // Tipo de cierre
    
    // Eventos
    public void onConfuigurationClick();    // Evento al presionar el boton de configuración
    public void onClose();                  // Evento al cerrar la ventana
    public void onGetFocus();               // Evento al obtener el foco de la ventana
    
}
