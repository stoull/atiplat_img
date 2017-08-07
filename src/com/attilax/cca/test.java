package com.attilax.cca;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {

    public static void main(String[] args) throws IOException {

//      new bwlable(getCircle(10));
        String img = "c:\\00\\b.jpg";
		BufferedImage mBufferedImage = ImageIO.read(new File(img));

        int image[][];
        image = new int[mBufferedImage.getWidth()][mBufferedImage.getHeight()];
        int numRGB = 0xff222222;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (mBufferedImage.getRGB(i, j) > numRGB)   image[i][j] = 1;
                else image[i][j] = 0;
            }
        }
        new bwlable(image);

    }
}