/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Reserva;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author Carlos Torrejón
 */
public class ReservasController {
    
    
    final HttpClient cliente = HttpClient.newHttpClient();
    
    
    public void guardarReserva(Reserva reserva) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(reserva);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/reserva"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        
        respuesta.statusCode();

    }
    
}
