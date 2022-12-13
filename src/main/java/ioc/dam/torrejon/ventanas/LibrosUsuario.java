/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.CorreosController;
import ioc.dam.torrejon.controladores.LibrosController;
import ioc.dam.torrejon.controladores.ResenaController;
import ioc.dam.torrejon.controladores.ReservasController;
import ioc.dam.torrejon.controladores.Utils;
import ioc.dam.torrejon.modelos.Libro;
import ioc.dam.torrejon.modelos.Resena;
import ioc.dam.torrejon.modelos.Reserva;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.table.DefaultTableModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Torrejón
 */
public class LibrosUsuario extends javax.swing.JInternalFrame {

    private final Object[] columnas = new Object[]{"Isbn", "Título", "Autor", "Sinopsis", "Genero", "Disponible"};
    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    private final Object[] columnaResena = new Object[]{"Libro", "Reseña"};
    private final DefaultTableModel modeloResena = new DefaultTableModel(columnaResena, 0);

    JSONObject perfil = new JSONObject();
    Utils util = new Utils();
    ReservasController guardarReserva = new ReservasController();
    ResenaController resenas = new ResenaController();
    CorreosController correoReserva = new CorreosController();

    Usuario usuario = new Usuario();
    Libro libro = new Libro();
    List<Libro> books;
    List<Resena> resena;
    Reserva reserva = new Reserva();
    LibrosController libros = new LibrosController();
    DefaultTableModel clean = new DefaultTableModel();
    ZoneId zonaHoraria = ZoneId.systemDefault();

    String autor, genero, isbn, titulo, correo;
    String mensaje = "Para realizar una busqueda es necesario rellenar alguno de los campos, desea continuar?";
    String mensaje2 = "busquedas disponibles por : Isbn, autor, género, disponibilidad o isbn + disponibilidad, desea continuar?";
    Long id;
    int code;
    int dias = 10;
    Date fecha;
    LocalDate localDate;

    /**
     * Creates new form LibrosUsuario
     */
    public LibrosUsuario() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tListarLibros = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        chDisponible = new javax.swing.JCheckBox();
        bBuscar = new javax.swing.JButton();
        txtIsbn = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        bReseva = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblIsbn = new javax.swing.JLabel();
        bListarLibros = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Libros");
        setPreferredSize(new java.awt.Dimension(1190, 640));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Libros");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 460, 70));

