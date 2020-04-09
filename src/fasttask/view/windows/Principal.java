package fasttask.view.windows;

import fasttask.controller.code.CodeController;
import fasttask.data.system.Directions;
import fasttask.view.components.ListElement;
import fasttask.controller.view.ViewController;
import fasttask.data.system.Constants;
import fasttask.data.system.FileAccess;
import fasttask.view.components.Frame;
import fasttask.view.components.Frameable;
import fasttask.view.components.Dialog;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public final class Principal extends javax.swing.JPanel implements Frameable {

    // Ventanas
    Frame frame;        // Ventana contenedora

    // Controladores
    public ViewController viewController;      // Controlador de la interfaz grafica

    public Principal() {

        viewController = new ViewController(this);

        initComponents();               // Iniciar componentes generados
        setFunctionList();              // Asignar lista de funciones
        setCustomization();             // Personalizar

        frame = new Frame(this);
        
        savedFileNotFoundMessage();     // Mensaje único de que la carpeta de codigos no esta configurada

    }

    // Personalizar ventana
    public void setCustomization() {

        // Personalizar botones
        ViewController.customizeButton(jLabel3, new javax.swing.ImageIcon(getClass().getResource(Constants.SEARCH_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel4, new javax.swing.ImageIcon(getClass().getResource(Constants.CLOSE_ALL_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel5, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), Constants.mainFrameColor());
        ViewController.customizeButton(jLabel6, new javax.swing.ImageIcon(getClass().getResource(Constants.ADD_ICON)), Constants.mainFrameColor());

        // Personalizar bordes
        jTextField1.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));
        jTextField2.setBorder(new javax.swing.border.LineBorder(Constants.mainFrameColor(), 1, true));

        // Personalizar tamaño
        setMinimumSize(getPreferredSize());
        
        if (frame != null) {
            frame.setCustomization();
        }

    }

    // Asignar lista de funciones con el filtro del campo
    public void setFunctionList() {
        setFunctionList(jTextField2.getText());
    }

    // Asignar lista de funciones con el filtro especificado
    public void setFunctionList(String filter) {

        // Remover lista anterior
        jPanel2.removeAll();

        // Dar formato de dos comas (nombre, lenguaje, descripción))
        filter = filter.replace(",", ", ");
        if (filter.split(",").length == 1) {
            filter += ", , ";
        } else if (filter.split(",").length == 2) {
            filter += ", ";
        }

        // asignar nueva lista
        String[] directions;
        try {
            directions = viewController.getDirectionsList(filter);
            for (int i = 0; i < directions.length; i++) {
                jPanel2.add(new ListElement(this, directions[i]));
            }
        } catch (IOException | NullPointerException ex) {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.ACCESS_ERROR);
            dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
            dialog.show();
            System.exit(-1);

        }

        // Asctualizar interfaz
        jPanel2.updateUI();

    }
    
    // Mensaje único de que la carpeta de codigos no esta configurada
    public void savedFileNotFoundMessage() {
        try {
            if (!new File(Directions.getSaveFolder()).exists()) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.ACCESS_ERROR);
                dialog.setDescription(Constants.SAVED_FILE_NOT_FOUND);
                dialog.show();
            }
        } catch (IOException ex) {
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog.setTitle(Constants.ACCESS_ERROR);
            dialog.setDescription(Constants.CONFIG_FILE_NO_FOUND);
            dialog.show();
            System.exit(-1);
        }
    }

    @Override
    public String title() {
        return Constants.APP_NAME;
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
        return Frameable.CENTER;
    }

    @Override
    public int typeClose() {
        return Frameable.CLOSE_PROGRAM;
    }

    @Override
    public void onConfuigurationClick() {

        // Si la configuración no esta abierta
        if (!ViewController.configurationActived) {

            // Abrir configuración
            new Configuration(this);
            ViewController.configurationActived = true;

        }

    }

    @Override
    public void onClose() {
        // No se necesita
    }

    @Override
    public void onGetFocus() {
        setFunctionList();
        setCustomization();
    }

    // Al presionar el boton de buscar codigo
    public void onSearchCode() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        if (fileChooser.getSelectedFile() != null) {
            jTextField1.setText(fileChooser.getSelectedFile().getAbsolutePath());
            jLabel5MousePressed(null);
        }
    }

    // Al presionar el boton agregar codigo en la dirección escrita
    public void onAppendCode() {
        if (new File(jTextField1.getText()).exists()) {
            try {

                // Si el nombre del archivo ya existe
                if (new File(jTextField1.getText()).exists()) {
                    Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                    dialog.setTitle(Constants.CREATION_ERROR);
                    dialog.setDescription(Constants.NAME_ALREADY_EXIST);
                    dialog.show();
                    jTextField1.setText("");
                    return;
                }

                viewController.addClass(jTextField1.getText());
                setFunctionList(jTextField2.getText().trim());
                jTextField1.setText("");

            } catch (IOException ex) {
                Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog.setTitle(Constants.CHANGE_ERROR);
                dialog.setDescription(Constants.DELETE_FILE_NOT_FOUND);
                dialog.show();
            }

        }
    }

    // Al presionar el boton cerrar todas ventanas de codigo activas
    public void onCloseCodeAllActivedWindows() {
        viewController.removeActivedClass();
    }

    // Al presionar el boton crear nuevo código
    public void onAddNewCode() {
        onAddNewCode("");
    }

    // Al presionar el boton crear nuevo código
    private void onAddNewCode(String text) {

        // Abrir ventana para ingresar nueva clase
        Dialog dialog = new Dialog(Dialog.NEW_CODE, Constants.mainFrameColor(), this);
        dialog.setTitle("Nuevo codigo");
        dialog.setDescription("Ingrese el nombre del archivo con extención");
        dialog.setCheckBoxText("Incluir plantilla");
        dialog.setInputText(text);
        dialog.show();

        // Si no se acepta la creación 
        if (!dialog.accept()) {
            return;
        }

        String direction;
        try {

            direction = Directions.getSaveFolder() + "\\" + dialog.getInputText();

            // Si el nombre del archivo ya existe
            if (new File(direction).exists()) {
                Dialog dialog2 = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog2.setTitle(Constants.CREATION_ERROR);
                dialog2.setDescription(Constants.NAME_ALREADY_EXIST);
                dialog2.show();
                onAddNewCode(dialog.getInputText());
                return;
            }

            // Si el lenguaje de la extensión no está soportado
            if (CodeController.getController(direction) == null) {
                Dialog dialog2 = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                dialog2.setTitle(Constants.CREATION_ERROR);
                dialog2.setDescription(Constants.LANGUAJE_NOT_SUPPORTED);
                dialog2.show();
                onAddNewCode(dialog.getInputText());
                return;
            }

            // Si se requiere platilla
            if (dialog.getCheckBoxValue()) {
                try {
                    FileAccess.copyFile(Directions.getTemplatesFolder() + "\\template." + FileAccess.getExtension(dialog.getInputText()), direction);
                    viewController.addActivedClass(new RunClass(this, CodeController.getController(direction)));
                } catch (IOException ex) {
                    Dialog dialog2 = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                    dialog2.setTitle(Constants.CREATION_ERROR);
                    dialog2.setDescription(Constants.TEMPLATE_COPY_NOT_FOUND);
                    dialog2.show();
                    onAddNewCode(dialog.getInputText());
                    return;
                }

                // Si no se requiere plantilla
            } else {
                try {
                    FileAccess.createFile(direction);
                    viewController.addActivedClass(new RunClass(this, CodeController.getController(direction)));
                } catch (IOException ex) {
                    Dialog dialog2 = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
                    dialog2.setTitle(Constants.CREATION_ERROR);
                    dialog2.setDescription(Constants.CREATE_FILE_NOT_FOUND);
                    dialog2.show();
                    onAddNewCode(dialog.getInputText());
                    return;
                }
            }

        } catch (IOException ex) {
            Dialog dialog2 = new Dialog(Dialog.NOTIFICATION, Constants.mainFrameColor(), this);
            dialog2.setTitle(Constants.ACCESS_ERROR);
            dialog2.setDescription(Constants.CONFIG_FILE_NO_FOUND);
            dialog2.show();
            onAddNewCode(dialog.getInputText());
        }

        // Actualizar lista
        setFunctionList();

    }

    // Al cambiar el texto del filtro
    public void onFiltredChanged() {
        setFunctionList(jTextField2.getText().trim());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(400, 600));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(246, 246, 246));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel1.setText("Ingresar código:");
        jLabel1.setOpaque(true);

        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextField1.setAlignmentX(2.0F);
        jTextField1.setAlignmentY(2.0F);
        jTextField1.setBorder(new javax.swing.border.LineBorder(new Color(44, 169, 36), 1, true));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(246, 246, 246));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel2.setText("Filtrar códigos: (Nombre, Lenguaje, Descripción)");
        jLabel2.setOpaque(true);

        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextField2.setAlignmentX(2.0F);
        jTextField2.setAlignmentY(2.0F);
        jTextField2.setBorder(new javax.swing.border.LineBorder(new Color(44, 169, 36), 1, true));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(44, 169, 36)));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/buscar.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/closeall.png"))); // NOI18N
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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/anadir.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        onFiltredChanged();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        onSearchCode();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        onCloseCodeAllActivedWindows();
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        onAppendCode();
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseEntered

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

    }//GEN-LAST:event_formComponentShown

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        onAddNewCode();
    }//GEN-LAST:event_jLabel6MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
