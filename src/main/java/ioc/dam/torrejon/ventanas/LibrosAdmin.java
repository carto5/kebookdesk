/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ioc.dam.torrejon.ventanas;

import ioc.dam.torrejon.controladores.EscritorController;
import ioc.dam.torrejon.controladores.LibrosController;
import ioc.dam.torrejon.controladores.Utils;
import ioc.dam.torrejon.modelos.Escritor;
import ioc.dam.torrejon.modelos.Libro;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlostorrejongaragallo
 */
public class LibrosAdmin extends javax.swing.JInternalFrame {

    EscritorController escritores = new EscritorController();

    LibrosController libros = new LibrosController();

    Libro libro = new Libro();

    Escritor escritor = new Escritor();

    DefaultTableModel clean = new DefaultTableModel();

    String isbn, titulo, autor, genero, sinopsis, idAuto;
    int idAutor, code;

    String book = "Faltan datos por rellenar, desea continuar?";
    String autorG = "Falta el nombre del autor, desea continuar?";
    String eliminar = "Se necesita id del autor para poder eliminarlo, desea continuar?";

    /**
     * Creates new form LibrosAdmin
     */
    public LibrosAdmin() {
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
        librosTable = new javax.swing.JTable();
        bLibros = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSinop = new javax.swing.JTextField();
        txtIsbn = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        bGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtIdAutor = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        bGAutor = new javax.swing.JButton();
        bAutores = new javax.swing.JButton();

        setClosable(true);
        setTitle("Libros\n");
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
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 530, 80));

        librosTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        librosTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(librosTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 620, 340));

        bLibros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bLibros.setText("Listar libros");
        bLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLibrosActionPerformed(evt);
            }
        });
        jPanel1.add(bLibros, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 550, 180, -1));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Añadir autores y libros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Isbn");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 70, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Título");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Autor");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Genero");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Sinopsis");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, -1));
        jPanel2.add(txtSinop, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 200, 100));
        jPanel2.add(txtIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 200, -1));
        jPanel2.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 200, -1));
        jPanel2.add(txtAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 200, -1));
        jPanel2.add(txtGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 200, -1));

        bGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bGuardar.setText("Guardar libro");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(bGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Id autor");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));
        jPanel2.add(txtIdAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 200, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 380, 10));

        bGAutor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bGAutor.setText("Guardar autor");
        bGAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGAutorActionPerformed(evt);
            }
        });
        jPanel2.add(bGAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 580));

        bAutores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bAutores.setText("Listar autores");
        bAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAutoresActionPerformed(evt);
            }
        });
        jPanel1.add(bAutores, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 550, 170, -1));

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

    private void bLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLibrosActionPerformed

        clean = (DefaultTableModel) librosTable.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }
        try {
            libros.ListarLibros(librosTable);
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }

    }//GEN-LAST:event_bLibrosActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed

        try {

            isbn = txtIsbn.getText();
            titulo = txtTitulo.getText();
            autor = txtAutor.getText();
            genero = txtGenero.getText();
            sinopsis = txtSinop.getText();
            idAuto = txtIdAutor.getText();
            idAutor = Integer.parseInt(idAuto);

            escritor.setId(idAutor);
            //escritor.setNombre(autor);

            if (isbn.isEmpty() || titulo.isEmpty() /*|| autor.isEmpty() */|| genero.isEmpty() || sinopsis.isEmpty() || idAuto.isEmpty()) {

                Utils.OptionPane(book, this);

            } else {

                libro.setIsbn(isbn);
                libro.setTitulo(titulo);
                libro.setAutor(escritor);
                libro.setGenero(genero);
                libro.setSinopsis(sinopsis);
                libro.setDisponible(true);

                code = libros.guardarLibro(libro);
                if(code!=200){
                    Utils.OptionPaneInfo("El libro no se ha podido guardar.", rootPane);
                }
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_bGuardarActionPerformed

    private void bGAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGAutorActionPerformed

        try {

            autor = txtAutor.getText();

            if (autor.isEmpty()) {
                Utils.OptionPane(autorG, this);
            } else {
                escritor.setNombre(autor);

                code = escritores.guardarEscritor(escritor);
                if(code!=200){
                    Utils.OptionPaneInfo("El escritor no se ha podido guardar. ", this);
                }
            }
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        }

    }//GEN-LAST:event_bGAutorActionPerformed

    private void bAutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAutoresActionPerformed

        clean = (DefaultTableModel) librosTable.getModel();
        while (clean.getRowCount() > 0) {
            clean.removeRow(0);
        }

        escritores.listarEscritores(librosTable);

    }//GEN-LAST:event_bAutoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAutores;
    private javax.swing.JButton bGAutor;
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton bLibros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable librosTable;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtIdAutor;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtSinop;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
