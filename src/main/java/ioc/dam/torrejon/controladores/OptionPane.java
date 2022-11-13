/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author carlostorrejongaragallo
 */
public class OptionPane {
    
    public static void OptionPane (String mensaje, Component componente){
        int salir = JOptionPane.showConfirmDialog(componente, mensaje, "Advertencia", JOptionPane.YES_NO_OPTION);
        if (salir == JOptionPane.NO_OPTION) {
                            System.exit(0);                       }
    }
    
}
