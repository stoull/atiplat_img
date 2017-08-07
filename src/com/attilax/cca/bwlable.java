package com.attilax.cca;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
	

	public class bwlable extends JPanel{

	    int image[][];
	    BufferedImage grayImage;
	    BufferedImage colorImage;
	    int counter=1;

	    public bwlable(int[][] grayImageData) {
	        // TODO Auto-generated constructor stub

	        image = new int[grayImageData.length][grayImageData[1].length];
	        for (int i = 1; i < grayImageData.length-1; i++) {
	            for (int j = 1; j < grayImageData[0].length-1; j++) {
	                image[i][j] = grayImageData[i][j];
	            }
	        }
	        for (int i = 1; i < grayImageData.length-1; i++) {
	            for (int j = 1; j < grayImageData[0].length-1; j++) {
	                if (image[i][j] == 1) {
	                    counter++;
	                    dealBwlabe(i,j);
	                }
	            }
	        }
	        System.out.println(counter-1);

	        color();
	        dialog();

	    }

	    private void dealBwlabe(int i, int j) {
	        // TODO Auto-generated method stub
	        //上
	        if (image[i-1][j] == 1) {
	            image[i-1][j] = counter;
	            dealBwlabe(i-1, j);
	        }
	        //左
	        if (image[i][j-1] == 1) {
	            image[i][j-1] = counter;
	            dealBwlabe(i, j-1);
	        }
	        //下
	        if (image[i+1][j] == 1) {
	            image[i+1][j] = counter;
	            dealBwlabe(i+1, j);
	        }
	        //右
	        if (image[i][j+1] == 1) {
	            image[i][j+1] = counter;
	            dealBwlabe(i, j+1);
	        }

	////八连通需要
//	      //上左
//	      if (image[i-1][j-1] == 1) {
//	          image[i-1][j-1] = counter;
//	          dealBwlabe(i-1, j-1);
//	      }
//	      //上右
//	      if (image[i-1][j+1] == 1) {
//	          image[i-1][j+1] = counter;
//	          dealBwlabe(i-1, j+1);
//	      }
//	      //下左
//	      if (image[i+1][j-1] == 1) {
//	          image[i+1][j-1] = counter;
//	          dealBwlabe(i+1, j-1);
//	      }
//	      //下右
//	      if (image[i+1][j+1] == 1) {
//	          image[i+1][j+1] = counter;
//	          dealBwlabe(i+1, j+1);
//	      }       

	    }

	    private void color(){

	        int color[];
	        color = new int[counter+1];

	        for (int i = 0; i < color.length; i++)  
	            color[i] = (int) (0xff000000+Math.random()*0xffffff);
	        colorImage = new BufferedImage(image.length, image[0].length, 5);

	        for (int i = 0; i < colorImage.getWidth(); i++) 
	            for (int j = 0; j < colorImage.getHeight(); j++) 
	            {   if (image[i][j] > 0) colorImage.setRGB(i, j, color[image[i][j]]);
	                else colorImage.setRGB(i, j, 0xff000000);
	            }

	    }

	    public void dialog(){
	        JFrame mFrame = new JFrame();
	        mFrame.setSize(800, 500);
	        mFrame.setVisible(true);

	        mFrame.add(this);
	        //mFrame
	    }
	    @Override
	    public void paint(Graphics g) {
	        // TODO Auto-generated method stub
	        super.paint(g);
	        try {
	            grayImage = ImageIO.read(new File("specialGray.jpg"));

	            g.drawImage(grayImage, 0, 0, 400, 500, null);
	            g.drawImage(colorImage, 402, 0, 400, 500, null);
	            g.setColor(Color.red);
	            Font mFont= g.getFont();
	            g.setFont(new Font(mFont.getFontName(), Font.PLAIN, 20));
	            g.drawString(counter-1+" ", 2, 20);
	            g.drawString("原图", 100, 400);
	            g.drawString("染色处理图", 500, 400);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


	    }
	}

 