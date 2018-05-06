package com.kangyonggan.app.util;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import lombok.Data;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * CSS/JS压缩工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class CompressorUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private CompressorUtil() {

    }

    /**
     * 压缩CSS
     *
     * @param data CSS字符串
     * @return 返回压缩后的数据
     * @throws Exception 可能发生IO异常
     */
    public static String compressCSS(String data) throws Exception {
        if (StringUtil.isEmpty(data)) {
            return StringUtil.EMPTY;
        }
        StringReader reader = new StringReader(data);

        CssCompressor cssCompressor = new CssCompressor(reader);
        StringWriter writer = new StringWriter();
        cssCompressor.compress(writer, -1);

        return writer.toString();
    }

    /**
     * 压缩JS
     *
     * @param data js字符串
     * @return 返回压缩后的数据
     * @throws Exception 可能发生IO异常
     */
    public static Map<String, String> compressJS(String data) throws Exception {
        Map<String, String> resultMap = new HashMap(3);

        if (StringUtil.isEmpty(data)) {
            return resultMap;
        }
        StringReader reader = new StringReader(data);
        MyErrorReporter errorReporter = new MyErrorReporter(data);

        JavaScriptCompressor jsCompressor = new JavaScriptCompressor(reader, errorReporter);
        StringWriter writer = new StringWriter();
        jsCompressor.compress(writer, -1, true, true, false, false);

        resultMap.put("result", writer.toString());
        resultMap.put("warningMsg", errorReporter.getWarningMsg());
        resultMap.put("errorMsg", errorReporter.getErrorMsg());

        return resultMap;
    }

    /**
     * 错误报告
     */
    @Data
    private static class MyErrorReporter implements ErrorReporter {

        /**
         * 数据
         */
        private String data;

        /**
         * 错误信息
         */
        private String errorMsg;

        /**
         * 警告信息
         */
        private String warningMsg;

        public MyErrorReporter(String data) {
            this.data = data;
        }

        @Override
        public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                warningMsg = message;
            } else {
                warningMsg = line + ':' + lineOffset + ':' + message;
            }

        }

        @Override
        public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                errorMsg = message;
            } else {
                errorMsg = line + ':' + lineOffset + ':' + message;
            }

        }

        @Override
        public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
            this.error(message, sourceName, line, lineSource, lineOffset);
            return new EvaluatorException(message);
        }

    }


}
