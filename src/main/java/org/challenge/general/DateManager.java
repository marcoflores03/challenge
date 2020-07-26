package org.challenge.general;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {
    public static String getDateId() {
        final DateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmss");
        final Date date = new Date();
        return dateFormat.format(date);
    }
}