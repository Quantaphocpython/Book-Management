package com.devteria.postservice.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class DateTimeFormatter {
    // áp dụng Map/Dictionary-Based Strategy design pattern
    Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>();

    public DateTimeFormatter() {
        strategyMap.put(60L, this::formatInSeconds);
        strategyMap.put(3600L, this::formatInMinutes);
        strategyMap.put(86400L, this::formatInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDates);
    }

    public String format(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        return strategyMap.entrySet().stream()
                .filter(longFunctionEntry -> elapseSeconds < longFunctionEntry.getKey())
                .findFirst()
                .get()
                .getValue()
                .apply(instant);
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

    private String formatInDates(Instant instant) {
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;
        return localDateTime.format(dateTimeFormatter);
    }
}
