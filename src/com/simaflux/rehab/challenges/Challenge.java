package com.simaflux.rehab.challenges;

import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vector2f;

public abstract class Challenge {
	
	protected Vector2f pos, size;
	
	protected boolean answered;
	
	protected String q;
	
	public boolean updatePosition(float speed) {
		pos.x -= speed;
		
		if(pos.x + size.x + 60 < 0) {
			return true;
		} else return false;
	}
	
	public boolean answer(double a) {
		answered = true;
		if(a == -1) return true;
		
		return answerCase(a);
	}
	
	public abstract boolean answerCase(double a);
	public abstract void render(Graphics2D g);
	
	public Vector2f getPos() {
		return pos;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public boolean hasAnswered() {
		return answered;
	}
	
	public String getQuestion() {
		return q;
	}

}
