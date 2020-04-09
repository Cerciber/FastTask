package fasttask.view.windows;

import fasttask.data.system.Directions;
import fasttask.controller.view.ViewController;
import fasttask.data.system.Constants;
import fasttask.data.system.FileAccess;
import fasttask.view.components.Dialog;
import fasttask.view.components.FileChooser;
import fasttask.view.components.Frame;
import fasttask.view.components.Frameable;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public final class Configuration extends javax.swing.JPanel implements Frameable {

    // Ventanas
    Principal principal;

    // Ventanas
    Frame frame;        // Ventana contenedora

    public Configuration(Principal principal) {

        this.principal = principal;

        initComponents();       // Iniciar componentes generados
        setInformation();       // Asignar información
        setCustomization();     // Personalizar

        frame = new Frame(this);

    }

    // Personalizar ventana
    public void setCustomization() {
        
        // Personalizar botones
        ViewController.customizeButton(jLabel3, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel4, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel6, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel5, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel7, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel8, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel10, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel11, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel13, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel15, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel17, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel18, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel24, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel25, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());

        // Colores de los lenguajes
        ViewController.customizeButton(jLabel26, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\Java.txt"));
        ViewController.customizeButton(jLabel27, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\Python.txt"));
        ViewController.customizeButton(jLabel28, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\JavaScript.txt"));
        ViewController.customizeButton(jLabel29, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\C.txt"));
        ViewController.customizeButton(jLabel33, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\CPlusPlus.txt"));
        ViewController.customizeButton(jLabel30, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\MatLab.txt"));
        ViewController.customizeButton(jLabel31, new javax.swing.ImageIcon(getClass().getResource(Constants.COLOR_ICON)), FileAccess.getColor(Directions.getColorsFolder() + "\\General.txt"));
        
        // Personalizar bordes
        jTextField1.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField2.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField3.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField4.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField5.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField6.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField7.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        
        if (frame != null) {
            frame.setCustomization();
        }
        
    }

    // Asignar información
    public void setInformation() {

        try {

            // Direcciones
            jTextField1.setText(Directions.getSaveFolder());
            jTextField2.setText(Directions.getEditorFile());
            jTextField3.setText(Directions.getJavaFolder());
            jTextField4.setText(Directions.getPythonFolder());
            jTextField5.setText(Directions.getJavaScriptFolder());
            jTextField6.setText(Directions.getCPlusPlusFolder());
            jTextField7.setText(Directions.getMatLabFolder());

        } catch (IOException ex) {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), principal);
            dialog.setTitle(Constants.ACCESS_ERROR);
            dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
            dialog.show();
        }

    }

    @Override
    public String title() {
        return "Configuración";
    }

    @Override
    public Color color() {
        return Constants.mainFrameColor();
    }

    @Override
    public JPanel content() {
        return this;
    }

    @Override
    public int typeStartLocation() {
        return Frameable.RANDOM;
    }

    @Override
    public int typeClose() {
        return Frameable.CLOSE_ACTUAL;
    }

    @Override
    public void onConfuigurationClick() {
        // No se necesita
    }

    @Override
    public void onClose() {
        ViewController.configurationActived = false;
    }

    @Override
    public void onGetFocus() {
        setInformation();
        setCustomization();
    }

    // Al presionar el boton de cambiar color general
    public void onChangeGeneralColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\General.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\General.txt", color);
                setCustomization();
                principal.onGetFocus();
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }
    
    // Al presionar el boton de buscar directorio de clases guardadas
    public void onSearchSavedFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField1.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendSavedFolder();
        }
    }

    // Al presionar el boton agregar directorio de clases guardadas
    public void onAppendSavedFolder() {
        if (new File(jTextField1.getText()).exists()) {
            try {
                Directions.setSaveFolder(jTextField1.getText());
                principal.onGetFocus();
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.DIRECTION_NO_FOUND);
            dialog.show();
        }
    }

    // Al presionar el boton de buscar ejecutable de editor de codigo
    public void onSearchEditExecutable() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField2.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendEditExecutable();
        }
    }

    // Al presionar el boton agregar ejecutable de editor de codigo
    public void onAppendEditExecutable() {
        if (new File(jTextField2.getText()).exists()) {
            try {
                Directions.setEditorFile(jTextField2.getText());
                principal.setFunctionList(principal.jTextField2.getText());
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.FILE_NO_FOUND);
            dialog.show();
        }
    }

    // Al presionar el boton de buscar directorio de Java
    public void onSearchJavaFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField3.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendJavaFolder();
        }
    }

    // Al presionar el boton agregar directorio de Java
    public void onAppendJavaFolder() {
        if (new File(jTextField3.getText()).exists()) {
            try {
                Directions.setJavaFolder(jTextField3.getText());
                principal.setFunctionList(principal.jTextField2.getText());
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.DIRECTION_NO_FOUND);
            dialog.show();
        }
    }

    // Al presionar el boton de cambiar color de Java
    public void onChangeJavaColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\Java.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\Java.txt", color);
                setCustomization();
                principal.onGetFocus();
                principal.viewController.updateActivedClass("Java");
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }
    
    // Al presionar el boton de buscar directorio de Python
    public void onSearchPythonFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField4.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendPythonFolder();
        }
    }

    // Al presionar el boton agregar directorio de Python
    public void onAppendPythonFolder() {
        if (new File(jTextField4.getText()).exists()) {
            try {
                Directions.setPythonFolder(jTextField4.getText());
                principal.setFunctionList(principal.jTextField2.getText());
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.DIRECTION_NO_FOUND);
            dialog.show();
        }
    }

    // Al presionar el boton de cambiar color de Python
    public void onChangePythonColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\Python.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\Python.txt", color);
                setCustomization();
                principal.onGetFocus();
                principal.viewController.updateActivedClass("Python");
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }
    
    // Al presionar el boton de buscar directorio de JavaScript
    public void onSearchJavaScriptFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField5.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendJavaScriptFolder();
        }
    }

    // Al presionar el boton agregar directorio de JavaScript
    public void onAppendJavaScriptFolder() {
        if (new File(jTextField5.getText()).exists()) {
            try {
                Directions.setJavaScriptFolder(jTextField5.getText());
                principal.setFunctionList(principal.jTextField2.getText());
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.DIRECTION_NO_FOUND);
            dialog.show();
        }
    }

    // Al presionar el boton de cambiar color de JavaScript
    public void onChangeJavaScriptColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\JavaScript.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\JavaScript.txt", color);
                setCustomization();
                principal.onGetFocus();
                principal.viewController.updateActivedClass("JavaScript");
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }
    
    // Al presionar el boton de buscar directorio de C y C++
    public void onSearchCPlusPlusFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField6.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendCPlusPlusFolder();
        }
    }

    // Al presionar el boton agregar directorio de C y C++
    public void onAppendCPlusPlusFolder() {
        if (new File(jTextField6.getText()).exists()) {
            try {
                Directions.setCPlusPlusFolder(jTextField6.getText());
                principal.setFunctionList(principal.jTextField2.getText());
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.DIRECTION_NO_FOUND);
            dialog.show();
        }
    }

    // Al presionar el boton de cambiar color de C
    public void onChangeCColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\C.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\C.txt", color);
                setCustomization();
                principal.onGetFocus();
                principal.viewController.updateActivedClass("C");
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }
    
    // Al presionar el boton de cambiar color de C++
    public void onChangeCPlusPlusColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\CPlusPlus.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\CPlusPlus.txt", color);
                setCustomization();
                principal.onGetFocus();
                principal.viewController.updateActivedClass("CPlusPlus");
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }
    
    // Al presionar el boton de buscar directorio de C y C++
    public void onSearchMatLabFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField7.setText(fileChooser.getSelectedFile().getAbsolutePath());
            onAppendMatLabFolder();
        }
    }

    // Al presionar el boton agregar directorio de C y C++
    public void onAppendMatLabFolder() {
        if (new File(jTextField7.getText()).exists()) {
            try {
                Directions.setMatLabFolder(jTextField7.getText());
                principal.setFunctionList(principal.jTextField2.getText());
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        } else {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.CHANGE_ERROR);
            dialog.setDescription(Constants.DIRECTION_NO_FOUND);
            dialog.show();
        }
    }
    
    // Al presionar el boton de cambiar color de MatLab
    public void onChangeMatLabColor() {
        Color color = JColorChooser.showDialog(this, "Seleccione un Color", FileAccess.getColor(Directions.getColorsFolder() + "\\MatLab.txt"));
        if (color != null) {
            try {
                FileAccess.saveColor(Directions.getColorsFolder() + "\\MatLab.txt", color);
                setCustomization();
                principal.onGetFocus();
                principal.viewController.updateActivedClass("MatLab");
            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
                dialog.show();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(246, 246, 246));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel1.setText("Directorios para almacenar su codigos");
        jLabel1.setOpaque(true);

        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField1.setAlignmentX(2.0F);
        jTextField1.setAlignmentY(2.0F);
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(246, 246, 246));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel2.setText("Editor: ");
        jLabel2.setOpaque(true);

        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField2.setAlignmentX(2.0F);
        jTextField2.setAlignmentY(2.0F);
        jTextField2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField3.setAlignmentX(2.0F);
        jTextField3.setAlignmentY(2.0F);
        jTextField3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(246, 246, 246));
        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel9.setText("Colores y directorios de sus compiladores");
        jLabel9.setOpaque(true);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField4.setAlignmentX(2.0F);
        jTextField4.setAlignmentY(2.0F);
        jTextField4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(246, 246, 246));
        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel12.setText("Python: (Python\\Python37-32\\python.exe)");
        jLabel12.setOpaque(true);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField5.setAlignmentX(2.0F);
        jTextField5.setAlignmentY(2.0F);
        jTextField5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(246, 246, 246));
        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel14.setText("JavaScript: (nodejs\\node.exe)");
        jLabel14.setOpaque(true);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(246, 246, 246));
        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel16.setText("C y C++: (MinGW\\bin\\gcc.exe)");
        jLabel16.setOpaque(true);

        jTextField6.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField6.setAlignmentX(2.0F);
        jTextField6.setAlignmentY(2.0F);
        jTextField6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel18MousePressed(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(246, 246, 246));
        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel19.setText("Directorio de codigos ingresados:");
        jLabel19.setOpaque(true);

        jLabel20.setBackground(new java.awt.Color(246, 246, 246));
        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel20.setText("Ejecutable para su editor de texto preferido");
        jLabel20.setOpaque(true);

        jLabel21.setBackground(new java.awt.Color(246, 246, 246));
        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel21.setText("Java: (Java\\jdk...\\bin\\java.exe) y (Java\\jdk...\\bin\\javac.exe)");
        jLabel21.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(246, 246, 246));
        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 3, 13)); // NOI18N
        jLabel22.setText("(Verifique que contengan los ejecutables entre parentesis)");
        jLabel22.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(246, 246, 246));
        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel23.setText("MatLab: (MATLAB\\R...\\bin\\matlab.exe)");
        jLabel23.setOpaque(true);

        jTextField7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField7.setAlignmentX(2.0F);
        jTextField7.setAlignmentY(2.0F);
        jTextField7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel24MousePressed(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel25MousePressed(evt);
            }
        });

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel26MousePressed(evt);
            }
        });

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel27MousePressed(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel28MousePressed(evt);
            }
        });

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel29MousePressed(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel30MousePressed(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel31MousePressed(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(246, 246, 246));
        jLabel32.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        jLabel32.setText("Color de la aplicación");
        jLabel32.setOpaque(true);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/color.png"))); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel33MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        onSearchSavedFolder();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        onAppendSavedFolder();
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        onAppendEditExecutable();
    }//GEN-LAST:event_jLabel5MousePressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        onSearchEditExecutable();
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        onSearchJavaFolder();
    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        onAppendJavaFolder();
    }//GEN-LAST:event_jLabel8MousePressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        onSearchPythonFolder();
    }//GEN-LAST:event_jLabel10MousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        onAppendPythonFolder();
    }//GEN-LAST:event_jLabel11MousePressed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        onAppendJavaScriptFolder();
    }//GEN-LAST:event_jLabel13MousePressed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed
        onSearchJavaScriptFolder();
    }//GEN-LAST:event_jLabel15MousePressed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
        onSearchCPlusPlusFolder();
    }//GEN-LAST:event_jLabel17MousePressed

    private void jLabel18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MousePressed
        onAppendCPlusPlusFolder();
    }//GEN-LAST:event_jLabel18MousePressed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jLabel24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MousePressed
        onSearchMatLabFolder();
    }//GEN-LAST:event_jLabel24MousePressed

    private void jLabel25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MousePressed
        onAppendMatLabFolder();
    }//GEN-LAST:event_jLabel25MousePressed

    private void jLabel26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26MousePressed

    private void jLabel27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MousePressed

    private void jLabel28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MousePressed

    private void jLabel29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel29MousePressed

    private void jLabel30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel30MousePressed

    private void jLabel31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MousePressed

    private void jLabel33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel33MousePressed

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        onChangeGeneralColor();
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        onChangeJavaColor();
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        onChangePythonColor();
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        onChangeJavaScriptColor();
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        onChangeCColor();
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        onChangeCPlusPlusColor();
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        onChangeMatLabColor();
    }//GEN-LAST:event_jLabel30MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

}
