package com.cdhaixun.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

/**
 * @Author tanggm
 */
public class ImagesUtil {

    /**
     * 图片剪裁功能
     * @param imagePath 原图地址
     * @param x 目标切片坐标 x轴起点
     * @param y 目标切片坐标 y轴起点
     * @param w 目标切片  宽度
     * @param h 目标切片 高度
     */
    public String cutImage(String imagePath,int x,int y,int w,int h){
        Image img;
        ImageFilter cropFilter;
        try {
         //读取源图像
         BufferedImage bi=ImageIO.read(new File(imagePath));
         int srcWidth=bi.getWidth();//原图宽度
         int srcHeight=bi.getHeight();//原图高度
         //若原图大小大于大于切片大小，则进行切割
         if(srcWidth>=w&&srcHeight>=h){
          Image image=bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
//          int x1=x*srcWidth/400;
//          int y1=y*srcHeight/270;
//          int w1=w*srcWidth/400;
//          int h1=h*srcHeight/270;
          int x1=x;
          int y1=y;
          int w1=w;
          int h1=h;
          cropFilter=new CropImageFilter(x1,y1,w1,h1);
          img=Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(image.getSource(),cropFilter));
          BufferedImage tag=new BufferedImage(w1,h1,BufferedImage.TYPE_INT_BGR);
          Graphics g=tag.getGraphics();
          g.drawImage(img, 0, 0, null);
          g.dispose();
          String url=imagePath.substring(0,imagePath.lastIndexOf(File.separator)+1);
          String name=imagePath.substring(imagePath.lastIndexOf(File.separator)+1);
          String[] src=name.split(File.separator+".");
          imagePath=src[0].concat("cut.").concat(src[1]);
          url=url.concat(imagePath);
          ImageIO.write(tag, "JPEG",new File(url) );
         }
        } catch (IOException e) {
         e.printStackTrace();
        }
        return ConfigContentUtils.PIC_ROOT_PATH+File.separator+imagePath;
       }
}