        tListarLibros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tListarLibros.setModel(new javax.swing.table.DefaultTableModel(
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
        tListarLibros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tListarLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tListarLibrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tListarLibros);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 610, 340));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar libro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Isbn");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 80, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Autor");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 70, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Genero");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 70, -1));

        chDisponible.setBackground(new java.awt.Color(51, 204, 255));
        chDisponible.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chDisponible.setText("Disponible");
        chDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chDisponibleActionPerformed(evt);
            }
        });
        jPanel2.add(chDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        bBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bBuscar.setText("Buscar");
        bBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(bBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 170, -1));
        jPanel2.add(txtIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 150, -1));
        jPanel2.add(txtAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 150, -1));
        jPanel2.add(txtGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 150, -1));

        bReseva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bReseva.setText("Reservar");
        bReseva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResevaActionPerformed(evt);
            }
        });
        jPanel2.add(bReseva, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 170, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Título");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 70, -1));
        jPanel2.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 150, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Informacion del libro a reservar :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 260, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 380, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Isbn :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 70, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Título :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 70, 20));
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 250, 20));
        jPanel2.add(lblIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 250, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 600));

        bListarLibros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bListarLibros.setText("Listar libros");
        bListarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bListarLibrosActionPerformed(evt);
            }
        });
        jPanel1.add(bListarLibros, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 540, 190, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Reseñas del libro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 540, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bListarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bListarLibrosActionPerformed
        clean = (DefaultTableModel) tListarLibros.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            books = libros.ListarLibros();
            if (books != null) {
                books.stream().forEach(item -> {
                    modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
                });

                tListarLibros.setModel(modelo);
            } else {
                Utils.OptionPaneInfo("Error al cargar lista de libros", this);
            }

        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        } catch (NoSuchAlgorithmException |KeyManagementException ex) {
            ex.getMessage();
        }

    }//GEN-LAST:event_bListarLibrosActionPerformed

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed

        autor = txtAutor.getText();
        genero = txtGenero.getText();
        isbn = txtIsbn.getText();
        titulo = txtTitulo.getText();

        clean = (DefaultTableModel) tListarLibros.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            if (autor.isEmpty() && genero.isEmpty() && titulo.isEmpty() && isbn.isEmpty() && !chDisponible.isSelected()) {
                Utils.OptionPane(mensaje, this);
            } else if (!autor.isEmpty() && genero.isEmpty() && titulo.isEmpty() && isbn.isEmpty() && !chDisponible.isSelected()) {

                books = libros.ObtenerLibroEscritor(autor);

                if (books != null) {
                    books.stream().forEach(item -> {
                        modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
                    });

                    tListarLibros.setModel(modelo);
                } else {
                    Utils.OptionPaneInfo("No hay libros de este escritor", this);
                }
            } else if (!genero.isEmpty() && autor.isEmpty() && titulo.isEmpty() && isbn.isEmpty() && !chDisponible.isSelected()) {

                books = libros.ObtenerLibroGenero(genero);
                if (books != null) {
                    books.stream().forEach(item -> {
                        modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
                    });

                    tListarLibros.setModel(modelo);
                } else {
                    Utils.OptionPaneInfo("No hay libros de este genero", this);
                }
            } else if (isbn.isEmpty() && titulo.isEmpty() && genero.isEmpty() && autor.isEmpty() && chDisponible.isSelected()) {

                books = libros.ObtenerLibroDisponible();
                if (books != null) {
                    books.stream().forEach(item -> {
                        modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
                    });

                    tListarLibros.setModel(modelo);
                } else {
                    Utils.OptionPaneInfo("No hay libros disponibles", this);
                }
            } else if (!isbn.isEmpty() && genero.isEmpty() && titulo.isEmpty() && autor.isEmpty() && !chDisponible.isSelected()) {

                libro = libros.ObtenerLibroIsbn(isbn);
                if (libro != null) {
                    modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor().getNombre(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
                    tListarLibros.setModel(modelo);
                } else {
                    Utils.OptionPaneInfo("No hay libro con ese número de Isbn.", this);
                }
            } else if (autor.isEmpty() && !titulo.isEmpty() && genero.isEmpty() && isbn.isEmpty() && !chDisponible.isSelected()) {
                clean = (DefaultTableModel) tListarLibros.getModel();
                while (clean.getRowCount() > 0) {
                    clean.removeRow(0);
                }
                libro = libros.ObtenerLibroTitulo(titulo);
                if (libro != null) {
                    modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor().getNombre(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
                    tListarLibros.setModel(modelo);
                } else {
                    Utils.OptionPaneInfo("No hay libro con ese título.", this);
                }

            } else {
                Utils.OptionPane(mensaje2, this);
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        } catch (NoSuchAlgorithmException |KeyManagementException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bBuscarActionPerformed

    private void bResevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResevaActionPerformed

        try {
            localDate = LocalDate.now();
            fecha = Date.from(localDate.atStartOfDay(zonaHoraria).toInstant());
            perfil = util.DatosUsuario(Login.token);
            id = perfil.getLong("jti");
            correo = perfil.getString("sub");
            
            

            usuario.setId(id);
            libro.setIsbn(lblIsbn.getText());
            reserva.setUsuario(usuario);
            reserva.setLibro(libro);
            reserva.setFecha_inicio(fecha);
            reserva.setFecha_fin(util.SumarDias(fecha, dias));
            titulo = lblTitulo.getText();
            
            correoReserva.enviarMailReserva(correo, titulo);

            code = guardarReserva.guardarReserva(reserva);
            if (code != 200) {
                Utils.OptionPaneInfo("Falta seleccionr libro", this);
            }else{
                Utils.OptionPaneInfo("Reserva realizada", this);
            }

        } catch (JSONException | IOException | InterruptedException ex) {
            ex.getMessage();
        } catch (NoSuchAlgorithmException |KeyManagementException ex) {
            ex.getMessage();
        } catch (MessagingException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bResevaActionPerformed

    private void chDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chDisponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chDisponibleActionPerformed

    private void tListarLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tListarLibrosMouseClicked

        int fila = tListarLibros.rowAtPoint(evt.getPoint());
        lblIsbn.setText(String.valueOf(tListarLibros.getValueAt(fila, 0)));
        lblTitulo.setText(String.valueOf(tListarLibros.getValueAt(fila, 1)));
    }//GEN-LAST:event_tListarLibrosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clean = (DefaultTableModel) tListarLibros.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }

        isbn = lblIsbn.getText();

        try {
            resena = resenas.obtenerResenaPorLibro(isbn);
            if (resena != null) {
                resena.stream().forEach(item -> {
                    modeloResena.addRow(new Object[]{item.getLibro().getTitulo(), item.getResena()});
                });

                tListarLibros.setModel(modeloResena);

            }

        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        } catch (NoSuchAlgorithmException |KeyManagementException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton bListarLibros;
    private javax.swing.JButton bReseva;
    private javax.swing.JCheckBox chDisponible;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel lblIsbn;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tListarLibros;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
