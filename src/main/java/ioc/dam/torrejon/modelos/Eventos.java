/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.modelos;

import java.util.Date;

/**
 *
 * @author Carlos Torrejón
 */
public class Eventos {

    private int id;
    private Usuario proponente;
    private Libro libro;
    private Date fecha;
    private boolean isAproved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getProponente() {
        return proponente;
    }

    public void setProponente(Usuario proponente) {
        this.proponente = proponente;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isIsAproved() {
        return isAproved;
    }

    public void setIsAproved(boolean isAproved) {
        this.isAproved = isAproved;
    }

}
