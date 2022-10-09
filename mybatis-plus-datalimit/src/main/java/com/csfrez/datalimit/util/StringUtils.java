package com.csfrez.datalimit.util;

public class StringUtils {

    public static String getClassPath(String string) {
        int index = string.lastIndexOf(".");
        return index > 0 ? string.substring(0, index) : null;
    }

}
