/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.AuthController;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Torrejón
 */
public class Login extends javax.swing.JFrame {

    public static String token;

    AuthController autenticar = new AuthController();

    DashboardAdmin dAdmin = new DashboardAdmin();
    DashboardUser dUser = new DashboardUser();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        chAdmin = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 800));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 500));
        jPanel1.setSize(new java.awt.Dimension(600, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KeBook");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 400, 80));

        jLabel2.setText("Correo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 80, -1));
        jPanel1.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 180, -1));

        jLabel4.setText("Contraseña");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 110, -1));
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 180, -1));

        chAdmin.setText("Administrador");
        chAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chAdminActionPerformed(evt);
            }
        });
        jPanel1.add(chAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jButton1.setText("Iniciar sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 140, -1));

        jButton2.setText("Nuevo usuario");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String mail = txtMail.getText();

        String password = new String(txtPass.getPassword());

        String urlAdmin = "http://192.168.2.108:8080/login/admin/" + mail + "/" + password;

        String url = "http://192.168.2.108:8080/login/" + mail + "/" + password;

        if (mail.isEmpty() || password.isEmpty()) {
            
            int salir = JOptionPane.showConfirmDialog(this, "Faltan datos por insertar, quiere volver ha intentarlo ?", "Advertencia", JOptionPane.YES_NO_OPTION);
                        if (salir == JOptionPane.NO_OPTION) {
                            System.exit(0);                       }

        } else {
            if (chAdmin.isSelected()) {
                try {
                    token = autenticar.userLogin(urlAdmin);
                    if ("ERROR".equals(token)) {
                        //System.out.println(token); 
                        int salir = JOptionPane.showConfirmDialog(this, "Usuario y/o contraseña incorrectos, quiere volver ha intentarlo ?", "Advertencia", JOptionPane.YES_NO_OPTION);
                        if (salir == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }
                    } else {

                        dAdmin.setVisible(true);
                        this.setVisible(false);
                    }
                } catch (IOException | InterruptedException ex) {
                    ex.getMessage();
                }
            } else {
                try {
                    token = autenticar.userLogin(url);
                    if ("ERROR".equals(token)) {
                        int salir = JOptionPane.showConfirmDialog(this, "Usuario y/o contraseña incorrectos, quiere volver ha intentarlo ?", "Advertencia", JOptionPane.YES_NO_OPTION);
                        if (salir == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }

                    } else {
                        dUser.setVisible(true);
                        this.setVisible(false);
                    }
                } catch (IOException | InterruptedException ex) {
                    ex.getMessage();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chAdminActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMail;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
