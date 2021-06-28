package com.ryfa.MVP.general;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCode {

    public static String parse(String str) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(str);
        String code = "";
        while (m.find()) {
            code = code + m.group(0);
        }
        return code;
    }
}
