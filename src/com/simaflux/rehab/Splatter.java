package com.simaflux.rehab;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.simaflux.rehab.utils.Vector2f;

public class Splatter {
	
	private List<Blood> blood;
	
	public Splatter(Vector2f pos) {
		
		blood = new ArrayList<>();
		
		for(int i = 0; i < 100; i++) {
			blood.add(new Blood(pos));
		}
		
	}
	
	public void update() {
		for(Blood b: blood) b.update();
	}
	
	public void render(Graphics2D g) {
		for(Blood b: blood) b.render(g);
	}
	
}
