package com.example.demo.service;

import com.example.demo.util.JsoupUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Hashtable;
import java.util.UUID;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import static org.springframework.data.redis.core.convert.Bucket.CHARSET;

/**
 * Created by fb on 2020/9/9
 */
public class ImgAddImg {
        /**
         * overlapImage
         * @description：合成二维码和图片为文件
         * @params [backPicPath, code]
         * @return void
         */
        public static final void combineCodeAndPicToFile(String backPicPath, BufferedImage code/*String fillPicPath*/) {
                try {
                        BufferedImage big = ImageIO.read(new File(backPicPath));
                        BufferedImage small = code;
        /*//合成两个文件时使用
        BufferedImage small = ImageIO.read(new File(fillPicPath));*/
                        Graphics2D g = big.createGraphics();

                        //二维码或小图在大图的左上角坐标
                        int x = (big.getWidth() - small.getWidth()) / 2;
                        // int y = (big.getHeight() - small.getHeight()) / 2;
                        int y = (big.getHeight() - small.getHeight() - 100);
                        g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
                        g.dispose();
                        //为了保证大图背景不变色，formatName必须为"png"
                        ImageIO.write(big, "png", new File("C:/Users/kevin/Desktop/combinePic.jpg"));
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * combineCodeAndPicToInputstream
         * @description：合成二维码和图片为输出流，可用于下载或直接展示
         * @date 2018/12/13
         * @params [backPicPath, code]
         * @return java.io.InputStream
         */
        public static final void combineCodeAndPicToInputstream(String backPicPath, BufferedImage code, HttpServletResponse resp) {
                try {
                        BufferedImage big = ImageIO.read(new File(backPicPath));
                        // BufferedImage small = ImageIO.read(new File(fillPicPath));
                        BufferedImage small = code;
                        Graphics2D g = big.createGraphics();

                        //二维码或小图在大图的左上角坐标
                        int x = (big.getWidth() - small.getWidth()) / 200;
                        int y = (big.getHeight() - small.getHeight() - 100);   //二维码距大图下边距100
                        g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
                        g.dispose();
                        //去掉这行设置header的代码，前端访问可以直接展示
                        resp.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("lia阿里.png","UTF-8") );
                        //为了保证大图背景不变色，formatName必须为"png"
                        ImageIO.write(big, "png", resp.getOutputStream());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        /**
         * combineCodeAndPicToBase64
         * @description：合成二维码和图片为Base64，同样可用于直接展示
         * @params [backPicPath, code]
         * @return java.lang.String
         */
        public static final String combineCodeAndPicToBase64(String backPicPath, BufferedImage code) {
                ImageOutputStream imOut = null;
                try {
                        BufferedImage big = ImageIO.read(new File(backPicPath));
                        // BufferedImage small = ImageIO.read(new File(fillPicPath));
                        BufferedImage small = code;
                        Graphics2D g = big.createGraphics();

                        //二维码或小图在大图的左上角坐标
                        int x = (big.getWidth() - small.getWidth()) / 2;
                        int y = (big.getHeight() - small.getHeight() - 100);
                        g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
                        g.dispose();
                        //为了保证大图背景不变色，formatName必须为"png"

                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        imOut = ImageIO.createImageOutputStream(bs);
                        ImageIO.write(big, "png", imOut);
                        InputStream in = new ByteArrayInputStream(bs.toByteArray());

                        byte[] bytes = new byte[in.available()];
                        in.read(bytes);
                        String base64 = Base64.getEncoder().encodeToString(bytes);
                        System.out.println(base64);

                        return "data:image/jpeg;base64," + base64;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * createImage
         * @description：生成二维码
         * @params [content 二维码内容, logoImgPath 中间logo, needCompress 是否压缩]
         * @return java.awt.image.BufferedImage
         */
        public static BufferedImage createImage(String content, String logoImgPath, boolean needCompress) throws IOException, WriterException {
                Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
                hints.put(EncodeHintType.MARGIN, 1);
                //200是定义的二维码或小图片的大小
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                //循环遍历每一个像素点
                for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                        }
                }
                // 没有logo
                if (logoImgPath == null || "".equals(logoImgPath)) {
                        return image;
                }

                // 插入logo
                insertImage(image, logoImgPath, needCompress);
                return image;
        }

        /**
         * insertImage
         * @description：二维码插入logo
         * @params [source, logoImgPath, needCompress]
         * @return void
         */
        private static void insertImage(BufferedImage source, String logoImgPath, boolean needCompress) throws IOException {
                File file = new File(logoImgPath);
                if (!file.exists()) {
                        return;
                }

                Image src = ImageIO.read(new File(logoImgPath));
                int width = src.getWidth(null);
                int height = src.getHeight(null);
                //处理logo
                if (needCompress) {
                        if (width > WIDTH) {
                                width = WIDTH;
                        }

                        if (height > HEIGHT) {
                                height = HEIGHT;
                        }

                        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                        Graphics gMaker = tag.getGraphics();
                        gMaker.drawImage(image, 0, 0, null); // 绘制缩小后的图
                        gMaker.dispose();
                        src = image;
                }

                // 在中心位置插入logo
                Graphics2D graph = source.createGraphics();
                int x = (200 - width) / 2;
                int y = (200 - height) / 2;
                graph.drawImage(src, x, y, width, height, null);
                Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
                graph.setStroke(new BasicStroke(3f));
                graph.draw(shape);
                graph.dispose();
        }

        public static final void main(String[] args) throws IOException, WriterException {
                BufferedImage code = createImage("https://my.oschina.net/kevin2kelly", null, false);
                String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode"; //图片保存的位置
                //combineCodeAndPicToFile("D:/360Downloads/b39c1e35e9b8ca42b93b74754c764d80.png", code);
                combineCodeAndPicToBase64("D:/360Downloads/b39c1e35e9b8ca42b93b74754c764d80.png", code);
        }


/*****************************************************x下面没用*********************************************************************
        /**
         * 合并图片
         * create by ly on 2020/9/9
         * @Param [adUrl 广告图片地址，linkUrl 链接地址, x 二维码横坐标, y 二维码纵坐标, qrCodeWith 二维码宽度,
         * qrCodeHeight 二维码高度, format 扩展名, file 文件]
//         * @return void
//         */
//        public static void merageImage(String adURL,String linkUrl,int x,int y,int qrCodeWith,int qrCodeHeight,String format,File file){
//                File qrcodeTemp = null;
//                InputStream linkIs = null;
//                try {
//                        linkIs = JsoupUtils.jsonInputStream(adURL);
//                        if(linkIs==null) throw new NullPointerException();
//                        BufferedImage big = ImageIO.read(linkIs);
//                        String uuidTempName = UUID.randomUUID().toString();
//                        //创建临时文件
//                        qrcodeTemp = File.createTempFile(uuidTempName, "." + format);
//                        //根据链接生成二维码
//                        //GenerateQrCodeUtils.createQrCodeToFile(qrCodeWith,qrCodeHeight,linkUrl,format,qrcodeTemp);
//                        BufferedImage small = ImageIO.read(qrcodeTemp);
//                        Graphics2D g = big.createGraphics();
//                        g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
//                        g.dispose();
//                        ImageIO.write(big, format,file);
//                } catch (Exception e) {
//                        e.printStackTrace();
//                }finally {
//                        if(qrcodeTemp!=null&&qrcodeTemp.exists()){
//                                qrcodeTemp.delete();
//                        }
//                        try {
//                                if(linkIs!=null)linkIs.close();
//                        } catch (IOException e) {
//                                e.printStackTrace();
//                        }
//                }
//        }


        public static final void rrrrr(String backPicPath, BufferedImage code, HttpServletResponse resp) {
                try {
                        InputStream linkIs = null;
                        linkIs = JsoupUtils.jsonInputStream(backPicPath);
                        if(linkIs==null) throw new NullPointerException();
                        BufferedImage big = ImageIO.read(linkIs);

                        // BufferedImage small = ImageIO.read(new File(fillPicPath));
                        BufferedImage small = code;
                        Graphics2D g = big.createGraphics();

                        //二维码或小图在大图的左上角坐标
                        int x = (big.getWidth() - small.getWidth()) / 200;
                        int y = (big.getHeight() - small.getHeight() - 100);   //二维码距大图下边距100
                        g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
                        g.dispose();
                        //去掉这行设置header的代码，前端访问可以直接展示
                        resp.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("lia阿里.png","UTF-8") );
                        //为了保证大图背景不变色，formatName必须为"png"
                        ImageIO.write(big, "png", resp.getOutputStream());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
