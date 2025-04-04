package org.example.Mod5;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class WebService {
    //Por convencion, debe de estar en CAPS
    public static final String URL = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicos";


    //Se llama al metodo
    public IndicadorEco getIndicador(
            String indicador,
            String fechaInicio,
            String fechaFinal,
            String nombre,
            String subNiveles,
            String correo,
            String token

    ) throws Exception{

        //Es el post data
        String parametros =
                "Indicador=" + indicador +
                "&FechaInicio=" + fechaInicio +
                "&FechaFinal=" + fechaFinal +
                "&Nombre=" + nombre +
                "&SubNiveles=" + subNiveles +
                "&CorreoElectronico=" + correo +
                "&Token=" + token;

        //Se crea el request primeramente

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(HttpRequest.BodyPublishers.ofString(parametros, StandardCharsets.UTF_8))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        //Luego se crea el cliente

        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = responseFuture.join();

        //Ahora imprime el status
        System.out.println("Estado de respuesta: " + response.statusCode());
        System.out.println("Respuesta: " + response.body());

        //Si es respuesta 200 = OK  De 200-299 Son Succesful Response Codes
        if(response.statusCode() == 200) {
            try(InputStream inputStream = new ByteArrayInputStream(response.body().getBytes())){
                JAXBContext jaxbContext = JAXBContext.newInstance(IndicadorEco.class);
                Unmarshaller unmarshaller =  jaxbContext.createUnmarshaller();
                return(IndicadorEco) unmarshaller.unmarshal(inputStream);
            }

        }else{
            System.out.println("La respuesta falló");
            throw new Exception("Hubo un error en la solicitud " + response.statusCode()); //Se imprime el codigo de status
        }

    }





}


