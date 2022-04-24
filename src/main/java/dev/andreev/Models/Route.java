package dev.andreev.Models;

import lombok.Data;

import java.util.Map;

@Data
public class Route {

    private int number;
    private Map<String, String> destinationS;

    public Route(int i, Map<String, String> destinations) {
    }
}
