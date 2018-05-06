package com.kangyonggan.app.bean;

import com.kangyonggan.app.constants.Resp;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public class Response extends HashMap<String, Object> implements Serializable {

    /**
     * 响应码的key
     */
    public static final String RESP_CO = "respCo";

    /**
     * 响应消息的key
     */
    public static final String RESP_MSG = "respMsg";

    /**
     * 私有化构造
     */
    private Response() {

    }

    /**
     * 获取一个响应
     *
     * @return 返回一个响应
     */
    public static Response getResponse() {
        return new Response();
    }

    /**
     * 获取一个成功的响应
     *
     * @return 返回一个成功响应
     */
    public static Response getSuccessResponse() {
        Response response = new Response();
        response.put(RESP_CO, Resp.SUCCESS.getRespCo());
        response.put(RESP_MSG, Resp.SUCCESS.getRespMsg());
        return response;
    }

    /**
     * 获取一个失败的响应
     *
     * @return 返回一个失败响应
     */
    public static Response getFailureResponse() {
        Response response = new Response();
        response.put(RESP_CO, Resp.FAILURE.getRespCo());
        response.put(RESP_MSG, Resp.FAILURE.getRespMsg());
        return response;
    }

    /**
     * 响应置为失败
     *
     * @return 返回置为失败的响应
     */
    public Response failure() {
        failure(Resp.FAILURE.getRespMsg());
        return this;
    }

    /**
     * 响应置为失败
     *
     * @param msg 失败消息
     * @return 返回置为失败的响应
     */
    public Response failure(String msg) {
        put(RESP_CO, Resp.FAILURE.getRespCo());
        put(RESP_MSG, msg);
        return this;
    }

}
