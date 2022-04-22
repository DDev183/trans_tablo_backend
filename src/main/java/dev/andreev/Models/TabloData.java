package dev.andreev.Models;

import lombok.Data;

import java.util.Map;

@Data
public class TabloData {


    private int stopNum;
    private Map<Integer, ScheduleCase> scheduleCase;



}
