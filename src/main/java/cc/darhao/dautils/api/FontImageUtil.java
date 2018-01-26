package cc.darhao.dautils.api;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FontImageUtil {
	

    /**
     * 根据文字、字体、宽高，生成文字图片
     * @param str 文字
     * @param font 字体
     * @param width 宽
     * @param height 高
     * @return
     */
    public static BufferedImage createImage(String str, Font font, Integer width, Integer height) {    
        // 创建图片    
        BufferedImage image = new BufferedImage(width, height,    
                BufferedImage.TYPE_INT_BGR);    
        Graphics g = image.getGraphics();    
        g.setClip(0, 0, width, height);    
        g.setColor(Color.white);    
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景    
        g.setColor(Color.black);// 在换成黑色    
        g.setFont(font);// 设置画笔字体    
        /** 用于获得垂直居中y */    
        Rectangle clip = g.getClipBounds();    
        FontMetrics fm = g.getFontMetrics(font);    
        int ascent = fm.getAscent();    
        int descent = fm.getDescent();    
        int y = (clip.height - (ascent + descent)) / 2 + ascent;    
        for (int i = 0; i < 6; i++) {// 256 340 0 680    
            g.drawString(str, i * 680, y);// 画出字符串    
        }    
        g.dispose();
        return image;
    }
    
    
    /**
     * 根据文字、宽高，生成文字图片
     * @param str 文字
     * @param width 宽
     * @param height 高
     * @return
     */
    public static BufferedImage createImage(String str, Integer width, Integer height) { 
    	Font font = new Font("宋体", Font.PLAIN, 32);
        return createImage(str, font, width, height);
    }    
	
}
