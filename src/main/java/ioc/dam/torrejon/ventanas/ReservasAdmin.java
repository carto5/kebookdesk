/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.ReservasController;
import ioc.dam.torrejon.controladores.UsuariosController;
import ioc.dam.torrejon.controladores.Utils;
import ioc.dam.torrejon.modelos.Reserva;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlostorrejongaragallo
 */
public class ReservasAdmin extends javax.swing.JInternalFrame {

    UsuariosController usuarios = new UsuariosController();
    ReservasController reservas = new ReservasController();

    private final Object[] columnas = new Object[]{"Id", "Nombre", "Correo", "Fecha de creación", "Administrador"};
    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    private final Object[] columnaReserva = new Object[]{"ID", "id_usuario", "Isbn_libro", "Fecha_inició", "Fecha_final", "Recogido", "Devuelto"};
    private final DefaultTableModel modeloReserva = new DefaultTableModel(columnaReserva, 0);

    List<Usuario> user;
    List<Reserva> reserva;
    DefaultTableModel clean = new DefaultTableModel();
    Utils util = new Utils();

    int idReserva, idUsuario, code;
    String isbn;

    /**
     * Creates new form PrestamosDevoluciones
     */
    public ReservasAdmin() {
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
        jLabel1 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdReserva = new javax.swing.JTextField();
        bBuscarId = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bConfirmDev = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        BuscarIsbn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        bConfirm = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bUsuarios = new javax.swing.JButton();
        bReservas = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Recogidas\n");

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recogidas / Devoluciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID usuario :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 100, -1));
        jPanel2.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 80, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ID reserva :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 90, -1));
        jPanel2.add(txtIdReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 80, -1));

        bBuscarId.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        bBuscarId.setText(" Buscar por id de usuario");
        bBuscarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarIdActionPerformed(evt);
            }
        });
        jPanel2.add(bBuscarId, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 180, 40));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 400, 20));

        bConfirmDev.setBackground(new java.awt.Color(0, 0, 0));
        bConfirmDev.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bConfirmDev.setForeground(new java.awt.Color(255, 255, 255));
        bConfirmDev.setText("Confirmar devolución");
        bConfirmDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmDevActionPerformed(evt);
            }
        });
        jPanel2.add(bConfirmDev, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 160, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Isbn :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 100, -1));
        jPanel2.add(txtIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 80, -1));

        BuscarIsbn.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        BuscarIsbn.setText("Buscar por isbn y id usuario");
        BuscarIsbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarIsbnActionPerformed(evt);
            }
        });
        jPanel2.add(BuscarIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 180, 40));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 400, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Buscar reserva");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 360, 30));

        bConfirm.setBackground(new java.awt.Color(0, 0, 0));
        bConfirm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bConfirm.setForeground(new java.awt.Color(255, 255, 255));
        bConfirm.setText("Confirmar recogida");
        bConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmActionPerformed(evt);
            }
        });
        jPanel2.add(bConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 630));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tabla de consultas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 460, 80));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 580, 300));

        bUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bUsuarios.setText("Usuarios");
        bUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUsuariosActionPerformed(evt);
            }
        });
        jPanel1.add(bUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 500, 130, -1));

        bReservas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bReservas.setText("Reservas");
        bReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReservasActionPerformed(evt);
            }
        });
        jPanel1.add(bReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 500, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUsuariosActionPerformed
        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            user = usuarios.listarUsuarios();
            user.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getNombre(), item.getCorreo(), item.getContrasena(), item.getFecha_creacion(), item.isAdmin()});
            });

            jTable1.setModel(modelo);
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bUsuariosActionPerformed

    private void bReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReservasActionPerformed

        try {
            clean = (DefaultTableModel) jTable1.getModel();
            while (clean.getRowCount() > 0) {
                clean.removeRow(0);
            }
            reserva = reservas.ListarReservas();
            if (reserva != null) {
                reserva.stream().forEach(item -> {
                    modeloReserva.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
                });
                jTable1.setModel(modeloReserva);
            } else {
                Utils.OptionPaneInfo("Error al listar las reservas.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }

    }//GEN-LAST:event_bReservasActionPerformed

    private void bBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarIdActionPerformed

        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            idUsuario = Integer.parseInt(txtIdUsuario.getText());
            reserva = reservas.ObtenerReservaUsuario(idUsuario);
            if (reserva != null) {
                reserva.stream().forEach(item -> {
                    modeloReserva.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
                });
                jTable1.setModel(modeloReserva);
            } else {
                Utils.OptionPaneInfo("El usuario no tiene reservas.", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bBuscarIdActionPerformed

    private void bConfirmDevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmDevActionPerformed
        try {
            idReserva = Integer.parseInt(txtIdReserva.getText());
            code = reservas.confirmarDevolucion(idReserva);
            if (code != 200) {
                Utils.OptionPaneInfo("No se ha podido confirmar la devolución", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bConfirmDevActionPerformed

    private void BuscarIsbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarIsbnActionPerformed

        clean = (DefaultTableModel) jTable1.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            isbn = txtIsbn.getText();
            idUsuario = Integer.parseInt(txtIdUsuario.getText());
            reserva = reservas.ObtenerReservadeLibroPorUsuario( isbn, idUsuario);
            if (reserva != null) {
                reserva.stream().forEach(item -> {
                    modeloReserva.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
                });
                jTable1.setModel(modeloReserva);
            }else{
                Utils.OptionPaneInfo("El usuario seleccionado no tiene reservas", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_BuscarIsbnActionPerformed

    private void bConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmActionPerformed

        try {
            idReserva = Integer.parseInt(txtIdReserva.getText());
            code = reservas.confirmarRecogida(idReserva);
            if (code != 200) {
                Utils.OptionPaneInfo("No se ha podido confirmar la recogida", this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarIsbn;
    private javax.swing.JButton bBuscarId;
    private javax.swing.JButton bConfirm;
    private javax.swing.JButton bConfirmDev;
    private javax.swing.JButton bReservas;
    private javax.swing.JButton bUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtIdReserva;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtIsbn;
    // End of variables declaration//GEN-END:variables
}
