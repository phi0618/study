package com.iusername.base.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtils {
    private JsonUtils() {
    }

    private static Gson gson;

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
