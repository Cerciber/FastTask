package fasttask.view.components;

import fasttask.controller.view.ViewController;
import fasttask.data.system.Constants;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;

public final class Dialog extends javax.swing.JPanel {

    // Ventanas
    JDialog jDialog;        // Ventana contenedora
    JComponent parent;      // Componente invocador

    // Propiedades
    boolean result;         // Resultado del mensaje (Aceptado, cancelado)
    Color color;            // Color del dialogo
    int type;               // Tipo de dialogo

    // Estado anterior al redimensionamiento
    int x;
    int y;
    
    // Constantes
    public static final int NOTIFICATION = 1;           // Notificación
    public static final int NEW_CODE = 2;               // Nuevo codigo
    public static final int NOTIFICATION_OUTPUT = 3;    // Notificación con salida
    

    public Dialog(int type, Color color, JComponent parent) {

        this.type = type;
        this.parent = parent;
        this.color = color;

        this.jDialog = new JDialog();

        initComponents();           // Iniciar componentes generados
        jDialog.add(this);          // Asignar contenido
        setCustomization();         // Personalizar

    }

    // Personalizar ventana
    public void setCustomization() {

        // Personalizar botones
        ViewController.customizeButton(jLabel3, new javax.swing.ImageIcon(getClass().getResource(Constants.CLOSE_ICON)), color);
        ViewController.customizeButton(jLabel5, new javax.swing.ImageIcon(getClass().getResource(Constants.CHECK_ICON)), color);
        
        // Personalizar bordes
        setBorder(javax.swing.BorderFactory.createLineBorder(color, 4));
        jTextField1.setBorder(new javax.swing.border.LineBorder(color, 1, true));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(color, 1, true));


        // Dar transparencia 
        jDialog.setModal(true);

        // Dar bloqueo
        jDialog.setUndecorated(true);

        // Segun el tipo de dialogo especificado
        switch (type) {
            case NOTIFICATION:
                jTextField1.setVisible(false);
                jCheckBox1.setVisible(false);
                jScrollPane1.setVisible(false);
                jLabel3.setVisible(false);
                break;
            case NEW_CODE:
                jScrollPane1.setVisible(false);
                break;
            case NOTIFICATION_OUTPUT:
                jTextField1.setVisible(false);
                jCheckBox1.setVisible(false);
                break;
        }
        
        jDialog.pack();

    }
    
    public void setPreferedSize() {
        
        // Personalizar tamaño
        jDialog.setMinimumSize(jDialog.getPreferredSize());
        jDialog.setSize(jDialog.getPreferredSize());
        
    }
    
    // Mostar dialogo
    @Override
    public void show(){
        setPreferedSize();
        setLocation();
        jDialog.pack();
        jDialog.setVisible(true);   
        
    }

    // Asignar posición en pantalla
    public void setLocation() {
        jDialog.setLocationRelativeTo(parent);
    }

    // Asignar titulo
    public void setTitle(String title) {
        jLabel1.setText(title);
    }

    // Obtener titulo
    public String getTitle() {
        return jLabel1.getText();
    }
    
    // Asignar descpripción
    public void setDescription(String descpription) {
        jLabel4.setText(descpription);
    }

    // Obtener descpripción
    public String getDescription() {
        return jLabel4.getText();
    }
    
    // Asignar texto de entrada
    public void setInputText(String text) {
        jTextField1.setText(text);
    }

    // Obtener texto de entrada
    public String getInputText() {
        return jTextField1.getText();
    }
    
    // Asignar texto del error
    public void setOutputText(String text) {
        jTextArea1.setText(text);
    }

    // Obtener texto de entrada
    public String getOutputText() {
        return jTextArea1.getText();
    }
    
    // Asignar valor del checkbox
    public void setCheckBoxText(String text) {
        jCheckBox1.setText(text);
    }
    
    // Obtener valor del checkbox
    public String getCheckBoxText() {
        return jCheckBox1.getText();
    }

    // Asignar valor del checkbox
    public void setCheckBoxValue(boolean value) {
        jCheckBox1.setSelected(value);
    }
    
    // Obtener valor del checkbox
    public boolean getCheckBoxValue() {
        return jCheckBox1.isSelected();
    }

    // Verificar si se aceptó la creación
    public boolean accept() {
        return result;
    }

    // Al presionar el boton de aceptar
    public void onAccept() {
        result = true;
        jDialog.dispose();
    }

    // Al presionar el boton de cancelar
    public void onCancel() {
        result = false;
        jDialog.dispose();
    }

    // Al presionar el mouse en el dialogo
    public void onMousePressed(MouseEvent evt) {
        x = evt.getX();
        y = evt.getY();
    }
    
    // Al arrastrar el mouse en el dialogo
    public void onMouseDragged(MouseEvent evt) {
        jDialog.setLocation(jDialog.getLocation().x + evt.getX() - x, jDialog.getLocation().y + evt.getY() - y);
    }
    
    // Al presionar presionar una tecla en el campo
    public void onKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            onAccept();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new Color(44, 169, 36), 4));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(246, 246, 246));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Texto");
        jLabel1.setOpaque(true);

        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField1.setAlignmentX(2.0F);
        jTextField1.setAlignmentY(2.0F);
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField1MousePressed(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(246, 246, 246));
        jCheckBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/check.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/cerrar.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(246, 246, 246));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel4.setText("Texto");
        jLabel4.setOpaque(true);

        jTextArea1.setColumns(1);
        jTextArea1.setRows(1);
        jTextArea1.setTabSize(1);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        onAccept();
    }//GEN-LAST:event_jLabel5MousePressed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        onMousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        onMouseDragged(evt);
    }//GEN-LAST:event_formMouseDragged

    private void jTextField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MousePressed

    }//GEN-LAST:event_jTextField1MousePressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        onKeyPressed(evt);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        onCancel();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
