package com.example.finance.permissionmanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/11/23.
 */

public class GsonUtils {
    private static Gson mGson = new Gson();


    public static String castObjectJson(Object object) {
        return mGson.toJson(object);
    }

    public static <T> T castJsonObject(String json, Class<T> type) {
        return mGson.fromJson(json, type);
    }

    public static <T> T castJsonObjectList(String json, Class<T> t) {
        Type type = new TypeToken<T>() {}.getType();
        return mGson.fromJson(json, type);
    }
}
