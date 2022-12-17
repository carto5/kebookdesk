/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Eventos;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static okhttp3.internal.Util.EMPTY_REQUEST;

/**
 *
 * @author Carlos Torrejón
 */
public class EventosController {

    Utils util = new Utils();

    OkHttpClient cliente;
    RequestBody body;
    String urlBase = "https://localhost:8080/evento";
    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Método para listar todos los eventos.
     * @return List de eventos.
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException 
     */
    public List<Eventos> listarEventos() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();
        

        if (response.code() != 200) {
            return null;
        } else {
            List<Eventos> eventos = util.transObjeto(respuesta.string(), new TypeReference< List<Eventos>>() {
            });

            return eventos;
        }

    }

    /**
     * Método para guardar eventos en la base de datos.
     *
     * @param evento objetod de la clase eventos.
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int guardarEvento(Eventos evento) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(evento);

        body = RequestBody.create(requestBody, JSON);
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase)
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());

        return response.code();
    }

    /**
     * Método para buscar eventos por libro.
     *
     * @param isbn del libro del evento.
     * @return lista de libros de la base de datos.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Eventos> obtenerEventosPorLibro(String isbn) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/libro?isbn=" + URLEncoder.encode(isbn, "UTF-8"))
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();
        System.out.println(response.code());

        if (response.code() != 200) {
            return null;
        } else {
            List<Eventos> eventos = util.transObjeto(respuesta.string(), new TypeReference< List<Eventos>>() {
            });

            return eventos;
        }
    }

    /**
     * Método para buscar eventos por su id.
     *
     * @param id del evento.
     * @return lista de libros de la base de datos.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public Eventos obtenerEventoPorId(String id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/" + id)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();
        System.out.println(response.code());

        if (response.code() != 200) {
            return null;
        } else {
            Eventos evento = util.transObjeto(respuesta.string(), new TypeReference<Eventos>() {
            });

            return evento;
        }
    }

    /**
     * Método para aprobar eventos.
     *
     * @param idAdmin
     * @param id del evento.
     * @return codigo de respuesta.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int aprobarEvento(int idAdmin, int id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/aprobar?idEvento=" + id + "&idAdmin=" + idAdmin)
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .post(EMPTY_REQUEST)
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());

        return response.code();

    }

    /**
     * Método para eliminar un usuario mediante su id.
     *
     * @param id del usuario.
     * @return codigo de respuesta.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int eliminarEvento(int id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/" + id)
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .delete()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();

        return response.code();

    }

    /**
     * Método para obtener los eventos aprobados.
     *
     * @return List de eventos.
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public List<Eventos> obtenerEventosAprobados() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/aprobados")
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Eventos> eventos = util.transObjeto(respuesta.string(), new TypeReference< List<Eventos>>() {
            });

            return eventos;
        }

    }

    /**
     * Método para obtener los eventos pendientes.
     *
     * @return List de eventos.
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public List<Eventos> obtenerEventosPendientes() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/pendientes")
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Eventos> eventos = util.transObjeto(respuesta.string(), new TypeReference< List<Eventos>>() {
            });

            return eventos;
        }

    }

}
