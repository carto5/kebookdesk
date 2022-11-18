/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Libro;
import ioc.dam.torrejon.modelos.Reserva;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     *
     * @param tabla donde se listaran los libros
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public void ListarReservas(JTable tabla) {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva"))
                .header("token", Login.token)
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            System.out.println(respuesta.body());
            /**
             * Usamos una List para almacenar la información de las reservas que
             * nos envia el servidor
             */
            ArrayList<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< ArrayList<Reserva>>() {
            });


            reserva.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
            });

            tabla.setModel(modelo);
            System.out.println(respuesta.statusCode());

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
    public void ObtenerReservaUsuario(JTable tabla, int usuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/usuario?iDusuario=" + usuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.body());
        ArrayList<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< ArrayList<Reserva>>() {
        });

        reserva.stream().forEach(item -> {
            modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
        });
        tabla.setModel(modelo);
        respuesta.statusCode();

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
    public void ObtenerReservadeLibroPorUsuario(JTable tabla, String isbn, int usuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/" + isbn + "/usuario?idUsuario=" + usuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        System.out.println(respuesta.body());

        List<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< List<Reserva>>() {
        });

        reserva.stream().forEach(item -> {
            modelo.addRow(new Object[]{item.getId(), item.getUsuario().getId(), item.getLibro().getIsbn(), item.getFecha_inicio(), item.getFecha_fin(), item.isRecogido(), item.isDevuelto()});
        });
        tabla.setModel(modelo);
        respuesta.statusCode();
        System.out.println(respuesta.statusCode());
    }

    /**
     * Método para comprobar recogida del libro.
     *
     * @param reserva int con la id de la reserva.
     * @return un boolean.
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean comprobarReservaRecogida(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/recogido?idReserva=" + reserva))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        return "true".equals(respuesta.body());
    }

    /**
     * Método para comprobar la devolución del libro.
     *
     * @param reserva int con la id de la reserva.
     * @return un boolean.
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean comprobarReservaDevuelta(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/devuelto?idReserva=" + reserva))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        return "true".equals(respuesta.body());
    }

    /**
     * Método para confirmar recogida
     *
     * @param reserva número de reserva.
     * @throws IOException
     * @throws InterruptedException
     */
    public void confirmarRecogida(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/" + reserva + "/recogido"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        System.out.println(respuesta.statusCode());
    }

    /**
     * Método para confirmar devolución.
     *
     * @param reserva número de reserva.
     * @throws IOException
     * @throws InterruptedException
     */
    public void confirmarDevolucion(int reserva) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/" + reserva + "/devuelto"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.statusCode());
    }

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
