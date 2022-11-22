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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class ResenaController {

    Utils util = new Utils();

    final HttpClient cliente = HttpClient.newHttpClient();

    private final Object[] columnas = new Object[]{"Id", "Id_usuario", "Isbn_libro", "Reseña"};

    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    private final Object[] columnError = new Object[]{"Error"};

    private final DefaultTableModel modeloError = new DefaultTableModel(columnError, 0);

    /**
     * Método para guardar reseñas en la base de datos.
     *
     * @param resena objeto reseña.
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
     * @param tabla donde se listara la busqueda.
     * @throws IOException
     * @throws InterruptedException
     */
    public void obtenerResenas(JTable tabla) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena"))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Error al recibir las reseñas"});
            tabla.setModel(modeloError);
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            resena.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getResena()});
            });
            tabla.setModel(modelo);
            respuesta.statusCode();
        }
    }

    /**
     * Método para obtener reseñas de libros por usuario.
     *
     * @param tabla donde se listara la busqueda.
     * @param isbn String con el isbn del libro.
     * @param idUsuario identificacion de usuario
     * @throws IOException
     * @throws InterruptedException
     */
    public void obtenerResenasDeLibroPorUsuario(JTable tabla, String isbn, int idUsuario) throws IOException, InterruptedException {

        Object[] columBookUser = new Object[]{"Id_usuario", "Isbn_libro", "Reseña"};

        DefaultTableModel modelBookUser = new DefaultTableModel(columBookUser, 0);

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/" + isbn + "/usuario?idUsuario=" + idUsuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Este libro no tiene reseñas"});
            tabla.setModel(modeloError);
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            resena.stream().forEach(item -> {
                modelBookUser.addRow(new Object[]{item.getUsuario().getId(), item.getLibro().getIsbn(), item.getResena()});
            });

            tabla.setModel(modelBookUser);
            respuesta.statusCode();
        }
    }

    /**
     * Método para obtener reseñas de libros por usuario.
     *
     * @param tabla donde se listara la busqueda.
     * @param idUsuario identificacion de usuario
     * @throws IOException
     * @throws InterruptedException
     */
    public void obtenerResenasUsuario(JTable tabla, int idUsuario) throws IOException, InterruptedException {

        Object[] columUser = new Object[]{"Id_usuario", "Reseña"};

        DefaultTableModel modelUser = new DefaultTableModel(columUser, 0);

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/usuario?idUsuario=" + idUsuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Este usuario no tiene reseñas"});
            tabla.setModel(modeloError);
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            resena.stream().forEach(item -> {
                modelUser.addRow(new Object[]{item.getUsuario().getId(), item.getResena()});
            });

            tabla.setModel(modelUser);
            respuesta.statusCode();
        }
    }

    /**
     * Método para obtener reseñas por su id.
     *
     * @param tabla donde se listara la busqueda.
     * @param id_resena identificador de la reseña.
     * @throws IOException
     * @throws InterruptedException
     */
    public void obtenerResenaPorId(JTable tabla, int id_resena) throws IOException, InterruptedException {

        Object[] columUser = new Object[]{"Id_reseña", "Reseña"};

        DefaultTableModel modelUser = new DefaultTableModel(columUser, 0);

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/" + id_resena))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Esta reseña con este identificador no está"});
            tabla.setModel(modeloError);
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            resena.stream().forEach(item -> {
                modelUser.addRow(new Object[]{item.getId(), item.getResena()});
            });

            tabla.setModel(modelUser);
            respuesta.statusCode();
        }
    }

    /**
     * Método para obtener reseñas por libros.
     *
     * @param tabla donde se listara la busqueda.
     * @param isbn del libro.
     * @throws IOException
     * @throws InterruptedException
     */
    public void obtenerResenaPorLibro(JTable tabla, String isbn) throws IOException, InterruptedException {

        Object[] columBook = new Object[]{"Libro", "Reseñas"};

        DefaultTableModel modelBook = new DefaultTableModel(columBook, 0);

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/libro?isbn=" + isbn))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Este libro no tiene reseñas"});
            tabla.setModel(modeloError);
        } else {
            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
            });

            resena.stream().forEach(item -> {
                modelBook.addRow(new Object[]{item.getLibro().getTitulo(), item.getResena()});
            });

            tabla.setModel(modelBook);
            respuesta.statusCode();
        }
    }

    /**
     * Método para eliminar reseñas mediante su id.
     *
     * @param id del usuario.
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
