package com.kangyonggan.app.util;

import java.io.*;

/**
 * 文件读写工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class FileUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private FileUtil() {

    }

    /**
     * 写文本文件到本地服务器
     *
     * @param filePath    文件的绝对路径
     * @param fileContent 文件内容
     * @throws Exception 可能发生的异常
     */
    public static void writeTextToFile(String filePath, String fileContent) throws Exception {
        BufferedWriter writer = null;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(fileContent);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件的绝对路径
     * @return 返回文件内容
     * @throws Exception 可能发生的异常
     */
    public static String readFile(String filePath) throws Exception {
        BufferedReader reader = null;
        try {
            File file = new File(filePath);

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件的绝对路径
     * @return 如果存在返回true，否则返回false
     */
    public static boolean exists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 删除文件
     *
     * @param filePath 文件的绝对路径
     */
    public static void delete(String filePath) {
        new File(filePath).deleteOnExit();
    }
}
