package com.jpm.backend;

import org.json.JSONArray;
public class Utils {
    public static JSONArray parseJSON(String jsonString)
    {
        int startIdx = jsonString.indexOf("[");
        int endIdx = jsonString.indexOf("]");
        return new JSONArray(jsonString.substring(startIdx,endIdx+1));
    }

}
