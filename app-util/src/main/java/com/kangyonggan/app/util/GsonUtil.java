package com.kangyonggan.app.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.StringWriter;

/**
 * JSON工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class GsonUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private GsonUtil() {

    }

    /**
     * 格式化json
     *
     * @param data json数据
     * @return 返回格式化之后的json
     * @throws Exception 可能会发生的异常
     */
    public static String format(String data) throws Exception {
        Gson gson = new Gson();
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = gson.newJsonWriter(stringWriter);
        writer.setIndent("\t");

        JsonElement jsonElement = new JsonParser().parse(data);
        gson.toJson(jsonElement, writer);

        return stringWriter.toString();
    }
}
