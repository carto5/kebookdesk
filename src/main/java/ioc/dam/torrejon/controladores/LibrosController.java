/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Libro;
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
public class LibrosController {

    Utils util = new Utils();

    final HttpClient cliente = HttpClient.newHttpClient();



    /**
     * Método para listar libros
     *
     * @return lista de libros.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public List<Libro> ListarLibros() throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro"))
                .header("token", Login.token)
                .GET()
                .build();


        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            /**
             * Usamos una List para almacenar la información de los usuarios que
             * nos envia el servidor
             */
            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
            });

            return libro;

        }

    }

    /**
     * Método para buscar libros por su isbn.
     *
     * @param isbn String con el isbn del libro.
     * @return lista de libros de la base de datos.
     * @throws IOException
     * @throws InterruptedException
     */
    public Libro ObtenerLibroIsbn(String isbn) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/" + isbn))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            Libro libro = util.transObjeto(respuesta.body(), new TypeReference<Libro>() {
            });

            return libro;


        }
    }


    /**
     * Método para buscar libros por su título.
     *
     * @param titulo String con el título del libro.
     * @return objeto libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public Libro ObtenerLibroTitulo(String titulo) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/titulo?titulo=" + URLEncoder.encode(titulo, "UTF-8")))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            return null;
        }
        Libro libro = util.transObjeto(respuesta.body(), new TypeReference<Libro>() {
        });

        return libro;

    }

    /**
     * Método para buscar libros por su autor.
     *
     * @param autor String con el nombre del autor.
     * @return lista de libros de la base de datos.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Libro> ObtenerLibroEscritor(String autor) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/autor?autor=" + URLEncoder.encode(autor, "UTF-8")))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {

            return null;
        } else {
            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
            });

            return libro;

        }
    }

    /**
     * Método para buscar libros por genero.
     *
     * @param genero String con el genero al que pertenece el libro.
     * @return lista de libros de la base de datos.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Libro> ObtenerLibroGenero(String genero) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/genero?genero=" + URLEncoder.encode(genero, "UTF-8")))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
            });

            return libro;

        }
    }

    /**
     * Método para buscar libros que están disponibles.
     *
     * @return 
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Libro> ObtenerLibroDisponible() throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/disponibles"))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
            });

            return libro;

        }

    }

 
    /**
     * Método para guardar libros en la base de datos.
     *
     * @param libro objeto de la clase libro.
     * @return 
     * @throws IOException
     * @throws InterruptedException
     */
    public int guardarLibro(Libro libro) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(libro);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/libro"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        return respuesta.statusCode();

    }

}
