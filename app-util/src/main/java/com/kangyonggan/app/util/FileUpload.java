package com.kangyonggan.app.util;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class FileUpload {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private FileUpload() {

    }

    /**
     * 上传文件
     *
     * @param fileDir   文件目录
     * @param uploadDir 上传目录
     * @param file      文件
     * @param prefix    前缀
     * @return 返回相对路径
     * @throws FileUploadException 可能会抛出的异常
     */
    public static String upload(String fileDir, String uploadDir, MultipartFile file, String prefix) throws FileUploadException {
        String fileName = "";
        if (file.getSize() != 0) {
            try {
                fileName = uploadDir + extractFilePath(file, prefix);
                File desc = getAbsolutePath(fileDir + fileName);
                file.transferTo(desc);
            } catch (Exception e) {
                throw new FileUploadException("File Upload Exception", e);
            }
        }
        return fileName;
    }

    private static File getAbsolutePath(String filename) throws IOException {
        File desc = new File(filename);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    private static String extractFilePath(MultipartFile file, String prefix) {
        String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
        return extractFilePathByExtension(fileExt, prefix);
    }

    private static String extractFilePathByExtension(String extension, String prefix) {
        StringBuilder tempPath = new StringBuilder();
        tempPath.append(prefix).append(UUID.randomUUID().toString().replaceAll("-", ""));
        tempPath.append(".").append(extension);

        return tempPath.toString();
    }
}
