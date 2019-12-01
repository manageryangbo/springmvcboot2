package com.imageutil;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;


public class UpdateImage {
    public static void main(String[] args) throws TesseractException {
        String initPath = "/tmp/test2.pdf";
//        transferAlpha(initPath,outPath);
//        transferWenzi(initPath);
//        System.out.println("=========================================================");
//        transferWenzi(outPath);


    }

    public static void transferWenzi(String outPath) throws TesseractException {
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        //instance.setDatapath("the absolute path of tessdata");
        instance.setDatapath("/tmp/tessdata");
        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("eng");

        // 指定识别图片
        File imgDir = new File(outPath);
        long startTime = System.currentTimeMillis();
//        Rectangle rectangle = new Rectangle(0,0,100,100);
        String ocrResult = instance.doOCR(imgDir);
        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");

    }

    public static byte[] transferAlpha(String src,String src1) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        File file = new File(src);
//        InputStream is;
        try {
//            is = new FileInputStream(file);
            // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
            BufferedImage bi = ImageIO.read(new File(src));
            Image image = bi;
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
//                    System.out.println( "rgb:"+rgb  );
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    System.out.println( "rgb:"+rgb+"R:"+R+"G:"+G+"B:"+B  );
                    if (((255 - R) > 200) && ((255 - G) > 200) && ((255 - B) > 200)) {

                        System.out.println( "rgb:"+rgb+"R:"+R+"G:"+G+"B:"+B  );
//                        int red = (int)(R * 0.2126);
//                        int green = (int)(G * 0.3152);
//                        int blue = (int)(B*0.0722);
                        int red = 247;
                        int green = 247;
                        int blue = 247;
                        Color newColor = new Color(255,
                                255,255);
//                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                        rgb = newColor.getRGB() ;
                        System.out.println( "rgb changing:"+rgb );
//                        rgb = 000000;
                        System.out.println( "rgb changed:"+rgb );
                    }
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            ImageIO.write(bufferedImage, "png", new File(src1));// 直接输出文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
