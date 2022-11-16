/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author Carlos Torrejón
 */
public class AuthController {
    
    final HttpClient cliente = HttpClient.newHttpClient();
    
    /**
     * Método para autenticarse
     *
     * @param  url con la dirección y datos del usuario para logearse en el servidor. 
     * @return el token de autenticación.
     * @throws IOException
     * @throws InterruptedException
     */
    public String userLogin(String url) throws IOException, InterruptedException {


        HttpRequest solicitud = HttpRequest.newBuilder(URI.create(url)).GET()
                .build();

        HttpResponse respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

                            System.out.println(respuesta.body().toString());   

        return respuesta.body().toString();
        
        

    }
    
    /**
     * Método para insertar usuarios en la base de datos.
     * @param usuario objeto de la clase usuario.
     * @throws IOException
     * @throws InterruptedException 
     */
    public void registrarUsuario(Usuario usuario) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(usuario);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.2.108:8080/usuario"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        
        respuesta.statusCode();

    }
    
}
