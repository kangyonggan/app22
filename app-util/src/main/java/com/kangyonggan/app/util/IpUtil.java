package com.kangyonggan.app.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class IpUtil {

    /**
     * unknown
     */
    private static final String UNKNOWN = "unknown";

    /**
     * 获取IP信息的url
     */
    private static final String URL_GET_IP_INFO = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 获取IP信息成功响应码
     */
    private static final String CODE_SUCCESS = "0";

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private IpUtil() {

    }

    /**
     * 获取请求IP
     *
     * @param request Http请求
     * @return 返回请求IP
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取IP信息
     *
     * @param ip IP地址
     * @return 返回IP信息
     */
    public static IpInfo getIpInfo(String ip) {
        IpInfo ipInfo = new IpInfo();
        ipInfo.setIp(ip);
        Class clazz = ipInfo.getClass();
        try {
            String result = HttpUtil.get(URL_GET_IP_INFO, "ip=" + ip);

            JSONObject resultJSON = JSON.parseObject(result);
            String code = resultJSON.getString("code");
            String data = resultJSON.getString("data");

            ipInfo.setRespCo(code);

            if (CODE_SUCCESS.equals(code)) {
                JSONObject dataJSON = JSON.parseObject(data);
                for (String key : dataJSON.keySet()) {
                    String fieldName = StringUtil.underLineToCamel(key);
                    Reflections.setFieldValue(ipInfo, fieldName, dataJSON.getString(key));
                }
            } else {
                ipInfo.setRespMsg(data);
            }
        } catch (Exception e) {
            ipInfo.setRespCo("-1");
            ipInfo.setRespMsg(e.getMessage());
        }
        return ipInfo;
    }

}
