package com.revendedor.ticketservice.util.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ConvertFormat {
    public static Instant localDateToInstant(LocalDate date){
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
