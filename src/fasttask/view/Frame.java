package fasttask.view;

import fasttask.controller.view.ViewController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends javax.swing.JFrame {

    Principal principal;
    
    boolean state;
    int resizeOption;
    
    int x;
    int y;
    int width;
    int height;
    
    int xState;
    int yState;
    int widthState;
    int heightState;
    
    // State (true: principal, false segundario)
    public Frame(Principal principal, JPanel content, boolean state) {
        
        initComponents();
        this.principal = principal;
        
        if (state) {
            setSize(300, 500);
            setLocationRelativeTo(null);
        } else {

            setSize(300, 400);

            // Asignar posición aleatoria en pantalla
            Random random = new Random();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            int x = random.nextInt(dimension.width - getWidth());
            int y = random.nextInt(dimension.height - getHeight());
            setLocation(x, y);

        }

        getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        ((JPanel) getContentPane()).setOpaque(true);
        panel.add(content);
        this.state = state;
        setVisible(true);
    }

    public Frame(Principal principal, JPanel content, boolean state, String name, Color color){
        this(principal, content,state);
        jLabel2.setText(name);
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(color, 4));
        jLabel1.setIcon(ViewController.colorImage(jLabel1.getIcon(), color));
        jLabel4.setIcon(ViewController.colorImage(jLabel4.getIcon(), color));
        jLabel5.setIcon(ViewController.colorImage(jLabel5.getIcon(), color));
        jLabel6.setIcon(ViewController.colorImage(jLabel6.getIcon(), color));
        jLabel2.setBackground(color);
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 0, 4, color));
        setVisible(false);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/cerrar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelMouseMoved(evt);
            }
        });
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMousePressed(evt);
            }
        });
        panel.setLayout(new java.awt.CardLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/Maximizar.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/menos.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/ajustes.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FastTask");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 0, 4, new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed


    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        
        if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
            xState = this.getLocation().x;
            yState = this.getLocation().y;
            widthState = this.getWidth();
            heightState = this.getHeight();
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
            getContentPane().setBackground(Color.WHITE);
        } else {
            setSize(widthState, heightState);
            setLocation(xState, yState);
            getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        }

    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        if (state) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else {
            setVisible(false);
            dispose();
            if (jLabel2.getText().equals("Configuración")) {
                ViewController.confActived = false;
            }
            
        }


    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed

        setState(Frame.ICONIFIED);

    }//GEN-LAST:event_jLabel5MousePressed

    private void panelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseMoved

        if (evt.getX() <= 4) {
            setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
        } else if (evt.getX() >= getSize().width - 4) {
            setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
        } else if (evt.getY() <= 4) {
            setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
        } else if (evt.getY() >= panel.getSize().height - 4) {
            setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

    }//GEN-LAST:event_panelMouseMoved

    private void panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseEntered

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_panelMouseEntered

    private void panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMousePressed

        x = evt.getX();
        y = evt.getY();
        width = getSize().width;
        height = getSize().height;

        if (evt.getX() <= 4) {
            resizeOption = 1;
        } else if (evt.getX() >= getSize().width - 4) {
            resizeOption = 2;
        } else if (evt.getY() <= 4) {
            resizeOption = 3;
        } else if (evt.getY() >= panel.getSize().height - 4) {
            resizeOption = 4;
        } else {
            resizeOption = 0;
        }

    }//GEN-LAST:event_panelMousePressed

    private void panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseDragged

        switch (resizeOption) {
            case 0:
                setLocation(getLocation().x + evt.getX() - x, getLocation().y + evt.getY() - y);
                break;
            case 1:
                setBounds(getLocation().x + evt.getX() - x, getLocation().y, getSize().width - evt.getX() + x, getSize().height);
                break;
            case 2:
                setSize(width + evt.getX() - x, getSize().height);
                break;
            case 3:
                setBounds(getLocation().x, getLocation().y + evt.getY() - y, getSize().width, getSize().height - evt.getY() + y);
                break;
            case 4:
                setSize(getSize().width, height + evt.getY() - y);
                break;
        }

    }//GEN-LAST:event_panelMouseDragged

    private void panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseExited

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_panelMouseExited

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        
        if (!ViewController.confActived) {
            // Crear frame de la configuración
            Configuration configuration = new Configuration(principal);
            Frame frame = new Frame(principal, configuration, false, "Configuración", Color.BLACK);
            frame.setSize(320, 360);
            ViewController.confActived = true;
        }
        
        
        
    }//GEN-LAST:event_jLabel6MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
