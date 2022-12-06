/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
//83.44.21.164

/**
 *
 * @author Carlos Torrejón
 */
public class AuthController {

    OkHttpClient cliente;
    RequestBody body;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Método para autenticarse
     *
     * @param url con la dirección y datos del usuario para logearse en el
     * servidor.
     * @return el token de autenticación.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public String userLogin(String url) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {


        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = cliente.newCall(request);

        Response respuesta = call.execute();
               
        if (respuesta.code() != 200) {
            System.out.println(respuesta.code());
            return "ERROR";

        } else {
            System.out.println(respuesta.code());

            ResponseBody body = respuesta.body();
            String token = body.string();
            System.out.println(token);

            return token;
        }

    }

    /**
     * Método para insertar usuarios en la base de datos.
     *
     * @param usuario objeto de la clase usuario.
     * @return codigo de conexion.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.KeyManagementException
     * @throws java.security.NoSuchAlgorithmException
     * @throws org.json.JSONException
     */
    public int registrarUsuario(Usuario usuario) throws IOException, InterruptedException, KeyManagementException, NoSuchAlgorithmException, JSONException {
                var objectMapper = new ObjectMapper();
                String requestBody = objectMapper
                        .writeValueAsString(usuario);

        body = RequestBody.create(requestBody, JSON);
        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url("https://localhost:8080/usuario")
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());

        return response.code();

    }

}
