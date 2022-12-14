/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.EventosController;
import ioc.dam.torrejon.controladores.LibrosController;
import ioc.dam.torrejon.controladores.ReservasController;
import ioc.dam.torrejon.controladores.UsuariosController;
import ioc.dam.torrejon.controladores.Utils;
import ioc.dam.torrejon.modelos.Eventos;
import ioc.dam.torrejon.modelos.Libro;
import ioc.dam.torrejon.modelos.Reserva;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carlostorrejongaragallo
 */
public class EventosUsuario extends javax.swing.JInternalFrame {

    ReservasController reservas = new ReservasController();
    UsuariosController usuarios = new UsuariosController();
    LibrosController libros = new LibrosController();
    EventosController evento = new EventosController();
    Usuario usuario = new Usuario();
    Libro libro = new Libro();
    Eventos eventos = new Eventos();
    Utils util = new Utils();
    JSONObject perfil = new JSONObject();
    DefaultTableModel clean = new DefaultTableModel();
        List<Eventos> events;

    private final Object[] columnaReserva = new Object[]{"ID", "id_usuario", "Isbn_libro", "Fecha_inició", "Fecha_final", "Recogido", "Devuelto"};
    private final DefaultTableModel modeloReserva = new DefaultTableModel(columnaReserva, 0);
    private final Object[] columEvents = new Object[]{"Id", "Usuario", "Libro", "fecha evento", "Autorizado", "Administrador"};
    private final DefaultTableModel modelEvents = new DefaultTableModel(columEvents, 0);

    List<Reserva> reserva;
    String isbn, titulo;
    int idUsuario, fila, code;
    long id;
    Date fecha;

    public EventosUsuario() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tReservas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bMostrarEventos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fFecha = new javax.swing.JFormattedTextField();
        bEvento = new javax.swing.JButton();
        bReservas = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 540, 350));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eventos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 490, 80));

        bMostrarEventos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bMostrarEventos.setText("Mostrar eventos");
        bMostrarEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMostrarEventosActionPerformed(evt);
            }
        });
        jPanel1.add(bMostrarEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, -1, -1));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crear eventos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Libro :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 50, -1));
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 220, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Añadir fecha para el evento :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 210, -1));

        fFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jPanel2.add(fFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 190, -1));

        bEvento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bEvento.setText("Crear evento");
        bEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEventoActionPerformed(evt);
            }
        });
        jPanel2.add(bEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 170, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 570));

        bReservas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bReservas.setText("Mis reservas");
        bReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReservasActionPerformed(evt);
            }
        });
        jPanel1.add(bReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReservasActionPerformed
        try {
            perfil = util.DatosUsuario(Login.token);
            id = perfil.getLong("jti");
            perfil = util.DatosUsuario(Login.token);
//            correo = perfil.getString("sub");

//                usuario = usuarios.obtenerUsuarioPorCorreo(correo);
//                id = usuario.getId();
            idUsuario = Math.toIntExact(id);

            reserva = reservas.ObtenerReservaUsuario(idUsuario);
            if (reserva != null) {
                reserva.stream().forEach(item -> {
                    modeloReserva.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
                });
                tReservas.setModel(modeloReserva);
            } else {
                Utils.OptionPaneInfo("El usuario no tiene reservas.", this);
            }

        } catch (IOException | InterruptedException | NoSuchAlgorithmException | KeyManagementException ex) {
            ex.getMessage();
        } catch (JSONException ex) {
            Logger.getLogger(EventosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bReservasActionPerformed

    private void tReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tReservasMouseClicked
        try {
            fila = tReservas.rowAtPoint(evt.getPoint());
            isbn = String.valueOf(tReservas.getValueAt(fila, 2));
            lblTitulo.setText(libros.ObtenerLibroIsbn(isbn).getTitulo());

        } catch (IOException | InterruptedException | NoSuchAlgorithmException | KeyManagementException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_tReservasMouseClicked

    private void bEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEventoActionPerformed
        try {

            perfil = util.DatosUsuario(Login.token);
            id = perfil.getLong("jti");
            idUsuario = Math.toIntExact(id);

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            //idUsuario = Integer.parseInt(lblId.getText());
            titulo = lblTitulo.getText();

            libro = libros.ObtenerLibroTitulo(titulo);
            usuario = usuarios.obtenerUsuarioPorId(idUsuario);
            fecha = formato.parse(fFecha.getText());

            eventos.setFecha(fecha);
            eventos.setLibro(libro);
            eventos.setProponente(usuario);

            if (titulo.isEmpty() || (fecha == null)) {
                Utils.OptionPaneInfo("Faltan datos", this);
            } else {

                code = evento.guardarEvento(eventos);

                if (code != 200) {
                    Utils.OptionPaneInfo("Error al enviar el evento", this);
                } else {

                    Utils.OptionPaneInfo("Evento enviado", this);
                }
            }

        } catch (ParseException | IOException | InterruptedException | NoSuchAlgorithmException | KeyManagementException | JSONException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bEventoActionPerformed

    private void bMostrarEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMostrarEventosActionPerformed
        clean = (DefaultTableModel) tReservas.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            events = evento.listarEventos();
            if (events != null) {
                events.stream().forEach(item -> {
                    modelEvents.addRow(new Object[]{item.getId(), item.getProponente().getId(), item.getLibro().getIsbn(), item.getFecha(), item.isAproved()});
                });

                tReservas.setModel(modelEvents);
            } else {
                Utils.OptionPaneInfo("No hay eventos disponibles", this);
            }
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bMostrarEventosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEvento;
    private javax.swing.JButton bMostrarEventos;
    private javax.swing.JButton bReservas;
    private javax.swing.JFormattedTextField fFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tReservas;
    // End of variables declaration//GEN-END:variables
}
