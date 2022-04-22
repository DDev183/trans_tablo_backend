package dev.andreev.Models;


import lombok.Data;

@Data
public class ScheduleCase {

    private String nameRus;
    private String nameEng;
    private int routeNumber;
    private int waitingTime;

}
