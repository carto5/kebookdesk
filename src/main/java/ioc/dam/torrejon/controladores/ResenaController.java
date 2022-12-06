/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static ioc.dam.torrejon.controladores.LibrosController.JSON;
import ioc.dam.torrejon.modelos.Resena;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * @author carlo
 */
public class ResenaController {

    Utils util = new Utils();

    OkHttpClient cliente;
    RequestBody body;
    String urlBase = "https://localhost:8080/resena";

    /**
     * Método para guardar reseñas en la base de datos.
     *
     * @param resena objeto reseña.
     * @return codigo de conexión.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int guardarResena(Resena resena) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(resena);

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/resena"))
//                .header("token", Login.token)
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
//
//        return respuesta.statusCode();
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
     * Método para buscar reseñas por id.
     *
     * @return lista de reseñas.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Resena> obtenerResenas() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena"))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        } else {
//            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
//            });
//            return resena;
//
//        }
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
            List<Resena> resena = util.transObjeto(respuesta.string(), new TypeReference< List<Resena>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Resena> obtenerResenasDeLibroPorUsuario(String isbn, int idUsuario) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/" + isbn + "/usuario?idUsuario=" + idUsuario))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        System.out.println(respuesta.statusCode());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        } else {
//            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
//            });
//
//            return resena;
//
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/" + isbn + "/usuario?idUsuario=" + idUsuario)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.string(), new TypeReference< List<Resena>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Resena> obtenerResenasUsuario(int idUsuario) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/usuario?idUsuario=" + idUsuario))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        System.out.println(respuesta.statusCode());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        } else {
//            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
//            });
//
//            return resena;
//
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/usuario?idUsuario=" + idUsuario)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.string(), new TypeReference< List<Resena>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Resena> obtenerResenaPorId(int id_resena) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/" + id_resena))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        System.out.println(respuesta.statusCode());
//        System.out.println(respuesta.body());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        } else {
//            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
//            });
//            return resena;
//
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/" + id_resena)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.string(), new TypeReference< List<Resena>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Resena> obtenerResenaPorLibro(String isbn) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/libro?isbn=" + isbn))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        System.out.println(respuesta.statusCode());
//        if (respuesta.statusCode() != 200) {
//
//            return null;
//        } else {
//            List<Resena> resena = util.transObjeto(respuesta.body(), new TypeReference< List<Resena>>() {
//            });
//
//            return resena;
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/libro?isbn=" + isbn)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Resena> resena = util.transObjeto(respuesta.string(), new TypeReference< List<Resena>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int eliminarResena(int id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/resena/borrar?idResena=" + id))
//                .header("token", Login.token)
//                .DELETE()
//                .build();
//
//        HttpResponse respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        return respuesta.statusCode();
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/borrar?idResena=" + id)
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .delete()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();

        return response.code();
    }

}
