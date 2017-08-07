package com.attilax.cca;

import java.awt.Color;
import java.awt.Point;

import com.attilax.json.AtiJson;

public class Pix {
	
	public static void main(String[] args) {
		Pix p=new Pix(new Point(0, 0));
		System.out.println(p);
	}

	public Point point;
	public Color color;

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Pix(Point pt) {
		this.point=pt;
	}
	
	public String toString()
	{
		return  AtiJson.toJson(point)+ AtiJson.toJson(color) +" code:"+this.hashCode();
	}
	
	 
//	  public boolean equals(Object obj) {
//		  Pix p2=(Pix) obj;
//		  return  p2.point.x==this.point.x && p2.point.y==this.point.y  && p2.color.getRGB()==this.color.getRGB();
//	  }

}
