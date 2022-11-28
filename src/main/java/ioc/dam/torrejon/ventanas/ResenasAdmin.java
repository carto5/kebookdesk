/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.ResenaController;
import ioc.dam.torrejon.controladores.Utils;
import ioc.dam.torrejon.modelos.Resena;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class ResenasAdmin extends javax.swing.JInternalFrame {

    ResenaController resenas = new ResenaController();
    DefaultTableModel clean = new DefaultTableModel();

    Object[] columBookUser = new Object[]{"Id_usuario", "Isbn_libro", "Reseña"};
    DefaultTableModel modelBookUser = new DefaultTableModel(columBookUser, 0);

    Object[] columId = new Object[]{"Id_reseña", "Reseña"};
    DefaultTableModel modelId = new DefaultTableModel(columId, 0);

    Object[] columUser = new Object[]{"Correo", "Reseña"};
    DefaultTableModel modelUser = new DefaultTableModel(columUser, 0);

    private final Object[] columnas = new Object[]{"Id", "Id_usuario", "Isbn_libro", "Reseña"};
    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    List<Resena> resena;
    int id, code;
    String isbn;

    /**
     * Creates new form ResenasAdmin
     */
    public ResenasAdmin() {
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        bBuscarResena = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bResenaUsuario = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtidUsuarios = new javax.swing.JTextField();
        bLibroUsuario = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        bEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bResenas = new javax.swing.JButton();

        setMaximizable(true);
        setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestionar reseñas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar o eliminar reseñas por id");
        jLabel2.setAutoscrolls(true);
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 50, 300, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("id :");
        jLabel3.setAutoscrolls(true);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 40, 20));
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 70, -1));

        bBuscarResena.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bBuscarResena.setText("Buscar");
        bBuscarResena.setAutoscrolls(true);
        bBuscarResena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarResenaActionPerformed(evt);
            }
        });
        jPanel2.add(bBuscarResena, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jSeparator1.setAutoscrolls(true);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 300, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Id de usuario:");
        jLabel4.setAutoscrolls(true);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 90, 20));
        jPanel2.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Buscar reseñas por usuario");
        jLabel5.setAutoscrolls(true);
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 300, 20));

        bResenaUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bResenaUsuario.setText("Buscar");
        bResenaUsuario.setAutoscrolls(true);
        bResenaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResenaUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(bResenaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Buscar reseñas de libro por usuario");
        jLabel6.setAutoscrolls(true);
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 330, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Id de usuario:");
        jLabel7.setAutoscrolls(true);
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 90, 20));
        jPanel2.add(txtidUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 100, -1));

        bLibroUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bLibroUsuario.setText("Buscar");
        bLibroUsuario.setAutoscrolls(true);
        bLibroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLibroUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(bLibroUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, -1, -1));

        jSeparator2.setAutoscrolls(true);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 300, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Isbn libro :");
        jLabel8.setAutoscrolls(true);
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 90, 20));
        jPanel2.add(txtIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 100, -1));

        bEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bEliminar.setText("Eliminar");
        bEliminar.setAutoscrolls(true);
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 580));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reseñas");
        jLabel1.setAutoscrolls(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 400, 60));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 120, 570, 270));

        bResenas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bResenas.setText("Reseñas");
        bResenas.setAutoscrolls(true);
        bResenas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResenasActionPerformed(evt);
            }
        });
        jPanel1.add(bResenas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, 200, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBuscarResenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarResenaActionPerformed
        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }

        id = Integer.parseInt(txtId.getText());

        try {
            resena = resenas.obtenerResenaPorId(id);
            if (resena != null) {
                resena.stream().forEach(item -> {
                    modelId.addRow(new Object[]{item.getId(), item.getResena()});
                });

                jTable1.setModel(modelId);
            } else {
                Utils.OptionPaneInfo("Este usuario no tiene reseñas.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bBuscarResenaActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        id = Integer.parseInt(txtId.getText());

        try {
            code = resenas.eliminarResena(id);
            if(code!=200){
                Utils.OptionPaneInfo("La reseña no ha podido ser eliminada.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bEliminarActionPerformed

    private void bResenaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResenaUsuarioActionPerformed

        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }

        id = Integer.parseInt(txtIdUsuario.getText());

        try {
            resena = resenas.obtenerResenasUsuario(id);
            if (resena != null) {
                resena.stream().forEach(item -> {
                    modelId.addRow(new Object[]{item.getUsuario().getCorreo(), item.getResena()});
                });

                jTable1.setModel(modelId);
            } else {
Utils.OptionPaneInfo("Este usuario no tiene reseñas.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bResenaUsuarioActionPerformed

    private void bLibroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLibroUsuarioActionPerformed
        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }

        isbn = txtIsbn.getText();
        id = Integer.parseInt(txtidUsuarios.getText());

        try {
            resena = resenas.obtenerResenasDeLibroPorUsuario(isbn, id);
            if (resena != null) {
                resena.stream().forEach(item -> {
                    modelBookUser.addRow(new Object[]{item.getUsuario().getId(), item.getLibro().getIsbn(), item.getResena()});
                });

                jTable1.setModel(modelBookUser);
            } else {
                Utils.OptionPaneInfo("Este libro no tiene reseñas de este usuario.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bLibroUsuarioActionPerformed

    private void bResenasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResenasActionPerformed
        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }

        try {
            resena = resenas.obtenerResenas();
            if (resena != null) {
                resena.stream().forEach(item -> {
                    modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getResena()});
                });
                jTable1.setModel(modelo);
            } else {
                Utils.OptionPaneInfo("Error al consultar las reseñas.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bResenasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBuscarResena;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bLibroUsuario;
    private javax.swing.JButton bResenaUsuario;
    private javax.swing.JButton bResenas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtidUsuarios;
    // End of variables declaration//GEN-END:variables
}
