/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Libro;
import ioc.dam.torrejon.ventanas.Login;
import java.awt.Component;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Torrejón
 */
public class LibrosController {

    final HttpClient cliente = HttpClient.newHttpClient();

    private final Object[] columnas = new Object[]{"Isbn", "Título", "Autor", "Sinopsis", "Genero", "Disponible"};

    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    String mensaje = "Error en al conexión";

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
     * Método para listar libros
     * @param tabla donde se listaran los libros
     */
    public void ListarLibros(JTable tabla) {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro"))
                .header("token", Login.token)
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            /**
             * Usamos una List para almacenar la información de los usuarios que
             * nos envia el servidor
             */
            List<Libro> libro = transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
            });

            libro.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
            });

            tabla.setModel(modelo);

            respuesta.statusCode();

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }

    }

    /**
     * Método para buscar libros por su isbn.
     *
     * @param tabla donde se listara la busqueda.
     * @param isbn String con el isbn del libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public void ObtenerLibroIsbn(JTable tabla, String isbn) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/" + isbn))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>() {
        });

        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor().getNombre(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});

        tabla.setModel(modelo);
        respuesta.statusCode();

    }

    /**
     * Método para buscar libros por su título.
     *
     * @param tabla donde se listara la busqueda.
     * @param titulo String con el título del libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public void ObtenerLibroTitulo(JTable tabla, String titulo) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/titulo?titulo=" + titulo))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>() {
        });

        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor().getNombre(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});

        tabla.setModel(modelo);
        respuesta.statusCode();

    }

    /**
     * Método para buscar libros por su autor.
     *
     * @param tabla donde se listara la busqueda.
     * @param autor String con el nombre del autor.
     * @throws IOException
     * @throws InterruptedException
     */
    public void ObtenerLibroEscritor(JTable tabla, String autor) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/autor?autor=" + URLEncoder.encode(autor,"UTF-8")))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        List<Libro> libro = transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
        });

        libro.stream().forEach(item -> {
            modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
        });

        tabla.setModel(modelo);
        respuesta.statusCode();

    }

    /**
     * Método para buscar libros por genero.
     *
     * @param tabla donde se listara la busqueda.
     * @param genero String con el genero al que pertenece el libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public void ObtenerLibroGenero(JTable tabla, String genero) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/genero?genero=" + genero))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        List<Libro> libro = transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
        });

        libro.stream().forEach(item -> {
            modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
        });

        tabla.setModel(modelo);
        respuesta.statusCode();

    }

    /**
     * Método para buscar libros que están disponibles.
     *
     * @param tabla donde se listara la busqueda.
     * @throws IOException
     * @throws InterruptedException
     */
    public void ObtenerLibroDisponible(JTable tabla) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/disponibles"))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        List<Libro> libro = transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
        });

        libro.stream().forEach(item -> {
            modelo.addRow(new Object[]{item.getIsbn(), item.getTitulo(), item.getAutor().getNombre(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
        });

        tabla.setModel(modelo);
        respuesta.statusCode();

    }

    /**
     * Método para buscar libros por su isbn y si están disponibles.
     *
     * @param tabla donde se listara la busqueda.
     * @param isbn String con el isbn del libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public void ObtenerLibroDispIsbn(JTable tabla, String isbn) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/disponible/" + URLEncoder.encode(isbn,"UTF-8")))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>() {
        });

        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor().getId(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});

        tabla.setModel(modelo);
        respuesta.statusCode();
    }

    /**
     * Método para guardar libros en la base de datos.
     *
     * @param libro objeto de la clase libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public void guardarLibro(Libro libro) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(libro);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.2.108:8080/libro"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        
        respuesta.statusCode();

    }

}
