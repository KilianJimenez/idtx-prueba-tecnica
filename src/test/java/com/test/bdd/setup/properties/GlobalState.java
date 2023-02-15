package com.test.bdd.setup.properties;

import java.util.HashMap;
import java.util.Map;

public class GlobalState {
    private static GlobalState instance = null;

    private Map<String, Object> data = new HashMap<>();

    public static GlobalState getInstance() {
        if (instance == null)
            instance = new GlobalState();
        return instance;
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }
}
