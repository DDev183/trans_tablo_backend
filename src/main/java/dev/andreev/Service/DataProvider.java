package dev.andreev.Service;


import dev.andreev.Models.Route;
import dev.andreev.Models.ScheduleCase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataProvider {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static List<Route> routeList;
    private final static int[] tramNumberList = {1,2,3,4,5,6,7,8,9,10,11,12,15,20,21,22};




    public List<String> getSchedule(int stopId) throws Exception {
        HttpGet request = new HttpGet("http://proezd.kttu.ru/krasnodar/departures2.php?stopid=715");
        String source;
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            source = EntityUtils.toString(response.getEntity());
        } catch (Exception e){
            throw new Exception("Source receiving error");
        }

        if (!source.contains("is stop not found in departures")){
            Arrays.stream(source.split("\n")).skip(1).forEach(tram -> caseInfo(tram));
        } else {
            throw new Exception("Source receiving error");
        }


        return null;
    }



    private ScheduleCase caseInfo(String tram){
        int route = Integer.parseInt(tram.split(",")[1]);
        String direction = tram.split(",")[2];
        int toDayTime = Integer.parseInt(tram.split(",")[3]);
        return null;

    }

    @PostConstruct
    private void reciveRoutes() throws Exception {
        Map<String, String> destinations = new HashMap<>();
        destinations.put("a-b", "Улица Декабристов — ж/д вокзал \"Краснодар-I");
        destinations.put("b-a", "Ж/д вокзал \"Краснодар-I\" — улица Декабристов");
        destinations.put("a-d", "Улица Декабристов — Восточное депо");
        destinations.put("a-d2", "Улица Декабристов  — Западное депо");
        destinations.put("a1-d", "Улица Декабристов — Восточное депо (по ул. Садовой)");
        destinations.put("b-d2", "Ж/д вокзал \"Краснодар-I\" — Западное депо");
        destinations.put("d-a", "Восточное депо — улица Декабристов");
        destinations.put("d-a1", "Восточное депо — улица Декабристов (по ул. Садовой)");
        destinations.put("d2-a", "Западное депо — улица Декабристов");
        routeList.add(new Route(1, destinations));
        destinations.clear();

    }



}
