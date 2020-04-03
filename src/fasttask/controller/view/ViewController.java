package fasttask.controller.view;

import fasttask.controller.code.CodeController;
import fasttask.controller.settting.SettingController;
import fasttask.data.system.FileAccess;
import fasttask.view.windows.Principal;
import fasttask.view.windows.RunClass;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ViewController {

    public FileAccess fileController;
    Principal principal;
    public ArrayList<RunClass> activedClasses;
    public static boolean confActived = false;

    public ViewController(Principal principal) {

        fileController = new FileAccess();
        this.principal = principal;
        activedClasses = new ArrayList<>();

    }

    // Obtener lista de clases (Devuelve el nombre, la descripción, el lenguaje y el nombre de los parametros)
    // Retorna una lista dode cada elemento es:
    // - Nombre (Nombre del archivo)
    // - Descripción (Comentarios anteriores a la función) 
    // - Lenguaje (Extención del archivo)
    // - Nombres de las entradas (Nombres de los parametros de la función)
    public Object[][] getClassList(String filter) {

        File[] files = new File(SettingController.getSaveFolder()).listFiles(new FilenameFilter() {  // Listar archivos que cumplan el filtro
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().contains(filter.toLowerCase());
            }
        });  
        ArrayList<Object[]> objects = new ArrayList<>();         // Crear objectos de retorno para cada archivo

        // Para cada archivo
        for (int i = 0; i < files.length; i++) {

            CodeController codeController = CodeController.getController(files[i].getAbsolutePath()); 
            
            if (codeController != null) {        
                objects.add(new Object[]{
                    codeController.direction(),
                    codeController.name(),
                    codeController.description(),
                    codeController.languaje(),
                    codeController.parameters()});
            }    

        }

        return objects.toArray(new Object[][]{});
    }

    // Añadir clase a la lista
    public void addClass(String dir) {
        fileController.copyFile(dir, SettingController.getSaveFolder() + "\\" + fileController.getName(dir) + "." + fileController.getExtension(dir));
    }

    // Eliminar clase de la lista
    public void removeClass(String dir) {
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
            if (activedClasses.get(i).direction.equals(runClass.direction)) {
                activedClasses.get(i).frame.setVisible(false);
                activedClasses.get(i).frame.dispose();
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
    
    public static BufferedImage colorImageBuffer(Icon image1, Color color) {
        
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
        
        return image;
    }

}
