/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.LibrosController;
import ioc.dam.torrejon.controladores.ResenaController;
import ioc.dam.torrejon.controladores.ReservasController;
import ioc.dam.torrejon.controladores.UsuariosController;
import ioc.dam.torrejon.controladores.Utils;
import ioc.dam.torrejon.modelos.Libro;
import ioc.dam.torrejon.modelos.Resena;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Torrejón
 */
public class ResenasUsuario extends javax.swing.JInternalFrame {

    UsuariosController usuarios = new UsuariosController();
    ReservasController reserva = new ReservasController();
    LibrosController libros = new LibrosController();
    ResenaController resenas = new ResenaController();
    Usuario usuario = new Usuario();
    Libro libro = new Libro();
    JSONObject perfil = new JSONObject();
    Utils util = new Utils();
    Resena resena = new Resena();

    int fila, idUsuario, code;
    Long id;
    String titulo, idUser, isbn, correo, rese;

    /**
     * Creates new form Devoluciones
     */
    public ResenasUsuario() {
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
        lblUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResena = new javax.swing.JTextArea();
        bResena = new javax.swing.JButton();
        bUsuario = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tReservas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bReservas = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Reseñas");

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Añadir reseñas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Id :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 70, 20));

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 240, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Libro :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 50, 20));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 130, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Añadir reseña");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 350, 40));

        txtResena.setColumns(20);
        txtResena.setRows(5);
        jScrollPane2.setViewportView(txtResena);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 290, 150));

        bResena.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bResena.setText("subir reseña");
        bResena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResenaActionPerformed(evt);
            }
        });
        jPanel2.add(bResena, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, 140, 30));

        bUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bUsuario.setText("Usuario");
        bUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(bUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 300, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Correo :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 640));

        tReservas.setModel(new javax.swing.table.DefaultTableModel(
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
        tReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tReservas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 630, 290));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Información de reservas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 580, 50));

        bReservas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bReservas.setText("Mis reservas");
        bReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReservasActionPerformed(evt);
            }
        });
        jPanel1.add(bReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tReservasMouseClicked
        try {
            fila = tReservas.rowAtPoint(evt.getPoint());
            isbn = String.valueOf(tReservas.getValueAt(fila, 2));
            lblTitulo.setText(libros.ObtenerLibroIsbn(isbn).getTitulo());
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_tReservasMouseClicked

    private void bUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUsuarioActionPerformed
        try {
            perfil = util.DatosUsuario(Login.token);

            id = perfil.getLong("jti");
            idUser = String.valueOf(id);
            lblUsuario.setText(perfil.getString("sub"));
            lblId.setText(idUser);
        } catch (JSONException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bUsuarioActionPerformed

    private void bResenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResenaActionPerformed

        correo = lblUsuario.getText();
        titulo = lblTitulo.getText();
        rese = txtResena.getText();

        if (correo.isEmpty() || titulo.isEmpty()||rese.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan datos para enviar la reseña");
        } else {

            try {

                libro = libros.ObtenerLibroTitulo(titulo);
                usuario = usuarios.obtenerUsuarioPorCorreo(correo);

                resena.setUsuario(usuario);
                resena.setLibro(libro);
                resena.setResena(rese);
                
                resenas.guardarResena(resena);
                
                
            } catch (IOException | InterruptedException ex) {
                ex.getMessage();
            }
        }
    }//GEN-LAST:event_bResenaActionPerformed

    private void bReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReservasActionPerformed
        try {
            correo = lblUsuario.getText();
            titulo = lblTitulo.getText();
            if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan datos del usuario para enviar la reseña");
        } else {
            
            usuario = usuarios.obtenerUsuarioPorCorreo(correo);
            id = usuario.getId();
            
            idUsuario = Math.toIntExact(id);
            
            reserva.ObtenerReservaUsuario(tReservas, idUsuario);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
        
        
        
    }//GEN-LAST:event_bReservasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bResena;
    private javax.swing.JButton bReservas;
    private javax.swing.JButton bUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tReservas;
    private javax.swing.JTextArea txtResena;
    // End of variables declaration//GEN-END:variables
}
