package kai.simulator.utils;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson GSON = new Gson();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

}
