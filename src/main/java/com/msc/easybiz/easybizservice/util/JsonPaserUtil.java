package com.msc.easybiz.easybizservice.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JsonPaserUtil {

    private Gson gson = new Gson();

    public String toJsonStr(Object obj){
        return gson.toJson(obj);
    }

    public <T> T fromJsonStr(String objStr, Class<T> obj){
        return gson.fromJson(objStr, obj);
    }
}
