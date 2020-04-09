package fasttask.view.components;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import fasttask.controller.view.ViewController;
import fasttask.data.system.Constants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Frame extends javax.swing.JFrame {

    // Contenido del la ventana
    Frameable frameable;
    
    // Estado anterior al redimensionamiento
    int x;
    int y;
    int width;
    int height;

    // Tipo de redimensionamiento
    int resizeOption;

    // Estado anterior a la maximización
    int xState;
    int yState;
    int widthState;
    int heightState;

    public Frame(Frameable frameable) {

        this.frameable = frameable;

        initComponents();                   // Iniciar componentes generados
        panel.add(frameable.content());     // Asignar contenido
        setCustomization();                 // Personalizar
        setLocation();                      // Asignar ubicación
        setVisible(true);                   // Mostrar

    }

    // Personalizar ventana
    public void setCustomization() {

        // Dar transparencia 
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        ((JPanel) getContentPane()).setOpaque(true);

        // Asignar Icono y nombre
        ImageIcon icon = ViewController.colorImage(new javax.swing.ImageIcon(getClass().getResource(Constants.CONFIGURATION_ICON)), frameable.color());
        setIconImage(icon.getImage().getScaledInstance(30, 30, 0));
        setName(frameable.title());
        setTitle(frameable.title());
        jLabel2.setText(frameable.title());

        // Personalizar botones
        ViewController.customizeButton(jLabel1, new javax.swing.ImageIcon(getClass().getResource(Constants.CLOSE_ICON)), frameable.color());
        ViewController.customizeButton(jLabel4, new javax.swing.ImageIcon(getClass().getResource(Constants.MAXIMIZE_ICON)), frameable.color());
        ViewController.customizeButton(jLabel5, new javax.swing.ImageIcon(getClass().getResource(Constants.MINIMIZE_ICON)), frameable.color());
        ViewController.customizeButton(jLabel6, new javax.swing.ImageIcon(getClass().getResource(Constants.CONFIGURATION_ICON)), frameable.color());

        // Asignar textos de ayuda
        jLabel1.setToolTipText("Cerrar");
        jLabel4.setToolTipText("Maximizar");
        jLabel5.setToolTipText("Minimizar");
        jLabel6.setToolTipText("Configuración");
        
        // Personaliar bordes
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(frameable.color(), 4));
        jLabel2.setBackground(frameable.color());
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 0, 4, frameable.color()));

        // Personalizar tamaño
        setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        setMinimumSize(getPreferredSize());
        setSize(getPreferredSize());

    }

    // Asignar posición en pantalla
    public void setLocation() {

        if (frameable.typeStartLocation() == Frameable.CENTER) {

            // Central
            setLocationRelativeTo(null);

        } else {

            // Aleatoria
            Random random = new Random();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            int x = random.nextInt(dimension.width - getWidth());
            int y = random.nextInt(dimension.height - getHeight());
            setLocation(x, y);

        }

    }

    // Al presionar el boton de configuración
    public void onConfigurationClick() {
        frameable.onConfuigurationClick();
    }

    // Al presionar el boton de  maximizar ventana
    public void onMaximizeClick() {
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
    }

    // Al presionar el boton de  minimizar ventana
    public void onMinimizeClick() {
        setState(Frame.ICONIFIED);
    }

    // Al presionar el boton de cerrar ventana
    public void onCloseClick() {
        frameable.onClose();
        if (frameable.typeClose() == Frameable.CLOSE_PROGRAM) {
            System.exit(0);
        } else {
            setVisible(false);
            dispose();
        }
    }

    // Al ingresar el mouse en el frame
    public void onMouseEntered(MouseEvent evt) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    // Al sacar el mouse en el frame
    public void onMouseExited(MouseEvent evt) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    // Al mover el mouse en el frame
    public void onMouseMoved(MouseEvent evt) {
        if (evt.getX() <= 4 && evt.getY() <= 4) {
            setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
        } else if (evt.getX() <= 4 && evt.getY() >= panel.getSize().height - 4) {
            setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
        } else if (evt.getX() >= getSize().width - 4 && evt.getY() >= panel.getSize().height - 4) {
            setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        } else if (evt.getX() >= getSize().width - 4 && evt.getY() <= 4) {
            setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
        } else if (evt.getX() <= 4) {
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
    }

    // Al presionar el mouse en el frame
    public void onMousePressed(MouseEvent evt) {
        x = evt.getX();
        y = evt.getY();
        width = getSize().width;
        height = getSize().height;
        if (evt.getX() <= 4 && evt.getY() <= 4) {
            resizeOption = 1;
        } else if (evt.getX() <= 4 && evt.getY() >= panel.getSize().height - 4) {
            resizeOption = 2;
        } else if (evt.getX() >= getSize().width - 4 && evt.getY() >= panel.getSize().height - 4) {
            resizeOption = 3;
        } else if (evt.getX() >= getSize().width - 4 && evt.getY() <= 4) {
            resizeOption = 4;
        } else if (evt.getX() <= 4) {
            resizeOption = 5;
        } else if (evt.getX() >= getSize().width - 4) {
            resizeOption = 6;
        } else if (evt.getY() <= 4) {
            resizeOption = 7;
        } else if (evt.getY() >= panel.getSize().height - 4) {
            resizeOption = 8;
        } else {
            resizeOption = 0;
        }
    }

    // Al arrastrar el mouse en el frame
    public void onMouseDragged(MouseEvent evt) {
        switch (resizeOption) {
            case 0:
                    setLocation(getLocation().x + evt.getX() - x, getLocation().y + evt.getY() - y);
                break;
            case 1:
                setBounds(getLocation().x + evt.getX() - x, getLocation().y, getSize().width - evt.getX() + x, getSize().height);
                setBounds(getLocation().x, getLocation().y + evt.getY() - y, getSize().width, getSize().height - evt.getY() + y);
                break;
            case 2:
                setBounds(getLocation().x + evt.getX() - x, getLocation().y, getSize().width - evt.getX() + x, getSize().height);
                setSize(getSize().width, height + evt.getY() - y);
                break;
            case 3:
                setSize(width + evt.getX() - x, getSize().height);
                setSize(getSize().width, height + evt.getY() - y);
                break;
            case 4:
                setSize(width + evt.getX() - x, getSize().height);
                setBounds(getLocation().x, getLocation().y + evt.getY() - y, getSize().width, getSize().height - evt.getY() + y);
                break;
            case 5:
                setBounds(getLocation().x + evt.getX() - x, getLocation().y, getSize().width - evt.getX() + x, getSize().height);
                break;
            case 6:
                setSize(width + evt.getX() - x, getSize().height);
                break;
            case 7:
                setBounds(getLocation().x, getLocation().y + evt.getY() - y, getSize().width, getSize().height - evt.getY() + y);
                break;
            case 8:
                setSize(getSize().width, height + evt.getY() - y);
                break;
        }
    }

    // Al obtener foco el frame
    public void onGetFocus() {
        frameable.onGetFocus();
    }
    
    // Cerrar ventana
    public void close(){
        setVisible(false);
        dispose();
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
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fasttask/data/files/images/cerrar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
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
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel2MouseMoved(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
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
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed


    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        onMaximizeClick();
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        onCloseClick();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        onMinimizeClick();
    }//GEN-LAST:event_jLabel5MousePressed

    private void panelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseMoved
        onMouseMoved(evt);
    }//GEN-LAST:event_panelMouseMoved

    private void panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseEntered
        onMouseEntered(evt);
    }//GEN-LAST:event_panelMouseEntered

    private void panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMousePressed
        onMousePressed(evt);
    }//GEN-LAST:event_panelMousePressed

    private void panelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseDragged
        onMouseDragged(evt);
    }//GEN-LAST:event_panelMouseDragged

    private void panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseExited
        onMouseExited(evt);
    }//GEN-LAST:event_panelMouseExited

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        onConfigurationClick();
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        onMouseEntered(evt);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        onMouseExited(evt);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        onMousePressed(evt);
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        onMouseDragged(evt);
    }//GEN-LAST:event_jLabel2MouseDragged

    private void jLabel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseMoved
        onMouseMoved(evt);
    }//GEN-LAST:event_jLabel2MouseMoved

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged

    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        onGetFocus();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onCloseClick();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
