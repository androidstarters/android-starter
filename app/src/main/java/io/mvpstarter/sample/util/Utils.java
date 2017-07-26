package io.mvpstarter.sample.util;
/*
 * Created by shivam on 26/7/17.
 */

public class Utils {

    public static String toCameCase(String str) {
        return String.format("%s%s",
                str.substring(0, 1).toUpperCase(), str.substring(1).toLowerCase());
    }
}
