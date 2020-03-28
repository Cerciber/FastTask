package fasttask.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Frame extends javax.swing.JFrame {

    boolean state;
    int x;
    int y;

    // State (true: principal, false segundario)
    public Frame(JPanel content, boolean state) {
        setWindowsView();
        initComponents();

        if (state) {
            setSize(600, 400);
            setLocationRelativeTo(null);
        } else {

            setSize(300, 500);

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

    // Asignar vista de windows
    public void setWindowsView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/view/Images/cerrar.png"))); // NOI18N
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMousePressed(evt);
            }
        });
        panel.setLayout(new java.awt.CardLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/view/Images/Maximizar.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/view/Images/menos.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/view/Images/ajustes.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(240, 240, 240))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed


    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed

        if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
            getContentPane().setBackground(Color.WHITE);
        } else {

            if (state) {
                setSize(600, 400);
                setLocationRelativeTo(null);
            } else {

                setSize(300, 500);

                // Asignar posición aleatoria en pantalla
                Random random = new Random();
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension dimension = toolkit.getScreenSize();
                int x = random.nextInt(dimension.width - getWidth());
                int y = random.nextInt(dimension.height - getHeight());
                setLocation(x, y);

            }

            getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        }

    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        if (state) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else {
            setVisible(false);
            dispose();
        }
        

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed

        setState(Frame.ICONIFIED);

    }//GEN-LAST:event_jLabel5MousePressed

    private void panelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseMoved
        
        
        
    }//GEN-LAST:event_panelMouseMoved

    private void panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseEntered
        
        
        
    }//GEN-LAST:event_panelMouseEntered

    private void panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMousePressed
        
        x = evt.getX();
        y = evt.getY();
        
    }//GEN-LAST:event_panelMousePressed

    private void panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseDragged
        
        setLocation(getLocation().x + evt.getX() - x, getLocation().y + evt.getY() - y);
        
    }//GEN-LAST:event_panelMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
