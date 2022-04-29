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

        destinations.put("a-b", "Улица Декабристов — улица Индустриальная");
        destinations.put("b-a", "Улица Индустриальная — улица Декабристов");
        destinations.put("a-d", "Улица Декабристов — Западное депо");
        destinations.put("b-d", "Улица Индустриальная — Западное депо");
        destinations.put("d-a", "Западное депо — улица Декабристов");
        destinations.put("d-b", "Западное депо — улица Индустриальная");
        routeList.add(new Route(2, destinations));
        destinations.clear();

        destinations.put("a-b", "Западное депо — улица Декабристов");
        destinations.put("b-a", "Улица Декабристов — Западное депо");
        destinations.put("b-d", "Улица Декабристов — в депо");
        destinations.put("d-b", "Из депо — улица Декабристов");
        routeList.add(new Route(3, destinations));
        destinations.clear();

        destinations.put("a-b", "Комсомольский микрорайон — улица Индустриальная");
        destinations.put("b-a", "Улица Индустриальная — Комсомольский микрорайон");
        destinations.put("a-d", "Комсомольский микрорайон — Восточное депо");
        destinations.put("b-d", "Улица Индустриальная — Восточное депо");
        destinations.put("d-a", "Восточное депо — Комсомольский микрорайон");
        destinations.put("d-b", "Восточное депо — улица Индустриальная");
        routeList.add(new Route(4, destinations));
        destinations.clear();




        destinations.put("a-b", "Улица Е.Бершанской — улица П.Метальникова");
        destinations.put("b-a", "Улица П.Метальникова — улица Е.Бершанской");
        destinations.put("a-d", "Улица Е.Бершанской — Восточное депо");
        destinations.put("b-d", "Улица П.Метальникова — Восточное депо");
        destinations.put("d-a", "Восточное депо — улица Е.Бершанской");
        destinations.put("d-b", "Восточное депо — улица П.Метальникова");
        destinations.put("c-d", "Пашковский мкр. — Хладокомбинат — Восточное депо");
        destinations.put("d1-b", "Восточное депо — улица П.Метальникова (по ул. Садовая)");
        routeList.add(new Route(5, destinations));
        destinations.clear();


        destinations.put("a-b", "Юбилейный микрорайон — улица Димитрова");
        destinations.put("b-a", "Улица Димитрова — Юбилейный микрорайон");
        destinations.put("a-d", "Юбилейный микрорайон — Западное депо");
        destinations.put("b-d", "Улица Димитрова — Западное депо");
        destinations.put("d-a", "Западное депо — Юбилейный микрорайон");
        destinations.put("d-b", "Западное депо — улица Димитрова");
        destinations.put("d1-b", "Западное депо — улица Димитрова (через кооп. рынок)");
        routeList.add(new Route(6, destinations));
        destinations.clear();


        destinations.put("a-b", "Западное депо — улица Димитрова");
        destinations.put("b-a", "Улица Димитрова — Западное депо");
        destinations.put("b-d", "Улица Димитрова — в депо");
        destinations.put("d-b", "Из депо - улица Димитрова");
        destinations.put("a-e", "Западное депо — улица Димитрова (по 6 м-ту)");
        destinations.put("a-k", "Западное депо — улица Декабристов");
        destinations.put("c-k", "Улица Индустриальная — улица Декабристов");
        destinations.put("k-a", "Улица Декабристов — Западное депо");
        destinations.put("d-c", "Западное депо — улица Индустриальная");
        destinations.put("e-d", "Улица Димитрова — в депо (по ул. Титаровской)");
        destinations.put("k-d", "Улица Декабристов — в депо");
        routeList.add(new Route(7, destinations));
        destinations.clear();


        destinations.put("a-b", "Улица П.Метальникова — хладокомбинат");
        destinations.put("a-b1", "Улица П.Метальникова — Комсомольский");
        destinations.put("b-a", "Хладокомбинат — улица П.Метальникова");
        destinations.put("b-a1", "Хладокомбинат — Комсомольский микрорайон");
        destinations.put("a-d", "Улица П.Метальникова — Восточное депо");
        destinations.put("a1-d", "Комсомольский микрорайон — Восточное депо");
        destinations.put("b-d", "Хладокомбинат — Восточное депо");
        destinations.put("b-d1", "Улица П.Метальникова — Восточное депо (по ул. Садовой)");
        destinations.put("d-a", "Восточное депо — улица П.Метальникова");
        destinations.put("d-b", "Восточное депо — хладокомбинат");
        destinations.put("d1-b", "Восточное депо — улица П.Метальникова (по ул. Садовой)");
        destinations.put("d1-b_12345", "Восточное депо — хладокомбинат (через мясокомбинат)");
        routeList.add(new Route(8, destinations));
        destinations.clear();



        destinations.put("a-b", "Улица Е.Бершанской — хладокомбинат");
        destinations.put("b-a", "Хладокомбинат — улица Е.Бершанской");
        destinations.put("a-d", "Улица Е.Бершанской — Восточное депо");
        destinations.put("b-d", "Хладокомбинат — Восточное депо");
        destinations.put("d-a", "Восточное депо — улица Е.Бершанской");
        destinations.put("d-b", "Восточное депо — хладокомбинат");
        routeList.add(new Route(9, destinations));
        destinations.clear();


        destinations.put("a-b", "Комсомольский микрорайон — хладокомбинат");
        destinations.put("b-a", "Хладокомбинат — Комсомольский микрорайон");
        destinations.put("a-d", "Комсомольский микрорайон — Восточное депо");
        destinations.put("b-d", "Хладокомбинат — Восточное депо");
        destinations.put("d-a", "Восточное депо — Комсомольский микрорайон");
        destinations.put("d-b", "Восточное депо — хладокомбинат");
        routeList.add(new Route(10, destinations));
        destinations.clear();

        destinations.put("a-b", "Юбилейный микрорайон — ж/д вокзал \"Краснодар-I\"");
        destinations.put("a1-b2", "Западное депо — улица Декабристов");
        destinations.put("b-a", "Ж/д вокзал \"Краснодар-I\" — Юбилейный микрорайон");
        destinations.put("b1-a1", "Улица Декабристов — Западное депо");
        destinations.put("a1-e", "Западное депо — улица Димитрова (по 6 м-ту)");
        destinations.put("a1-k", "Западное депо — Юбилейный микрорайон");
        destinations.put("a1-s", "Западное депо — улица Индустриальная");
        destinations.put("c-a1", "Улица Индустриальная — Западное депо");
        destinations.put("c1-e", "Улица Декабристов — улица Димитрова (по 7 м-ту)");
        destinations.put("e-c1", "Улица Димитрова — улица Декабристов");
        destinations.put("f-a1", "Юбилейный микрорайон — Западное депо");
        destinations.put("a1-e_12345", "Западное депо — улица Димитрова (по 7 м-ту)");
        destinations.put("e-f", "Улица Димитрова — Юбилейный микрорайон (по 7 м-ту)");
        destinations.put("b-d", "Ж/д вокзал \"Краснодар-I\" — Западное депо");
        destinations.put("d-b", "Западное депо — ж/д вокзал \"Краснодар-I\"");
        routeList.add(new Route(11, destinations));
        destinations.clear();


        destinations.put("a-b", "");
        destinations.put("b-a", "");
        destinations.put("a-d", "");
        destinations.put("b-d", "");
        destinations.put("d-a", "");
        destinations.put("d-b", "");

    }



}
