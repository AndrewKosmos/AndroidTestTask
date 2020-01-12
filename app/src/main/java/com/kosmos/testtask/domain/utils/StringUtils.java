package com.kosmos.testtask.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class StringUtils {

    public static String capitalizeString(String str) {
        if (str == null || str.length() <= 0) return "";
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public static String normalizeDate(String date) {
        if (date == null || date.length() <= 0) return "";
        if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", date)) {
            if (Pattern.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d", date)) {
                try {
                    Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                    return new SimpleDateFormat("yyyy-MM-dd").format(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            else {
                return "";
            }
        }
        return date;
    }

}
