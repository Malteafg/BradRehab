package com.simaflux.rehab.utils;

public abstract class Maths {
	
	public static final float PI = (float) Math.PI;
	
	public static float sqrt(float num) {
		return (float) Math.sqrt(num);
	}
	
	public static float pow(float num, float pow) {
		return (float) Math.pow(num, pow);
	}
	
	public static float random() {
		return (float) Math.random();
	}
	
	public static float cos(float angle) {
		return (float) Math.cos((double) angle);
	}
	
	public static float acos(float num) {
		return (float) Math.acos((double) num);
	}
	
	public static float sin(float angle) {
		return (float) Math.sin((double) angle);
	}
	
	public static float asin(float num) {
		return (float) Math.asin((double) num);
	}
	
	public static float atan(float y, float x) {
		return (float) Math.atan2(y, x);
	}
	
	public static float inverse(float f) {
		return Maths.sqrt(1f - f * f); 
	}
	
}
