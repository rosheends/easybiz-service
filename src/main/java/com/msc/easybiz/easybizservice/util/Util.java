package com.msc.easybiz.easybizservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Util {

    @Autowired
    private JsonPaserUtil jsonPaserUtil;

    public Map<String, String> getData(String jsonStr){
        return (Map<String, String>) jsonPaserUtil.fromJsonStr(jsonStr, Map.class);
    }
}
