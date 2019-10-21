package br.com.app.reciclamais.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    private static final DateTimeFormatter formatterDataTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatterDate  = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String getDate(String data) {
        LocalDate formatDateTime = LocalDate.parse(data, formatterDataTime);
        return formatterDate.format(formatDateTime);
    }

    public static String getDate(LocalDateTime data) {
        return formatterDate.format(data);
    }

    public static String  getDateTime(LocalDateTime data){
        return formatterDataTime.format(data);
    }


}
