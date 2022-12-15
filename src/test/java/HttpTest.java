
import ioc.dam.torrejon.controladores.AuthController;
import ioc.dam.torrejon.controladores.UsuariosController;
import ioc.dam.torrejon.modelos.Usuario;
import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.junit.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Carlos Torrejón
 */
public class HttpTest {

    AuthController login = new AuthController();
    UsuariosController user = new UsuariosController();
    Usuario usuario = new Usuario();

    @BeforeClass
    @Test
    public void authTest() throws IOException, InterruptedException, KeyManagementException, NoSuchAlgorithmException, JSONException {
        Login.token = login.userLogin("https://192.168.2.108:8080/login/admin/carlos@gmail.com/carlos");
        
    }
    
    @Test
    public void obtenerUsuarioIdTest() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException{
        usuario = user.obtenerUsuarioPorId(3);
        
        Assert.assertNotNull(usuario);
        
    }

}
