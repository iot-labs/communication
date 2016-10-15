package org.iotlabs.models;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

public class BaseModel implements Jsonify {

    public static final int VALUE_UNKNOWN = -1;

    // internal Gson object
    private static final Gson mGson = new Gson();

    @Override
    public String toJsonString() {
        return mGson.toJson(this);
    }
    @SerializedName("created_at")
    private long createTimeStamp;

    public BaseModel() {
        createTimeStamp = System.currentTimeMillis();
    }

    public static <T extends BaseModel>T fromJsonString(String json, Class<T> tClass)
            throws JsonSyntaxException {
        return mGson.fromJson(json, tClass);
    }

    public static <T extends BaseModel>T fromJsonString(JsonReader jsonReader, Class<T> tClass)
            throws JsonSyntaxException {
        return mGson.fromJson(jsonReader, tClass);
    }
}
