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
import java.lang.System.Logger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Torrejón
 */
public class LibrosController {
    
    final HttpClient cliente = HttpClient.newHttpClient();
    
    
    private final Object[] columnas = new Object[]{"Isbn","Título","Autor","Sinopsis","Genero","Disponible"};
    
    private final DefaultTableModel modelo = new DefaultTableModel(columnas,0);
    
    
    public <T> T transObjeto (final String json, final TypeReference<T> referencia){
        
        try {
            
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, referencia);
            
        } catch (IOException ex) {
            ex.getMessage();
        }
        return null;
    }
    
    
    public void ListarLibros (JTable tabla){
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro"))
                .header("token", Login.token)
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            /**
             * Usamos una List para almacenar la información de los usuarios que
             * nos envia el servidor
             */
            List<Libro> user = transObjeto(respuesta.body(), new TypeReference< List<Libro>>() {
            });

            user.stream().forEach(item -> {
                modelo.addRow(new Object[]{item.getIsbn() ,item.getTitulo(), item.getAutor(), item.getSinopsis(), item.getGenero(), item.isDisponible()});
            });
            

            tabla.setModel(modelo);

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
        
    }
    
    public void ObtenerLibroIsbn (JTable tabla, String isbn) throws IOException, InterruptedException{
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/"+isbn))
                .header("token", Login.token)
                .GET()
                .build();
        
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>(){});
        
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
        
        tabla.setModel(modelo);
        
    }
    
    public void ObtenerLibroTitulo (JTable tabla, String titulo) throws IOException, InterruptedException{
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/titulo?titulo="+ titulo))
                .header("token", Login.token)
                .GET()
                .build();
        
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>(){});
        
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
        
        tabla.setModel(modelo);
        
    }
    
    public void ObtenerLibroEscritor (JTable tabla, String autor) throws IOException, InterruptedException{
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/autor?autor="+ autor))
                .header("token", Login.token)
                .GET()
                .build();
        
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>(){});
        
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
        
        tabla.setModel(modelo);
        
    } 
    
    public void ObtenerLibroGenero (JTable tabla, String genero) throws IOException, InterruptedException{
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/genero?genero="+ genero))
                .header("token", Login.token)
                .GET()
                .build();
        
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>(){});
        
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
        
        tabla.setModel(modelo);
        
    }
    
    public void ObtenerLibroDisponible (JTable tabla) throws IOException, InterruptedException{
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/disponibles"))
                .header("token", Login.token)
                .GET()
                .build();
        
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>(){});
        
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
        
        tabla.setModel(modelo);
        
    }
    
    public void ObtenerLibroDispIsbn (JTable tabla, String isbn) throws IOException, InterruptedException{
        
        
        HttpRequest solicitud = HttpRequest.newBuilder(URI.create("http://192.168.2.108:8080/libro/disponible/"+isbn))
                .header("token", Login.token)
                .GET()
                .build();
        
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        
        Libro libro = transObjeto(respuesta.body(), new TypeReference<Libro>(){});
        
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getSinopsis(), libro.getGenero(), libro.isDisponible()});
        
        tabla.setModel(modelo);
    }
    
    public void guardarLibro(Libro libro) throws IOException, InterruptedException{
        
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(libro);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://192.168.2.108:8080/libro"))
                .header("token", Login.token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
    
}
