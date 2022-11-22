/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Reserva;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Torrejón
 */
public class ReservasController {

    final HttpClient cliente = HttpClient.newHttpClient();

    private final Object[] columnas = new Object[]{"ID", "id_usuario", "Isbn_libro", "Fecha_inició", "Fecha_final", "Recogido", "Devuelto"};

    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    private final Object[] columnError = new Object[]{"Error"};

    private final DefaultTableModel modeloError = new DefaultTableModel(columnError, 0);

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
     *
     * @param tabla donde se listaran los libros
     */
    public void ListarReservas(JTable tabla) {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva"))
                .header("token", Login.token)
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            System.out.println(respuesta.body());
            if (respuesta.statusCode() != 200) {
                modeloError.addRow(new Object[]{"Error al recibir las reservas"});
                tabla.setModel(modeloError);
            } else {
                /**
                 * Usamos una List para almacenar la información de las reservas
                 * que nos envia el servidor
                 */
                ArrayList<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< ArrayList<Reserva>>() {
                });

                reserva.stream().forEach(item -> {
                    modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
                });

                tabla.setModel(modelo);
                System.out.println(respuesta.statusCode());
            }
            //return respuesta.statusCode();
        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }

    }

    /**
     * Método para buscar reservas por usuario.
     *
     * @param tabla donde se listara la busqueda.
     * @param usuario int con el id del usuario.
     * @throws IOException
     * @throws InterruptedException
     */
    public int ObtenerReservaUsuario(JTable tabla, int usuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/usuario?idUsuario=" + usuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Este usuario no tiene reserva"});
            tabla.setModel(modeloError);
        } else {
            ArrayList<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< ArrayList<Reserva>>() {
            });

            reserva.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
            });
            tabla.setModel(modelo);
            return respuesta.statusCode();
        }
        return respuesta.statusCode();
    }

    /**
     * Método para buscar reservas del libro por usuario.
     *
     * @param tabla donde se listara la busqueda.
     * @param isbn del libro
     * @param usuario int con el id del usuario.
     * @throws IOException
     * @throws InterruptedException
     */
    public int ObtenerReservadeLibroPorUsuario(JTable tabla, String isbn, int usuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/" + isbn + "/usuario?idUsuario=" + usuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            modeloError.addRow(new Object[]{"Error al recibir las reseñas"});
            tabla.setModel(modeloError);
        } else {

            List<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< List<Reserva>>() {
            });

            reserva.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
            });
            tabla.setModel(modelo);
            return respuesta.statusCode();
        }
        return respuesta.statusCode();
    }

    /**
     * Método para comprobar recogida del libro.
     *
     * @param reserva int con la id de la reserva.
     * @return un boolean.
     * @throws IOException
     * @throws InterruptedException
     */
    public int comprobarReservaRecogida(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/recogido?idReserva=" + reserva))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        System.out.println(respuesta.body());
        
        return respuesta.statusCode();
    }

    /**
     * Método para comprobar la devolución del libro.
     *
     * @param reserva int con la id de la reserva.
     * @return un boolean.
     * @throws IOException
     * @throws InterruptedException
     */
    public int comprobarReservaDevuelta(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/devuelto?idReserva=" + reserva))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.body());

        return respuesta.statusCode();

    }

    /**
     * Método para confirmar recogida
     *
     * @param reserva número de reserva.
     * @return codigo de conexión
     * @throws IOException
     * @throws InterruptedException
     */
    public int confirmarRecogida(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/" + reserva + "/recogido"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        System.out.println(respuesta.body());
        return respuesta.statusCode();
    }

    /**
     * Método para confirmar devolución.
     *
     * @param reserva número de reserva.
     * @return codigo de conexión
     * @throws IOException
     * @throws InterruptedException
     */
    public int confirmarDevolucion(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/" + reserva + "/devuelto"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
        return respuesta.statusCode();
    }

    public int guardarReserva(Reserva reserva) throws IOException, InterruptedException {

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

        return respuesta.statusCode();

    }

}
