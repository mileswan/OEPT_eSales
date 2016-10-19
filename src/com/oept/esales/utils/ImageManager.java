package com.oept.esales.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
/**
 * @author mwan
 * Version: 1.0
 * Date: 2015/12/18
 * Description:Image utility.
 * Copyright (c) 2015 上海基辕科技有限公司. All rights reserved.
 */
public class ImageManager {

	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * @param is 上传的图片的输入流
	 * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param size 新图片的宽
	 * @param format 新图片的格式
	 * @throws IOException
	 */
	public static void resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		double percent = size/width;
		int newWidth = (int)(width * percent);
		int newHeight = (int)(height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, os);
		os.flush();
		is.close();
		os.close();
	}
}
