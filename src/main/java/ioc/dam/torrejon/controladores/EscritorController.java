/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Escritor;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 *
 * @author Carlos Torrejón
 */
public class EscritorController {

    final HttpClient cliente = HttpClient.newHttpClient();

    Utils util = new Utils();

    public int guardarEscritor(Escritor escritor) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(escritor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/escritor"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        return respuesta.statusCode();

    }

    /**
     * Método para buscar un escritor por su nombre.
     *
     * @param nombre String con el nombre del usuario.
     * @return codigo de conexión.
     * @throws IOException
     * @throws InterruptedException
     */
    public int obtenerEscritorNombre(String nombre) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/escritor/nombre?nombre=" + URLEncoder.encode(nombre, "UTF-8")))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        return respuesta.statusCode();

    }

    /**
     * Método para listar todos los escritores.
     *
     * @return lista de escritores.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public List<Escritor> listarEscritores() throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/escritor"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .GET()
                .build();


            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            if (respuesta.statusCode() != 200) {
                return null;
            } else {
                /**
                 * Usamos una List para almacenar la información de los usuarios
                 * que nos envia el servidor
                 */
                List<Escritor> escritor = util.transObjeto(respuesta.body(), new TypeReference< List<Escritor>>() {
                });
                return escritor;
            }

    }

}
