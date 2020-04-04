package fasttask.view.windows;

import fasttask.view.windows.Principal;
import fasttask.view.components.Frame;
import fasttask.view.components.ParameterElement;
import fasttask.controller.code.CodeController;
import fasttask.controller.code.CommandLine;
import fasttask.controller.view.ViewController;
import fasttask.data.system.Directions;
import fasttask.data.system.FileAccess;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class RunClass extends javax.swing.JPanel implements CommandLine{

    ViewController viewController;
    public Frame frame;
    Principal principal;
    CodeController runner;

    public String direction;
    String name;
    String description;
    String languaje;
    String[] parameters;
    Color color;
    int inputStart;
    BufferedWriter writer;
    Thread confButtonThread;

    public RunClass(Principal principal, Frame frame, ViewController viewController, String dir, String name, String description, String languaje, String[] parameters) {
        initComponents();
        String params = Arrays.toString(parameters);
        jLabel2.setText(name + " (" + params.substring(1, params.length() - 1) + ")");
        jLabel4.setText(languaje);
        jTextArea2.setText(description);

        this.principal = principal;
        this.frame = frame;
        this.viewController = viewController;
        direction = dir;
        this.name = name;
        this.description = description;
        this.languaje = languaje;
        this.parameters = parameters;
        
        color = CodeController.getController(dir).color();
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(color, 4));
        jPanel7.setBackground(color);
        ViewController.customizeButton(jLabel1, color);
        ViewController.customizeButton(jLabel5, color);
        ViewController.customizeButton(jLabel8, color);
        ViewController.customizeButton(jLabel6, color);
        ViewController.customizeButton(jLabel7, color);
        jLabel2.setForeground(color);
        jTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(color, 1));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(color, 1));
        setParametersList();
        
    }

    // Asignar lista de funciones
    public void setParametersList() {
        jPanel4.removeAll();
        if (parameters.length == 0) {
            jPanel1.setVisible(false);
        } else {
            jPanel1.setVisible(true);
            for (int i = 0; i < parameters.length - 1; i++) {

                ParameterElement parameterElement = new ParameterElement(parameters[i], color);
                jPanel4.add(parameterElement);

                // Asignar salto de foco a los elementos
                parameterElement.getField().addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            parameterElement.getField().transferFocus();
                        }
                    }
                });

            }

            ParameterElement ParameterElement = new ParameterElement(parameters[parameters.length - 1], color);
            jPanel4.add(ParameterElement);

            // Asignar ejecución al ultimo elemento
            ParameterElement.getField().addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        jLabel1MousePressed(null);
                    }
                }
            });

        }
    }
    
    @Override
    public void write(String text, boolean type) {
        if (type == CommandLine.DEFAULT) {
            jTextArea1.setBackground(new java.awt.Color(240, 255, 240));
            jTextArea1.setForeground(Color.BLACK);
        } else {
            jTextArea1.setForeground(Color.RED);
            jTextArea1.setBackground(new java.awt.Color(255, 240, 240));
        }
        jTextArea1.setText(jTextArea1.getText() + text);
        inputStart = jTextArea1.getText().length();
        jTextArea1.setCaretPosition(inputStart);
    }
    
    
    @Override
    public void read(BufferedWriter writer) {
        this.writer = writer;
    }
    
    @Override
    public void onRun() {
                
        // Bloquear color run
        jLabel1.setIcon(ViewController.colorLightImage(jLabel1.getIcon(), color));
        jLabel1.removeMouseListener(jLabel1.getMouseListeners()[jLabel1.getMouseListeners().length - 1]);
        
    }

    @Override
    public void onFinished() {
        
        // Desbloquear color run
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/play.png")));
        ViewController.customizeButton(jLabel1, color);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                formAncestorRemoved(evt);
            }
        });

        jPanel1.setOpaque(false);

        jScrollPane3.setBorder(null);
        jScrollPane3.setOpaque(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.X_AXIS));
        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(251, 255, 251));
        jTextArea1.setColumns(1);
        jTextArea1.setRows(1);
        jTextArea1.setTabSize(1);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel6.setOpaque(false);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/stop.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/play.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/editar.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });
        jLabel6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel6KeyPressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/borrar.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/clean.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7formMouseExited(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5formMouseExited(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(246, 246, 246));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setText("N ()");
        jLabel2.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(246, 246, 246));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel4.setText("L");
        jLabel4.setOpaque(true);

        jScrollPane2.setBorder(null);

        jTextArea2.setColumns(1);
        jTextArea2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextArea2.setRows(1);
        jTextArea2.setTabSize(1);
        jTextArea2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        jTextArea2.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel5formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5formMouseClicked

    }//GEN-LAST:event_jPanel5formMouseClicked

    private void jPanel5formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5formMouseEntered

    }//GEN-LAST:event_jPanel5formMouseEntered

    private void jPanel5formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5formMouseExited

    }//GEN-LAST:event_jPanel5formMouseExited

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        
        if (runner == null || runner.getState() == CodeController.STOPED) {
            
            jTextArea1.setText("");
            Component[] component = jPanel4.getComponents();
            String[] parameters = new String[component.length];

            // Obtener parametros
            for (int i = 0; i < parameters.length; i++) {
                if (((ParameterElement) component[i]).isEmpty()) {
                    return;
                }
                parameters[i] = ((ParameterElement) component[i]).getValue();
            }

            // Ejecutar función
            runner = CodeController.getController(direction);                             // Obtener runner del lenguaje detectado
            runner.run(parameters, this);      
            jTextArea1.requestFocus();
            
        }

    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel6KeyPressed


    }//GEN-LAST:event_jLabel6KeyPressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed

        int dialogResult = JOptionPane.showConfirmDialog(this, "¿Quieres borrar la clase " + name + "?", "Warning", JOptionPane.YES_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {

            viewController.removeClass(direction);
            viewController.removeActivedClass(this);

        }

    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed

        // Abirir archivo con sublime text 3
        try {
            String editorFile = Directions.getEditorFile();
            String command = "pushd " + FileAccess.getFolder(editorFile) + " "
                    + "&& " + FileAccess.getName(editorFile) + " \"" + direction + "\"";
            System.out.println(command);
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", command);
            processBuilder.start();
        } catch (IOException ex) {
        }

    }//GEN-LAST:event_jLabel6MousePressed

    private void jPanel7formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7formMouseClicked

    private void jPanel7formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7formMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7formMouseEntered

    private void jPanel7formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7formMouseExited

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        if (runner != null) {
            runner.stop(this);
        }
        
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
       
        jTextArea1.setText("");
        jTextArea1.setBackground(Color.white);
        
    }//GEN-LAST:event_jLabel8MousePressed

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                System.out.println("text: " + jTextArea1.getText().substring(inputStart));
                writer.write(jTextArea1.getText().substring(inputStart) + "\n");
                writer.flush();
            } catch (IOException | NullPointerException ex) {
            }
        }
        
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        jLabel5MousePressed(null);
    }//GEN-LAST:event_formAncestorRemoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables


}
