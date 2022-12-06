/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Escritor;
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

/**
 *
 * @author Carlos Torrejón
 */
public class EscritorController {

    OkHttpClient cliente;
    RequestBody body;
    Utils util = new Utils();
    
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String urlBase = "https://localhost:8080/escritor";

    
    /**
     * Método para guardar escritores en la base de datos.
     * @param escritor
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException 
     */
    public int guardarEscritor(Escritor escritor) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(escritor);

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(urlBase))
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
     * Método para buscar un escritor por su nombre.
     *
     * @param nombre String con el nombre del usuario.
     * @return codigo de conexión.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int obtenerEscritorNombre(String nombre) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/escritor/nombre?nombre=" + URLEncoder.encode(nombre, "UTF-8")))
//                .header("token", Login.token)
//                .header("Content-Type", "application/json")
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = clientes.send(solicitud, HttpResponse.BodyHandlers.ofString());
//
//        return respuesta.statusCode();
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/nombre?nombre=" + URLEncoder.encode(nombre, "UTF-8"))
                .header("token", Login.token)
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
//        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return response.code();
        } else {
//            Libro libro = util.transObjeto(respuesta.string(), new TypeReference<Libro>() {
//            });
            return response.code();
        }
    }

    /**
     * Método para listar todos los escritores.
     *
     * @return lista de escritores.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Escritor> listarEscritores() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase)
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .get()
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        ResponseBody respuesta = cliente.newCall(request).execute().body();

        if (response.code() != 200) {
            return null;
        } else {
            List<Escritor> escritores = util.transObjeto(respuesta.string(), new TypeReference< List<Escritor>>() {
            });

            return escritores;
        }

    }

}
