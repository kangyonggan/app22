package com.kangyonggan.app.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;

/**
 * ftp工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class FtpUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private FtpUtil() {

    }

    /**
     * 登录ftp服务器
     *
     * @param host       主机
     * @param username   用户名
     * @param password   密码
     * @param uploadPath 上传路径
     * @return 返回客户端连接
     * @throws Exception 可能会发生的异常
     */
    private static FTPClient connect(String host, String username, String password, String uploadPath) throws Exception {
        FTPClient ftp = new FTPClient();
        int reply;
        ftp.connect(host, 23);
        ftp.login(username, password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return null;
        }
        ftp.changeWorkingDirectory(uploadPath);
        return ftp;
    }

    /**
     * 向ftp服务器推送文件
     *
     * @param host       主机
     * @param username   用户名
     * @param password   密码
     * @param uploadPath 上传路径
     * @param filePath   待上传的本地文件路径
     * @return 上传成功返回true，否则返回false
     * @throws Exception 可能会发生的异常
     */
    public static boolean push(String host, String username, String password, String uploadPath, String filePath) throws Exception {
        FTPClient ftp = null;
        FileInputStream in = null;
        try {
            ftp = connect(host, username, password, uploadPath);
            File file = new File(filePath);
            in = new FileInputStream(file);
            return ftp.storeFile(file.getName(), in);
        } catch (Exception e) {
            throw e;
        } finally {
            if (ftp != null) {
                ftp.disconnect();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
