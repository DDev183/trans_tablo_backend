package dev.andreev.Controllers;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController

public class Controller {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    @GetMapping("/test")
    public String test() throws IOException {
        HttpGet request = new HttpGet("http://proezd.kttu.ru/krasnodar/departures2.php?stopid=715");
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            return EntityUtils.toString(response.getEntity());
        }
    }
}
