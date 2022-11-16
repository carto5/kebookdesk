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
public class EscritorController {

    final HttpClient cliente = HttpClient.newHttpClient();

    private final Object[] columnas = new Object[]{"Id", "Nombre"};

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
     * Método para guardar escritor en la base de datos.
     * @param escritor objeto de la clase escritor.
     * @throws IOException
     * @throws InterruptedException 
     */

    public void guardarEscritor(Escritor escritor) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(escritor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.2.108:8080/escritor"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        respuesta.statusCode();

    }
    
    /**
     * Método para buscar un escritor por su nombre.
     * @param nombre String con el nombre del usuario.
     * @throws IOException
     * @throws InterruptedException 
     */

    public void obtenerEscritorNombre(String nombre) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/escritor/nombre?nombre=" + nombre))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        respuesta.statusCode();;

    }

    
    /**
     * Método para listar todos los escritores.
     * @param tabla donde se listaran los escritores.
     */
    public void listarEscritores(JTable tabla) {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/escritor"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            /**
             * Usamos una List para almacenar la información de los usuarios que
             * nos envia el servidor
             */
            List<Escritor> escritor = transObjeto(respuesta.body(), new TypeReference< List<Escritor>>() {
            });

            escritor.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getId(), item.getNombre()});
            });

            tabla.setModel(modelo);
            respuesta.statusCode();

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }

    }
    
    /**
     * Método para eliminar escritores de la base de datos.
     * @param id del escritor.
     * @throws IOException
     * @throws InterruptedException 
     */

    public void eliminarEscritor(int id) throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/escritor/" + id))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        respuesta.statusCode();

    }
}
