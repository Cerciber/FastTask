
package fasttask.view.components;

import fasttask.controller.code.CodeController;
import fasttask.data.system.Constants;
import fasttask.view.windows.Principal;
import fasttask.view.windows.RunClass;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

public final class ListElement extends javax.swing.JPanel {

    // Ventanas
    Principal principal;            // Panel principal

    // Controladores
    CodeController codeController;   // Controlador del codigo
    
    public ListElement(Principal principal, String direction) {
        
        this.principal = principal;
        this.codeController = CodeController.getController(direction);
             
        initComponents();       // Iniciar componentes generados
        setInformation();       // Asignar información
        setCustomization();     // Personalizar
        
    }
    
    // Personalizar ventana
    public void setCustomization() {
    
        // Personalizar bordes
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.MAIN_FRAME_COLOR));
        
        // Personalizar colores texto
        jLabel4.setForeground(Constants.MAIN_FRAME_COLOR);
        
    }
    
    // Asignar información
    public void setInformation() {
        
        try {
            
            String params = Arrays.toString(codeController.parameters());
            jLabel1.setText(codeController.name() + " (" + params.substring(1, params.length() - 1) + ")");
            jLabel4.setText(codeController.languaje());
            jLabel3.setText(codeController.description());
            
        } catch (IOException ex) {
            
            Dialog dialog = new Dialog(Dialog.NOTIFICATION, Constants.MAIN_FRAME_COLOR, principal);
            dialog.setTitle(Constants.ACCESS_ERROR);
            dialog.setDescription(Constants.INFORMATION_NOT_FOUND);
            dialog.show();
            
        }
        
    }
    
    // Al entrar el mouse en el elemento
    public void onMouseEntered() {
        this.setBackground(Constants.MAIN_FRAME_COLOR);
    }
    
    // Al salir el mouse del elemento
    public void onMouseExited() {
        this.setBackground(Color.white);
    }
    
    // Al presionar el elemento
    public void onMouseClicked() {
        // Abrir nuevo compilador
        principal.viewController.addActivedClass(new RunClass(principal, codeController));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(44, 169, 36)));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(246, 246, 246));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel1.setText("Nombre (parametros)");
        jLabel1.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(246, 246, 246));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel4.setText("Lenguaje");
        jLabel4.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(246, 246, 246));
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel3.setText("Descripción");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        onMouseEntered();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        onMouseExited();
    }//GEN-LAST:event_formMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        onMouseClicked();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
