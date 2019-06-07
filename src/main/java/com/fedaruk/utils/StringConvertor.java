package com.fedaruk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringConvertor {

    public static String inputStreamToString(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return builder.toString();
    }
}
