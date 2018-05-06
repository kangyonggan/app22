package com.kangyonggan.app.util;

import lombok.Data;

/**
 * IP信息
 *
 * @author kangyonggan
 * @since 5/4/18
 */
@Data
public class IpInfo {

    /**
     * 响应码
     */
    private String respCo;

    /**
     * 响应信息
     */
    private String respMsg;

    /**
     * IP
     */
    private String ip;

    /**
     * 国家名称
     */
    private String country;

    /**
     * 国家代码
     */
    private String countryId;

    /**
     * 地区名称
     */
    private String area;

    /**
     * 地区代码
     */
    private String areaId;

    /**
     * 省名称
     */
    private String region;

    /**
     * 省代码
     */
    private String regionId;

    /**
     * 市名称
     */
    private String city;

    /**
     * 市代码
     */
    private String cityId;

    /**
     * 市名称
     */
    private String county;

    /**
     * 市代码
     */
    private String countyId;

    /**
     * ISP服务商名称
     */
    private String isp;

    /**
     * ISP服务商代码
     */
    private String ispId;

}
