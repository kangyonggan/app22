package com.kangyonggan.app.util;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class XmlUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private XmlUtil() {

    }

    /**
     * xml格式化
     *
     * @param data xml数据
     * @return 返回格式化后的xml
     * @throws Exception 可能会发生的异常
     */
    public static String format(String data) throws Exception {
        SAXReader reader = new SAXReader();
        StringReader in = new StringReader(data);

        XMLWriter writer = null;
        try {
            Document doc = reader.read(in);

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            format.setIndentSize(4);
            format.setExpandEmptyElements(true);
            StringWriter out = new StringWriter();
            writer = new XMLWriter(out, format);

            writer.write(doc);
            writer.flush();

            return out.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
