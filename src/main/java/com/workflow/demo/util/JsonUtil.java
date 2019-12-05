package com.workflow.demo.util;


import net.sf.json.JSONObject;

public class JsonUtil {
    public static JSONObject Success(Object obj) {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("result", true);
        jsonResult.put("data", obj);
        return jsonResult;
    }

    public static JSONObject Fail(int errorCode, String errorMsg) {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("result", false);
        jsonResult.put("errorCode", errorCode);
        jsonResult.put("errorMsg", errorMsg);
        return jsonResult;
    }
}
