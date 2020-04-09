package fasttask.data.system;

import java.awt.Color;

public class Constants {

    // Datos generales
    public static final String APP_NAME = "TrimCode";

    // Tipos de notificación
    public static final String ACCESS_ERROR = "Error de acceso";
    public static final String CREATION_ERROR = "Error de creación";
    public static final String CHANGE_ERROR = "Error de modificación";

    // Mensajes de notificación
    public static final String CONFIG_FILE_NO_FOUND = "No se pudo acceder a la configuración";
    public static final String DIRECTION_NO_FOUND = "La dirección de la carpeta no existe";
    public static final String FILE_NO_FOUND = "La dirección del archivo no existe";
    public static final String FILE_ALREADY_EXIST = "La dirección del archivo ya existe";
    public static final String NAME_ALREADY_EXIST = "El nombre del archivo ya existe";
    public static final String LANGUAJE_NOT_SUPPORTED = "El lenguaje no está soportado";
    public static final String TEMPLATE_COPY_NOT_FOUND = "No se pudo copiar la plantilla";
    public static final String CREATE_FILE_NOT_FOUND = "No se pudo crear el archivo";
    public static final String DELETE_FILE_NOT_FOUND = "No se pudo borrar el archivo";
    public static final String DESCRIPTION_NOT_FOUND = "No se pudo acceder a la descripción del archivo";
    public static final String PARAMETERS_NOT_FOUND = "No se pudo acceder a los parametros del archivo";
    public static final String INFORMATION_NOT_FOUND = "No se pudo acceder a la información del archivo";
    public static final String COMPILER_NOT_FOUND = "La dirección del compilador es incorrecta";
    public static final String EDITOR_NOT_FOUND = "La dirección del editor es incorrecta";
    public static final String SAVED_FILE_NOT_FOUND = "La dirección de la carpta de códigos es incorrecta";
    public static final String STOP_EJECUTION_NOT_FOUND = "No se pudo detener la ejecución";
    public static final String READ_CONSOLE_NOT_FOUND = "No se pudo leer la información en consola";
    public static final String CONSOLE_NOT_FOUND = "No se pudo acceder al terminal";
    
    // Imagenes
    public static final String MAXIMIZE_ICON = "/fasttask/data/files/images/Maximizar.png";
    public static final String CONFIGURATION_ICON = "/fasttask/data/files/images/ajustes.png";
    public static final String ADD_ICON = "/fasttask/data/files/images/anadir.png";
    public static final String DELETE_ICON = "/fasttask/data/files/images/borrar.png";
    public static final String SEARCH_ICON = "/fasttask/data/files/images/buscar.png";
    public static final String CLOSE_ICON = "/fasttask/data/files/images/cerrar.png";
    public static final String CHECK_ICON = "/fasttask/data/files/images/check.png";
    public static final String CLEAN_ICON = "/fasttask/data/files/images/clean.png";
    public static final String CLOSE_ALL_ICON = "/fasttask/data/files/images/closeall.png";
    public static final String COLOR_ICON = "/fasttask/data/files/images/color.png";
    public static final String EDIT_ICON = "/fasttask/data/files/images/editar.png";
    public static final String MINIMIZE_ICON = "/fasttask/data/files/images/menos.png";
    public static final String PLAY_ICON = "/fasttask/data/files/images/play.png";
    public static final String STOP_ICON = "/fasttask/data/files/images/stop.png";

    // Color principal
    public static final Color mainFrameColor() {
        return FileAccess.getColor(Directions.getColorsFolder() + "\\General.txt");
    }   

}
