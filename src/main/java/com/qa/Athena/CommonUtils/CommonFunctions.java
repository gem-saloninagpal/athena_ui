package com.qa.Athena.CommonUtils;

import com.gemini.generic.utils.ProjectConfigData;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonFunctions {

    public static Map<String, Object> data_map = new HashMap<>();

    public static Object getDataMapValue(String key) {
        return data_map.get(key);
    }

    public static void setDataMapValue(String key, Object val) {
        data_map.put(key, val);
    }

    public static String getBaseURL() {
        return ProjectConfigData.getProperty("apiBaseUrl");
    }

    public static String getUrl(String endpoint) {
        return getBaseURL() + endpoint;
    }

    public static String getUrl(String endpoint, String param) {
        return getBaseURL() + endpoint + "?" + param;
    }

//    public static void main(String[] args) {
//        System.out.println(getBaseURL());
//    }
}
