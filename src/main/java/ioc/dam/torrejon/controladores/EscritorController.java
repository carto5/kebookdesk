/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Escritor;
import ioc.dam.torrejon.modelos.Libro;
import ioc.dam.torrejon.modelos.Usuario;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JTable;

/**
 *
 * @author Carlos Torrejón
 */

public class EscritorController {
    
    
    final HttpClient cliente = HttpClient.newHttpClient();
    
    
    public void guardarEscritor(Escritor escritor) throws IOException, InterruptedException{
        
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(escritor);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.2.108:8080/escritor"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        
    }
    
    public void obtenerEscritorNombre(String nombre) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/escritor/query?nombre=" + nombre))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        System.out.println(respuesta.body());

        /*Usuario user = transObjeto(respuesta.body(), new TypeReference<Usuario>() {
        });

        modelo.addRow(new Object[]{user.getId(), user.getNombre(), user.getCorreo(), user.getFecha_creacion(), user.isAdmin()});

        tabla.setModel(modelo);
        //System.out.println(user.getId());*/

    }
}
