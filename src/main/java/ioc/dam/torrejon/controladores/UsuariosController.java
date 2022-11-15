/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.torrejon.controladores;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.dam.torrejon.modelos.Usuario;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Torrejón
 */
public class UsuariosController {

    final HttpClient cliente = HttpClient.newHttpClient();

    private final Object[] columnas = new Object[]{"Id", "Nombre", "Correo",  "Fecha de creación", "Administrador"};

    private final DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

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
     * Método para listar los usuarios de la base de datos en una jTable.
     *
     * @param tabla donde se listaran los usuarios.
     */
    public void listarUsuarios(JTable tabla) {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/usuario"))
                .header("token", Login.token)
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            /**
             * Usamos una List para almacenar la información de los usuarios que
             * nos envia el servidor
             */
            List<Usuario> user = transObjeto(respuesta.body(), new TypeReference< List<Usuario>>() {
            });

            user.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getNombre(), item.getCorreo(), item.getContrasena(), item.getFecha_creacion(), item.isAdmin()});
            });

            tabla.setModel(modelo);

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }

    }
    
    /**
     * Método para buscar usuario por id.
     * @param tabla donde se listara el resultado.
     * @param id del usuario que se quiere buscar.
     * @throws java.io.IOException
     * @throws InterruptedException .
     */

    public void obtenerUsuarioPorId(JTable tabla, int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/usuario/" + id))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        Usuario user = transObjeto(respuesta.body(), new TypeReference<Usuario>() {
        });

        modelo.addRow(new Object[]{user.getId(), user.getNombre(), user.getCorreo(), user.getFecha_creacion(), user.isAdmin()});

        tabla.setModel(modelo);
        //System.out.println(user.getId());

    }
    
    /**
     * Método para buscar un usuario por su correo.
     * @param tabla donde se listara el resultado.
     * @param correo del usuario que buscamos.
     * @throws IOException
     * @throws InterruptedException 
     */

    public void obtenerUsuarioPorCorreo(JTable tabla, String correo) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/usuario/correo?correo=" + correo))
                .header("token", Login.token)
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        Usuario user = transObjeto(respuesta.body(), new TypeReference<Usuario>() {
        });

        modelo.addRow(new Object[]{user.getId(), user.getNombre(), user.getCorreo(), user.getFecha_creacion(), user.isAdmin()});

        tabla.setModel(modelo);
        //System.out.println(user.getId());

    }

    
    /**
     * Método para eliminar un usuario mediante su id.
     * @param id del usuario.
     * @throws IOException
     * @throws InterruptedException
     */
    public void eliminarUsuario(int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/usuario/" + id))
                .header("token", Login.token)
                .DELETE()
                .build();
        
        HttpResponse respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
    }
    
    /**
     * Método para que el usuario pueda cambiar su contraseña.
     * @param contrasena antigua contraseña del usuario,
     * @param ncontrasena nueva contraseña del usuario.
     * @param id del usuario que desea haces el cambio.
     * @throws IOException
     * @throws InterruptedException 
     */
    
    public void cambiarContrasena (String contrasena, String ncontrasena, int id) throws IOException, InterruptedException{
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/usuario/contrasena/cambiar?id=" + id+"&contrasenaAntigua="+contrasena+"&contrasenaNueva="+ncontrasena))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        
        
    }

}
