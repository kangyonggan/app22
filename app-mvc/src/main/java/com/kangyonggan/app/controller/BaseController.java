package com.kangyonggan.app.controller;

import com.kangyonggan.app.bean.Params;
import com.kangyonggan.app.bean.Query;
import com.kangyonggan.app.interceptor.HandlerInterceptor;
import com.kangyonggan.app.util.StringUtil;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * 基础controller
 *
 * @author kangyonggan
 * @since 5/4/18
 */
@Log4j2
public class BaseController {

    /**
     * 工作台
     */
    protected static final String DASHBOARD = "dashboard";

    /**
     * 根路径
     */
    private String pathRoot;

    /**
     * list
     */
    private static final String LIST = "/list";

    /**
     * index
     */
    private static final String INDEX = "/index";

    /**
     * form
     */
    private static final String FORM = "/form";

    /**
     * form-modal
     */
    private static final String FORM_MODAL = "/form-modal";

    /**
     * detail
     */
    private static final String DETAIL = "/detail";

    /**
     * detail-modal
     */
    private static final String DETAIL_MODAL = "/detail-modal";

    /**
     * 在构造方法中获取根路径
     */
    public BaseController() {
        String className = getClass().getSimpleName();
        String[] arr = StringUtil.camelToArray(className);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length - 1; i++) {
            if (i != 0) {
                sb.append("/");
            }
            sb.append(arr[i]);
        }
        pathRoot = sb.toString();

        if (!pathRoot.startsWith(DASHBOARD)) {
            pathRoot = "web/" + pathRoot;
        }
    }

    /**
     * 获取请求参数
     *
     * @return 返回请求参数
     */
    protected Params getRequestParams() {
        Params params = new Params();

        // 分页相关参数
        params.setPageSize(getIntegerParam("limit", 10));
        int offset = getIntegerParam("offset", 0);
        params.setPageNum(offset / params.getPageSize() + 1);

        String sort = getStringParam("sort");
        params.setSort(StringUtil.camelToUnderLine(sort));
        params.setOrder(getStringParam("order", "asc"));

        // 其他查询条件
        params.setQuery(getQuery());

        return params;
    }

    /**
     * 获取查询条件
     *
     * @return 返回查询条件
     */
    protected Query getQuery() {
        Query query = new Query();
        Map<String, String[]> parameterMap = HandlerInterceptor.getRequest().getParameterMap();
        for (String key : parameterMap.keySet()) {
            String[] value = parameterMap.get(key);
            if (value != null && value.length == 1) {
                query.put(key, value[0]);
            } else {
                query.put(key, value);
            }
        }

        return query;
    }

    /**
     * 获取String类型的请求参数
     *
     * @param name 参数名
     * @return 返回参数值
     */
    protected String getStringParam(String name) {
        return HandlerInterceptor.getRequest().getParameter(name);
    }

    /**
     * 获取String类型的请求参数, 带默认值
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 返回参数值
     */
    protected String getStringParam(String name, String defaultValue) {
        String param = HandlerInterceptor.getRequest().getParameter(name);
        return param == null ? defaultValue : param;
    }

    /**
     * 获取int类型的请求参数
     *
     * @param name 参数名
     * @return 返回int型的参数值
     */
    protected int getIntegerParam(String name) {
        return Integer.parseInt(HandlerInterceptor.getRequest().getParameter(name));
    }

    /**
     * 获取int类型的请求参数, 带默认值
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 返回int型的参数值
     */
    protected int getIntegerParam(String name, int defaultValue) {
        try {
            return Integer.parseInt(HandlerInterceptor.getRequest().getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取根路径
     *
     * @return 返回根路径
     */
    protected String getPathRoot() {
        return pathRoot;
    }

    /**
     * 获取index路径
     *
     * @return 返回index路径
     */
    protected String getPathIndex() {
        return pathRoot + INDEX;
    }

    /**
     * 获取list路径
     *
     * @return 返回list路径
     */
    protected String getPathList() {
        return pathRoot + LIST;
    }

    /**
     * 获取form路径
     *
     * @return 返回form路径
     */
    protected String getPathForm() {
        return pathRoot + FORM;
    }

    /**
     * 获取detail路径
     *
     * @return 返回detail路径
     */
    protected String getPathDetail() {
        return pathRoot + DETAIL;
    }

    /**
     * 获取form-modal路径
     *
     * @return 返回form-modal路径
     */
    protected String getPathFormModal() {
        return pathRoot + FORM_MODAL;
    }

    /**
     * 获取detail-modal路径
     *
     * @return 返回detail-modal路径
     */
    protected String getPathDetailModal() {
        return pathRoot + DETAIL_MODAL;
    }
}
