package fasttask.controller.view;

import fasttask.controller.code.CodeController;
import fasttask.data.system.Directions;
import fasttask.data.system.FileAccess;
import fasttask.view.windows.Principal;
import fasttask.view.windows.RunClass;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ViewController {

    public FileAccess fileController;
    Principal principal;
    public ArrayList<RunClass> activedClasses;
    public static boolean configurationActived = false;

    public ViewController(Principal principal) {

        fileController = new FileAccess();
        this.principal = principal;
        activedClasses = new ArrayList<>();

    }

    // Obtener lista de codigos (Devuelve la lista de direcciones)
    public String[] getDirectionsList(String filter) throws IOException {

        // Obtener lista de Files que cumplen el filtro
        File[] files = new File(Directions.getSaveFolder()).listFiles((File dir, String name) -> {
            CodeController codeController = CodeController.getController(dir + "\\" + name);
            String[] filterParts = filter.split(",", 3);
            try {
                if (codeController != null && codeController.name().toLowerCase().contains(filterParts[0].trim().toLowerCase())
                        && (codeController.languaje().toLowerCase().startsWith(filterParts[1].trim().toLowerCase()) || codeController.extention().toLowerCase().startsWith(filterParts[1].trim().toLowerCase()))
                        && codeController.description().toLowerCase().contains(filterParts[2].trim().toLowerCase())) {
                    return true;
                }
            } catch (IOException ex) {
            }
            return false;
        });
        
        if (files == null) {
            files = new File[0];
        }
        
        // Obtener lista de cadenas de direcciones
        String[] directions = new String[files.length];         
        for (int i = 0; i < files.length; i++) {
            directions[i] = files[i].getAbsolutePath();
            CodeController codeController = CodeController.getController(files[i].getAbsolutePath()); 
        }

        return directions;
        
    }

    // Añadir clase a la lista
    public boolean addClass(String dir) throws IOException {
        return FileAccess.copyFile(dir, Directions.getSaveFolder() + "\\" + FileAccess.getName(dir) + "." + FileAccess.getExtension(dir));
    }

    // Eliminar clase de la lista
    public void removeClass(String dir) throws IOException {
        fileController.deleteFile(dir);
    }
    
    // Agregar ventana de ejecución activa
    public void addActivedClass(RunClass runClass){
       activedClasses.add(runClass);
    }
    
    // Remover clases activas que contengan la dirección
    public void removeActivedClass(RunClass runClass){
        
       // Remover clases activas con la misma ruta
        for (int i = activedClasses.size() - 1; i >= 0; i--) {
            if (activedClasses.get(i).codeController.direction().equals(runClass.codeController.direction())) {
                activedClasses.get(i).frame.close();
                activedClasses.remove(activedClasses.get(i));
            }
        }
       
       // Actualizar lista
       principal.setFunctionList();
       
    }
    
    // Remover clases activas
    public void removeActivedClass(){
        
       // Remover clases activas con la misma ruta
        for (int i = activedClasses.size() - 1; i >= 0; i--) {
            activedClasses.get(i).frame.setVisible(false);
            activedClasses.get(i).frame.dispose();
        }
        activedClasses.clear();
       
       // Actualizar lista
       principal.setFunctionList();
       
    }
    
    // Cambiar color de un icono
    public static Icon colorImage(Icon image1, Color color) {
        
        BufferedImage image = new BufferedImage(
            image1.getIconWidth(),
            image1.getIconHeight(),
            BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.createGraphics();
        image1.paintIcon(null, g, 0,0);
        g.dispose();
        
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                if (pixels[0] < 200 && pixels[1] < 200 && pixels[2] < 200) {
                    pixels[0] = Math.min(255, color.getRed() + pixels[0]);
                    pixels[1] = Math.min(255, color.getGreen() + pixels[1]);
                    pixels[2] = Math.min(255, color.getBlue() + pixels[2]);
                }
                raster.setPixel(x, y, pixels);
            }
        }
        
        return new ImageIcon(image);
    }
    
    public static Icon colorLightImage(Icon image1, Color color) {

        BufferedImage image = new BufferedImage(
            image1.getIconWidth(),
            image1.getIconHeight(),
            BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.createGraphics();
        image1.paintIcon(null, g, 0,0);
        g.dispose();
        
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                if (pixels[0] < 200 && pixels[1] < 200 && pixels[2] < 200) {
                    pixels[0] = Math.min(255, color.getRed() + pixels[0] + 150);
                    pixels[1] = Math.min(255, color.getGreen() + pixels[1] + 150);
                    pixels[2] = Math.min(255, color.getBlue() + pixels[2] + 150);
                }
                raster.setPixel(x, y, pixels);
            }
        }
        
        return new ImageIcon(image);

    }
    
    public static void customizeButton(JLabel label, Color color){
        
        Icon icon1 = colorImage(label.getIcon(), color);
        Icon icon2 = colorLightImage(label.getIcon(), color);
        
        label.setIcon(icon1);
        
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setIcon(icon2);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setIcon(icon1);
            }
        });
        
    }
    
}
