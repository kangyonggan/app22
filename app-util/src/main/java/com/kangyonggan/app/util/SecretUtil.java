package com.kangyonggan.app.util;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 加密解密工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class SecretUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private SecretUtil() {

    }

    /**
     * 默认字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 默认不是调试模式
     */
    private static final boolean IS_DEBUG = false;

    /**
     * 生成密钥工厂算法
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 数字签名算法
     */
    private static final String SIGN_ALGORITHM = "SHA1WithRSA";

    /**
     * 加密算法
     */
    private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 获取对方公钥
     *
     * @param publicKeyPath 公钥绝对路径
     * @return 返回对方公钥
     * @throws Exception 可能会抛出的异常
     */
    public static PublicKey getPublicKey(String publicKeyPath) throws Exception {
        return getPublicKey(publicKeyPath, IS_DEBUG);
    }

    /**
     * 获取对方公钥
     *
     * @param publicKeyPath 公钥绝对路径
     * @param isDebug       是否调试模式
     * @return 返回对方公钥
     * @throws Exception 可能会抛出的异常
     */
    public static PublicKey getPublicKey(String publicKeyPath, boolean isDebug) throws Exception {
        if (isDebug) {
            return null;
        }

        InputStream in = null;
        try {
            in = new FileInputStream(publicKeyPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String readLine;
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(sb.toString()));
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(pubX509);
            return publicKey;
        } catch (Exception e) {
            throw new Exception("加载对方公钥异常[" + publicKeyPath + "]", e);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 获取己方私钥
     *
     * @param privateKeyPath 私钥绝对路径
     * @return 返回己方公钥
     * @throws Exception 可能会抛出的异常
     */
    public static PrivateKey getPrivateKey(String privateKeyPath) throws Exception {
        return getPrivateKey(privateKeyPath, IS_DEBUG);
    }

    /**
     * 获取己方私钥
     *
     * @param privateKeyPath 私钥绝对路径
     * @param isDebug        是否调试模式
     * @return 返回己方公钥
     * @throws Exception 可能会抛出的异常
     */
    public static PrivateKey getPrivateKey(String privateKeyPath, boolean isDebug) throws Exception {
        if (isDebug) {
            return null;
        }

        InputStream in = null;
        try {
            in = new FileInputStream(privateKeyPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String readLine;
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(sb.toString()));
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);

            return privateKey;
        } catch (Exception e) {
            throw new Exception("加载己方私钥异常[" + privateKeyPath + "]", e);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 签名
     *
     * @param plain      明文
     * @param privateKey 己方私钥
     * @return 返回签名后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] sign(String plain, PrivateKey privateKey) throws Exception {
        return sign(plain, privateKey, DEFAULT_CHARSET, IS_DEBUG);
    }

    /**
     * 签名
     *
     * @param plain      明文
     * @param privateKey 己方私钥
     * @param charset    字符集
     * @return 返回签名后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] sign(String plain, PrivateKey privateKey, String charset) throws Exception {
        return sign(plain, privateKey, charset, IS_DEBUG);
    }

    /**
     * 签名
     *
     * @param plain      明文
     * @param privateKey 己方私钥
     * @param isDebug    是否调试模式
     * @return 返回签名后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] sign(String plain, PrivateKey privateKey, boolean isDebug) throws Exception {
        return sign(plain, privateKey, DEFAULT_CHARSET, isDebug);
    }

    /**
     * 签名
     *
     * @param plain      明文
     * @param privateKey 己方私钥
     * @param charset    字符集
     * @param isDebug    是否调试模式
     * @return 返回签名后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] sign(String plain, PrivateKey privateKey, String charset, boolean isDebug) throws Exception {
        if (isDebug) {
            return plain.getBytes(charset);
        }
        byte[] plainBytes = plain.getBytes(charset);
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(plainBytes);
        byte[] signBytes = signature.sign();
        return signBytes;
    }

    /**
     * 验签
     *
     * @param plain     明文
     * @param signBytes 签名数据
     * @param publicKey 对方公钥
     * @return 验签成功返回true，否则返回false
     * @throws Exception 可能会发生的异常
     */
    public static boolean isValid(String plain, byte[] signBytes, PublicKey publicKey) throws Exception {
        return isValid(plain, signBytes, publicKey, DEFAULT_CHARSET, IS_DEBUG);
    }

    /**
     * 验签
     *
     * @param plain     明文
     * @param signBytes 签名数据
     * @param publicKey 对方公钥
     * @param charset   字符集
     * @return 验签成功返回true，否则返回false
     * @throws Exception 可能会发生的异常
     */
    public static boolean isValid(String plain, byte[] signBytes, PublicKey publicKey, String charset) throws Exception {
        return isValid(plain, signBytes, publicKey, charset, IS_DEBUG);
    }

    /**
     * 验签
     *
     * @param plain     明文
     * @param signBytes 签名数据
     * @param publicKey 对方公钥
     * @param isDebug   是否调试模式
     * @return 验签成功返回true，否则返回false
     * @throws Exception 可能会发生的异常
     */
    public static boolean isValid(String plain, byte[] signBytes, PublicKey publicKey, boolean isDebug) throws Exception {
        return isValid(plain, signBytes, publicKey, DEFAULT_CHARSET, isDebug);
    }

    /**
     * 验签
     *
     * @param plain     明文
     * @param signBytes 签名数据
     * @param publicKey 对方公钥
     * @param charset   字符集
     * @param isDebug   是否调试模式
     * @return 验签成功返回true，否则返回false
     * @throws Exception 可能会发生的异常
     */
    public static boolean isValid(String plain, byte[] signBytes, PublicKey publicKey, String charset, boolean isDebug) throws Exception {
        if (isDebug) {
            return true;
        }
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(plain.getBytes(charset));
        return signature.verify(signBytes);
    }

    /**
     * 加密
     *
     * @param plain     明文
     * @param publicKey 对方公钥
     * @return 加密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] encrypt(String plain, PublicKey publicKey) throws Exception {
        return encrypt(plain, publicKey, DEFAULT_CHARSET, IS_DEBUG);
    }

    /**
     * 加密
     *
     * @param plain     明文
     * @param publicKey 对方公钥
     * @param charset   字符集
     * @return 加密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] encrypt(String plain, PublicKey publicKey, String charset) throws Exception {
        return encrypt(plain, publicKey, charset, IS_DEBUG);
    }

    /**
     * 加密
     *
     * @param plain     明文
     * @param publicKey 对方公钥
     * @param isDebug   是否调试模式
     * @return 加密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] encrypt(String plain, PublicKey publicKey, boolean isDebug) throws Exception {
        return encrypt(plain, publicKey, DEFAULT_CHARSET, isDebug);
    }

    /**
     * 加密
     *
     * @param plain     明文
     * @param publicKey 对方公钥
     * @param charset   字符集
     * @param isDebug   是否调试模式
     * @return 加密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static byte[] encrypt(String plain, PublicKey publicKey, String charset, boolean isDebug) throws Exception {
        if (isDebug) {
            return plain.getBytes(charset);
        }

        byte[] plainBytes = plain.getBytes(charset);
        int keyByteSize = 2048 / 8;
        int encryptBlockSize = keyByteSize - 11;
        int nBlock = plainBytes.length / encryptBlockSize;
        if ((plainBytes.length % encryptBlockSize) != 0) {
            nBlock += 1;
        }

        ByteArrayOutputStream out = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            out = new ByteArrayOutputStream(nBlock * keyByteSize);
            for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
                int inputLen = plainBytes.length - offset;
                if (inputLen > encryptBlockSize) {
                    inputLen = encryptBlockSize;
                }
                byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
                out.write(encryptedBlock);
            }

            out.flush();
            return out.toByteArray();
        } catch (Exception e) {
            throw new Exception("加密异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 解密
     *
     * @param encryptedBytes 密文
     * @param privateKey     己方私钥
     * @return 解密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static String decrypt(byte[] encryptedBytes, PrivateKey privateKey) throws Exception {
        return decrypt(encryptedBytes, privateKey, DEFAULT_CHARSET, IS_DEBUG);
    }

    /**
     * 解密
     *
     * @param encryptedBytes 密文
     * @param privateKey     己方私钥
     * @param charset        字符集
     * @return 解密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static String decrypt(byte[] encryptedBytes, PrivateKey privateKey, String charset) throws Exception {
        return decrypt(encryptedBytes, privateKey, charset, IS_DEBUG);
    }

    /**
     * 解密
     *
     * @param encryptedBytes 密文
     * @param privateKey     己方私钥
     * @param isDebug        是否调试模式
     * @return 解密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static String decrypt(byte[] encryptedBytes, PrivateKey privateKey, boolean isDebug) throws Exception {
        return decrypt(encryptedBytes, privateKey, DEFAULT_CHARSET, isDebug);
    }

    /**
     * 解密
     *
     * @param encryptedBytes 密文
     * @param privateKey     己方私钥
     * @param charset        字符集
     * @param isDebug        是否调试模式
     * @return 解密后的字节
     * @throws Exception 可能会发生的异常
     */
    public static String decrypt(byte[] encryptedBytes, PrivateKey privateKey, String charset, boolean isDebug) throws Exception {
        if (isDebug) {
            return new String(encryptedBytes, charset);
        }

        int keyByteSize = 2048 / 8;
        int decryptBlockSize = keyByteSize - 11;
        int nBlock = encryptedBytes.length / keyByteSize;

        ByteArrayOutputStream out = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            out = new ByteArrayOutputStream(nBlock * decryptBlockSize);
            for (int offset = 0; offset < encryptedBytes.length; offset += keyByteSize) {
                int inputLen = encryptedBytes.length - offset;
                if (inputLen > keyByteSize) {
                    inputLen = keyByteSize;
                }
                byte[] decryptedBlock = cipher.doFinal(encryptedBytes, offset, inputLen);
                out.write(decryptedBlock);
            }

            out.flush();
            return new String(out.toByteArray(), charset);
        } catch (Exception e) {
            throw new Exception("解密异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
