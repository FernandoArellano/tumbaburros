package com.example.tumbaburros.exercizes.durationbetween;

import java.time.*;

/*
    Problem: Calculate hours between two zoned timestamps.
 */

public class TimeCalc {

    public static long hoursBetween(ZonedDateTime start, ZonedDateTime end){
        return Duration.between(start, end).toHours();
    }

    public static void main(String[] args) {
        ZonedDateTime starting = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        System.out.println(starting);
        ZonedDateTime ending = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).plusHours(9);
        System.out.println(ending);
        System.out.println(hoursBetween(starting,ending));
    }
}
