package com.attilax.img;

import com.attilax.json.AtiJson;

public class HSV {

	public float h;
	public float s;
	public float v;
	public int y;
	public int x;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getS() {
		return s;
	}

	public void setS(float s) {
		this.s = s;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public HSV(float h, float s, float v) {
		this.h=h;
		this.s=s;
		this.v=v;
	}
	public String toString()
	{
		return AtiJson.toJson(this);
	}

//	public boolean contain(HSV hs) {
//		 
//		return  hs.h>this.h;
//	}

}
