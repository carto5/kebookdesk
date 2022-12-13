/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import ioc.dam.torrejon.modelos.Usuario;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static okhttp3.internal.Util.EMPTY_REQUEST;
//192.168.2.108

/**
 *
 * @author Carlos Torrejón
 */
public class UsuariosController {

    Utils util = new Utils();
    String urlBase = "https://localhost:8080/usuario";
    OkHttpClient cliente;


    /**
     * Método para listar los usuarios de la base de datos en una jTable.
     *
     * @return codigo de conexión.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public List<Usuario> listarUsuarios() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

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
            List<Usuario> usuarios = util.transObjeto(respuesta.string(), new TypeReference< List<Usuario>>() {
            });

            return usuarios;
        }
    }

    /**
     * Método para buscar usuario por id.
     *
     * @param id del usuario que se quiere buscar.
     * @return objeto usuario.
     * @throws java.io.IOException
     * @throws InterruptedException .
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public Usuario obtenerUsuarioPorId(int id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {


        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/" + id)
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
            Usuario user = util.transObjeto(respuesta.string(), new TypeReference<Usuario>() {
            });

            return user;
        }
    }

    /**
     * Método para buscar un usuario por su correo.
     *
     * @param correo del usuario que buscamos.
     * @return objeto usuario.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public Usuario obtenerUsuarioPorCorreo(String correo) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {


        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/correo?correo=" + correo)
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
            Usuario user = util.transObjeto(respuesta.string(), new TypeReference<Usuario>() {
            });

            return user;
        }

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
    public int eliminarUsuario(int id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

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
     * Método para que el usuario pueda cambiar su contraseña.
     *
     * @param contrasena antigua contraseña del usuario,
     * @param ncontrasena nueva contraseña del usuario.
     * @param id del usuario que desea haces el cambio.
     * @return codigo de respuesta.
     * @throws IOException
     * @throws InterruptedException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public int cambiarContrasena(String contrasena, String ncontrasena, int id) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        cliente = Utils.getTrustAllCertsClient();

        Request request = new Request.Builder()
                .url(urlBase + "/contrasena/cambiar?id=" + id + "&contrasenaAntigua=" + contrasena + "&contrasenaNueva=" + ncontrasena)
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .post(EMPTY_REQUEST)
                .build();

        Call call = cliente.newCall(request);
        Response response = call.execute();

        return response.code();

    }

}
