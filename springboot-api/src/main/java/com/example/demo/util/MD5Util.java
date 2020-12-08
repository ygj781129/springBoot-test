package com.example.demo.util;

import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fb on 2020/7/14
 */
public class MD5Util {
        /**
         * 获取该输入流的MD5值
         */
        public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
                StringBuffer md5 = new StringBuffer();
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] dataBytes = new byte[1024];

                int nread = 0;
                while ((nread = is.read(dataBytes)) != -1) {
                        md.update(dataBytes, 0, nread);
                };
                byte[] mdbytes = md.digest();

                // convert the byte to hex format
                for (int i = 0; i < mdbytes.length; i++) {
                        md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                return md5.toString();
        }




        //盐，用于混交md5
        private static String salt = "";

        /**
         * springboot生成md5
         * @param str
         * @return
         */
        public static String getMD52(String str) {
                String base = str + "/" + salt;
                String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
                return md5;
        }


        /**
         * 比较一般的工具
         * @param pwd
         * @return
         */
        public final static String MD5(String pwd) {
                //用于加密的字符
                char md5String[] =  {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
                try {
                        //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
                        byte[] btInput = pwd.getBytes();

                        //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
                        MessageDigest mdInst = MessageDigest.getInstance("MD5");

                        //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
                        mdInst.update(btInput);

                        // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
                        byte[] md = mdInst.digest();

                        // 把密文转换成十六进制的字符串形式
                        int j = md.length;
                        char str[] = new char[j * 2];
                        int k = 0;
                        for (int i = 0; i < j; i++) {   //  i = 0
                                byte byte0 = md[i];  //95
                                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                                str[k++] = md5String[byte0 & 0xf];   //   F
                        }

                        //返回经过加密后的字符串
                        return new String(str);

                } catch (Exception e) {
                        return null;
                }
        }


        /**
         * 比较全的传统的MD5加密方式
         * @param pwd 密码
         * @param isUpper 大小写
         * @param bit 多少位
         * @return
         */
        public static String getMD53(String pwd, boolean isUpper, Integer bit) {
                String md5 = new String();
                try {
                        // 创建加密对象
                        MessageDigest md = MessageDigest.getInstance("md5");
                        if (bit == 64) {
                                BASE64Encoder bw = new BASE64Encoder();
                                String bsB64 = bw.encode(md.digest(pwd.getBytes("utf-8")));
                                md5 = bsB64;
                        } else {
                                // 计算MD5函数
                                md.update(pwd.getBytes());
                                byte b[] = md.digest();
                                int i;
                                StringBuffer sb = new StringBuffer("");
                                for (int offset = 0; offset < b.length; offset++) {
                                        i = b[offset];
                                        if (i < 0)
                                                i += 256;
                                        if (i < 16)
                                                sb.append("0");
                                        sb.append(Integer.toHexString(i));
                                }
                                md5 = sb.toString();
                                if(bit == 16) {
                                        //截取32位md5为16位
                                        String md16 = md5.substring(8, 24).toString();
                                        md5 = md16;
                                        if (isUpper)
                                                md5 = md5.toUpperCase();
                                        return md5;
                                }
                        }
                        //转换成大写
                        if (isUpper)
                                md5 = md5.toUpperCase();
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("md5加密抛出异常！");
                }

                return md5;
        }

}
