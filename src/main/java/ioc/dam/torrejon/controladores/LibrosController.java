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
import java.net.URLEncoder;
import java.net.http.HttpClient;
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

/**
 *
 * @author Carlos Torrejón
 */
public class LibrosController {

    Utils util = new Utils();

    OkHttpClient cliente;
    RequestBody body;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String urlBase = "https://localhost:8080/libro";

    /**
     * Método para listar libros
     *
     * @return lista de libros.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Libro> ListarLibros() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

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
            List<Libro> libro = util.transObjeto(respuesta.string(), new TypeReference< List<Libro>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public Libro ObtenerLibroIsbn(String isbn) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/" + isbn)
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            Libro libro = util.transObjeto(respuesta.string(), new TypeReference<Libro>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public Libro ObtenerLibroTitulo(String titulo) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/titulo?titulo=" + URLEncoder.encode(titulo, "UTF-8")))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        System.out.println(respuesta.statusCode());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        }
//        Libro libro = util.transObjeto(respuesta.body(), new TypeReference<Libro>() {
//        });
//
//        return libro;
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/titulo?titulo=" + URLEncoder.encode(titulo, "UTF-8"))
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            Libro libro = util.transObjeto(respuesta.string(), new TypeReference<Libro>() {
            });

            return libro;
        }

    }

    /**
     * Método para buscar libros por su autor.
     *
     * @param autor String con el nombre del autor.
     * @return lista de libros de la base de datos.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Libro> ObtenerLibroEscritor(String autor) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/autor?autor=" + URLEncoder.encode(autor, "UTF-8")))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());
//
//        if (respuesta.statusCode() != 200) {
//
//            return null;
//        } else {
//            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
//            });
//
//            return libro;
//
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/autor?autor=" + URLEncoder.encode(autor, "UTF-8"))
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Libro> libro = util.transObjeto(respuesta.string(), new TypeReference< List<Libro>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Libro> ObtenerLibroGenero(String genero) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/genero?genero=" + URLEncoder.encode(genero, "UTF-8")))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        } else {
//            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
//            });
//
//            return libro;
//
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/autor?autor=" + URLEncoder.encode(genero, "UTF-8"))
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Libro> libro = util.transObjeto(respuesta.string(), new TypeReference< List<Libro>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Libro> ObtenerLibroDisponible() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/libro/disponibles"))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());
//
//        if (respuesta.statusCode() != 200) {
//            return null;
//        } else {
//            List<Libro> libro = util.transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
//            });
//
//            return libro;
//
//        }
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/disponibles")
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Libro> libro = util.transObjeto(respuesta.string(), new TypeReference< List<Libro>>() {
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
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int guardarLibro(Libro libro) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(libro);

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

}
