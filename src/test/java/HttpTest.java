
import ioc.dam.torrejon.controladores.AuthController;
import ioc.dam.torrejon.modelos.Usuario;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Carlos Torrejón
 */
@ExtendWith(MockitoExtension.class)
public class HttpTest {
    
    @Mock
    HttpClient cliente = HttpClient.newHttpClient();
    
    @Mock
    HttpRequest solicitud;
    
    @InjectMocks
    private AuthController autenticar;
    
    @Test
    public void authTest() throws IOException, InterruptedException {
        
        Usuario user = new Usuario();
        user.setNombre("hola");
        user.setContrasena("hola");
        user.setCorreo("hola");
        when(autenticar.registrarUsuario(user)).thenReturn(solicitud.hashCode());
        
    }
    
}
