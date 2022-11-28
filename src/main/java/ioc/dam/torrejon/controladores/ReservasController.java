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

/**
 *
 * @author Carlos Torrejón
 */
public class ReservasController {

    final HttpClient cliente = HttpClient.newHttpClient();

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
     * @return lista de resevas.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public List<Reserva> ListarReservas() throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva"))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            /**
             * Usamos una ArrayList para almacenar la información de las
             * reservas que nos envia el servidor
             */
            List<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< ArrayList<Reserva>>() {
            });

            return reserva;

        }
    }

    /**
     * Método para buscar reservas por usuario.
     *
     * @param usuario int con el id del usuario.
     * @throws IOException
     * @throws InterruptedException
     */

    /**
     * Método para buscar reservas por usuario.
     *
     * @param usuario int con el id del usuario.
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Reserva> ObtenerReservaUsuario(int usuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/usuario?idUsuario=" + usuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            List<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< ArrayList<Reserva>>() {
            });

            return reserva;
        }

    }

    /**
     * Método para buscar reservas del libro por usuario.
     *
     * @param isbn del libro
     * @param usuario int con el id del usuario.
     * @return list de reservas.
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Reserva> ObtenerReservadeLibroPorUsuario( String isbn, int usuario) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/reserva/" + isbn + "/usuario?idUsuario=" + usuario))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        System.out.println(respuesta.body());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {

            List<Reserva> reserva = transObjeto(respuesta.body(), new TypeReference< List<Reserva>>() {
            });
            return reserva;

        }

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
