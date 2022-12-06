/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ioc.dam.torrejon.kebookdesk;

import ioc.dam.torrejon.ventanas.Login;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Carlos Torrejón
 */
public class Kebookdesk {

    public static HttpClient getTrustAllCertsClient() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        HttpClient client = HttpClient.newBuilder().sslContext(sslContext).build();
        return client;

//        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
//        newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
//        newBuilder.hostnameVerifier((hostname, session) -> true);
//        return newBuilder.build();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException {
        Login login = new Login();
        login.setVisible(true);
//        var properties = System.getProperties();
//        properties.setProperty("jdk.internal.httpclient.disableHostnameVerification", Boolean.TRUE.toString());
//        HttpClient client = getTrustAllCertsClient();
//        HttpRequest solicitud = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create("https://localhost:8080/login/carlos@gmail.com/carlos"))
//                .build();
//
//        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());
//        System.out.println(respuesta.body());

//        OkHttpClient newClient = getTrustAllCertsClient();
//        Response response = newClient.newCall(new Request.Builder().url("https://localhost:8080/login/carlos@gmail.com/carlos").get().build()).execute();
//        
//        System.out.println(response.body().string());
    }
}
