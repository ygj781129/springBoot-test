package com.example.demo.service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fb on 2020/9/9
 */
public class QrCodeUtil {
//        public static void main(String[] args) {
//                String url = "www.baidu.com";//需要转化二维码的地址链接
//                String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode"; //图片保存的位置
//                String fileName = "XXX.jpg"; //图片名称
//                createQrCode(url, path, fileName);
//        }

        public static void main(String[] args) throws Exception{
                String url = "www.baidu.com";//需要转化二维码的地址链接
                String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode"; //图片保存的位置
                Map<EncodeHintType, String> hints = new HashMap<>();
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);

                File f= new File("gfgfg") ;
                OutputStream stream=new FileOutputStream(f);
                writeToStream(bitMatrix,"jpg",stream);
        }

        public static String createQrCode(String url, String path, String fileName) {
                try {
                        Map<EncodeHintType, String> hints = new HashMap<>();
                        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);
                        File file = new File(path, fileName);
                        if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile())) {
                                writeToFile(bitMatrix, "jpg", file);
                                System.out.println("搞定：" + file);
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
                BufferedImage image = toBufferedImage(matrix);
                if (!ImageIO.write(image, format, file)) {
                        throw new IOException("Could not write an image of format " + format + " to " + file);
                }
        }

        static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
                BufferedImage image = toBufferedImage(matrix);
                if (!ImageIO.write(image, format, stream)) {
                        throw new IOException("Could not write an image of format " + format);
                }
        }

        private static final int BLACK = 0xFF000000;
        private static final int WHITE = 0xFFFFFFFF;

        private static BufferedImage toBufferedImage(BitMatrix matrix) {
                int width = matrix.getWidth();
                int height = matrix.getHeight();
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
                        }
                }
                return image;
        }

}
