package com.devteria.postservice.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeFormatter {
//    Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>(
//            60,
//    );

    public String format(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        if(elapseSeconds < 60) {
            return elapseSeconds + " seconds";
        } else if(elapseSeconds < 3600) {
            long elapseMinutes = ChronoUnit.MINUTES.between(instant, Instant.now());
            return elapseMinutes + " minutes";
        } else if (elapseSeconds < 86400) {
            long elapseHours = ChronoUnit.HOURS.between(instant, Instant.now());
            return elapseHours + " hours";
        }

        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;

        return localDateTime.format(dateTimeFormatter);
    }

    private String formatInSeconds(Instant instant) {
        return ChronoUnit.SECONDS.between(instant, Instant.now()) + " seconds";
    }

    private String formatInMinutes(Instant instant) {
        return ChronoUnit.MINUTES.between(instant, Instant.now()) + " minutes";
    }

    private String formatInHours(Instant instant) {
        return ChronoUnit.HOURS.between(instant, Instant.now()) + " hours";
    }
}
