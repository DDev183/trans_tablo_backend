package dev.andreev.Controllers;


import dev.andreev.Service.DataProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class Controller {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
    DataProvider dataProvider;


    @GetMapping("/test")
    public List<String> test() throws Exception {
        return dataProvider.getSchedule(666);
    }
}
