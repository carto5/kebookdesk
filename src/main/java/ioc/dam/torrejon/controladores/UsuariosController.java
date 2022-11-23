/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import ioc.dam.torrejon.modelos.Usuario;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Torrejón
 */
public class UsuariosController {

    final HttpClient cliente = HttpClient.newHttpClient();
    
    Utils util = new Utils();
    List<Usuario> user;

    private final Object[] columnas = new Object[]{"Id", "Nombre", "Correo", "Fecha de creación", "Administrador"};

    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    private final Object[] columnError = new Object[]{"Error"};

    private final DefaultTableModel modeloError = new DefaultTableModel(columnError, 0);


    /**
     * Método para listar los usuarios de la base de datos en una jTable.
     *
     * @return codigo de conexión.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public List<Usuario> listarUsuarios() throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/usuario"))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        if (respuesta.statusCode() != 200) {
            return null;
        } else {
            /**
             * Usamos una List para almacenar la información de los usuarios que
             * nos envia el servidor
             */
            user = util.transObjeto(respuesta.body(), new TypeReference< List<Usuario>>() {
            });
            return user;


        }
        
    }

    /**
     * Método para buscar usuario por id.
     *
     * @param tabla donde se listara el resultado.
     * @param id del usuario que se quiere buscar.
     * @return objeto usuario.
     * @throws java.io.IOException
     * @throws InterruptedException .
     */
    public Usuario obtenerUsuarioPorId( int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/usuario/" + id))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        if (respuesta.statusCode() != 200) {
//            modeloError.addRow(new Object[]{"Este usuario no está registrado"});
//            tabla.setModel(modeloError);
            return null;
        } else {

        Usuario user = util.transObjeto(respuesta.body(), new TypeReference<Usuario>() {
        });
        return user;

//        modelo.addRow(new Object[]{user.getId(), user.getNombre(), user.getCorreo(), user.getFecha_creacion(), user.isAdmin()});
//
//        tabla.setModel(modelo);
//        respuesta.statusCode();
        //System.out.println(user.getId());
        }
    }

    /**
     * Método para buscar un usuario por su correo.
     *
     * @param correo del usuario que buscamos.
     * @return objeto usuario.
     * @throws IOException
     * @throws InterruptedException
     */
    public Usuario obtenerUsuarioPorCorreo( String correo) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/usuario/correo?correo=" + correo))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        if(respuesta.statusCode()!=200){
            return null;
        }else{

        Usuario user = util.transObjeto(respuesta.body(), new TypeReference<Usuario>() {
        });
        return user;
        }

//        modelo.addRow(new Object[]{user.getId(), user.getNombre(), user.getCorreo(), user.getFecha_creacion(), user.isAdmin()});
//
//        tabla.setModel(modelo);
//        respuesta.statusCode();
//        //System.out.println(user.getId());

    }

//    /**
//     * Método para buscar un usuario por su correo.
//     *
//     * @param correo del usuario que buscamos.
//     * @return objeto Usuario.
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public Usuario obtenerUsuarioPorCorreo(String correo) throws IOException, InterruptedException {
//
//        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/usuario/correo?correo=" + correo))
//                .header("token", Login.token)
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        if (respuesta.statusCode() != 200) {
//            return null;
//        }
//        Usuario user = util.transObjeto(respuesta.body(), new TypeReference<Usuario>() {
//        });
//        return user;
//
//    }

    /**
     * Método para eliminar un usuario mediante su id.
     *
     * @param id del usuario.
     * @return codigo de respuesta.
     * @throws IOException
     * @throws InterruptedException
     */
    public int eliminarUsuario(int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/usuario/" + id))
                .header("token", Login.token)
                .DELETE()
                .build();

        HttpResponse respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        return respuesta.statusCode();
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
     */
    public int cambiarContrasena(String contrasena, String ncontrasena, int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://localhost:8080/usuario/contrasena/cambiar?id=" + id + "&contrasenaAntigua=" + contrasena + "&contrasenaNueva=" + ncontrasena))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        return respuesta.statusCode();

    }

}
