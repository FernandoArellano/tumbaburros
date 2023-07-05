package com.example.tumbaburros.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class JodaDate {

    public static void currentDate(){
        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalTime time = LocalTime.now();
        System.out.println(time);
    }

    public static void currentDayOfWeek(){
        System.out.println(LocalDate.now().getDayOfWeek());
    }

    public static void currentMonth(){
        System.out.println(LocalDate.now().getMonth());
    }

    public static void currentDayInMonth(){
        System.out.println(LocalDate.now().getDayOfMonth());
    }

    public static void currentHOurOfDay(){
        System.out.println(LocalTime.now().getHour());
    }

    public static void currentMinute(){
        System.out.println(LocalTime.now().getMinute());
    }

    public static void currentSecond(){
        System.out.println(LocalTime.now().getSecond());
    }

    public static void localDateTimeComplete(){
        System.out.println(LocalDateTime.now());
    }

    public static Period getPeriodFrom2Date(LocalDate d1, LocalDate d2){
        Period period = Period.between(d1,d2);
        return period;
    }

    public static void main(String[] args) {
        currentDate();
        currentDayOfWeek();
        currentMonth();
        currentDayInMonth();

        currentHOurOfDay();
        currentMinute();
        currentSecond();

        localDateTimeComplete();

        System.out.println(getPeriodFrom2Date(LocalDate.now(), LocalDate.of(1986,01,24)));
    }
}
