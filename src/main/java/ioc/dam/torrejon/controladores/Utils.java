/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Usuario;
import ioc.dam.torrejon.ventanas.Login;
import java.awt.Component;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Torrejón
 */
public class Utils {
    
    Usuario usuario = new Usuario();
    
    /**
     * Método para mapear los datos en formato json.
     *
     * @param <T>
     * @param json String con los datos en formato json.
     * @param referencia TypeReference para referenciar al objeto.
     * @return los datos json mapeados a objetos.
     */
    
    public <T> T transObjeto(final String json, final TypeReference<T> referencia) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, referencia);

        } catch (IOException ex) {
            ex.getMessage();
        }
        return null;
    }
    
    /**
     * 
     * @param mensaje informativo que se añade al OptionPane.
     * @param componente en el que se abrira el OptionPane.
     */
    public static void OptionPane (String mensaje, Component componente){
        int salir = JOptionPane.showConfirmDialog(componente, mensaje, "Advertencia", JOptionPane.YES_NO_OPTION);
        if (salir == JOptionPane.NO_OPTION) {
                            System.exit(0);                       }
    }
    
    public static void OptionPaneInfo (String mensaje, Component componente){
        JOptionPane.showConfirmDialog(componente, mensaje, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public static void OptionPaneSalir (String mensaje, Component componente){
        int salir = JOptionPane.showConfirmDialog(componente, mensaje, "Advertencia", JOptionPane.YES_NO_OPTION);
        if (salir == JOptionPane.YES_OPTION) {
                            System.exit(0);                       }
    }
    
    /**
     * Método para desencriptar el token.
     * @param token String que contiene el token de la sesión.
     * @return un JSONObject.
     * @throws JSONException
     */
    public JSONObject DatosUsuario(String token) throws JSONException{
        
        String[] parte = Login.token.split("\\.");
        Base64.Decoder desencriptar = Base64.getUrlDecoder();
        String payload = new String(desencriptar.decode(parte[1]));
        
        JSONObject info = new JSONObject(payload);

        return info;
    }
    
    public Date SumarDias(Date fecha, int dias){
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        
        return calendar.getTime();
    }
    
}
