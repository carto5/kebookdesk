/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Resena;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 *
 * @author carlo
 */
public class ResenaController {

    Utils util = new Utils();

    final HttpClient cliente = HttpClient.newHttpClient();

    /**
     * Método para guardar reseñas en la base de datos.
     *
     * @param resena objeto reseña.
     * @return codigo de conexión.
     * @throws IOException
     * @throws InterruptedException
     */
    public int guardarResena(Resena resena) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(resena);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/resena"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        return respuesta.statusCode();

    }

    /**
     * Método para buscar reseñas por id.
     *
     * @return lista de reseñas.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Resena> obtenerResenas() throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena"))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });
            return resena;

        }
    }

    /**
     * Método para obtener reseñas de libros por usuario.
     *
     * @param isbn String con el isbn del libro.
     * @param idUsuario identificacion de usuario.
     * @return lista de reseñas.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Resena> obtenerResenasDeLibroPorUsuario(String isbn, int idUsuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/" + isbn + "/usuario?idUsuario=" + idUsuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            return resena;

        }
    }

    /**
     * Método para obtener reseñas de libros por usuario.
     *
     * @param idUsuario identificacion de usuario
     * @return lista de reseñas de usuario.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Resena> obtenerResenasUsuario(int idUsuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/usuario?idUsuario=" + idUsuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            return resena;

        }
    }

    /**
     * Método para obtener reseñas por su id.
     *
     * @param id_resena identificador de la reseña.
     * @return lista de reseñas de usuario.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Resena> obtenerResenaPorId(int id_resena) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/" + id_resena))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });
            return resena;

        }
    }

    /**
     * Método para obtener reseñas por libros.
     *
     * @param isbn del libro.
     * @return lista de resñas.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Resena> obtenerResenaPorLibro(String isbn) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/libro?isbn=" + isbn))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {

            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            return resena;
        }
    }

    /**
     * Método para eliminar reseñas mediante su id.
     *
     * @param id del usuario.
     * @return codigo de conexión.
     * @throws IOException
     * @throws InterruptedException
     */
    public int eliminarResena(int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/borrar?idResena=" + id))
                .header("token", Login.token)
                .DELETE()
                .build();

        HttpResponse respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        return respuesta.statusCode();
    }

}
