
package fasttask.view.components;

import java.awt.Color;

public interface Frameable {
    
    // Titulo de la ventana
    public void title(String title);
    
    // Color de la ventana
    public void color(Color color);
    
    // Evento al presionar el boton de configuración
    public void onConfuigurationClick();
    
    // Evento al presionar el boton de minimizar
    public void onMinimizeClick();
    
    // Evento al presionar el boton de maximizar
    public void onMaximizeClick();
    
    // Evento al presionar el boton de cerrar
    public void onCloseClick();
    
}
