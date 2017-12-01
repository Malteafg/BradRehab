package com.simaflux.rehab.utils;

import static com.simaflux.rehab.utils.Vars.*;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.simaflux.rehab.Main;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
	
	private boolean[] keys, prevKeys;
	
	private Main main;
	
	public InputHandler(Main main) {
		this.main = main;
		keys = new boolean[23];
		prevKeys = new boolean[23];
	}
	
	public void update() {
		// KEY PRESSED
		for(int i = 0; i < keys.length; i++) {
			if(!prevKeys[i] && keys[i]) {
				main.keyPressed(i);
			}
			prevKeys[i] = keys[i];
		}
	}
	
	private void keySwitch(int k, boolean b) {
		if(k == KeyEvent.VK_ESCAPE) keys[ESCAPE] = b;
		if(k == KeyEvent.VK_SPACE) keys[SPACE] = b;
		if(k == KeyEvent.VK_LEFT) keys[LEFT] = b;
		if(k == KeyEvent.VK_RIGHT) keys[RIGHT] = b;
		if(k == KeyEvent.VK_UP) keys[UP] = b;
		if(k == KeyEvent.VK_DOWN) keys[DOWN] = b;
		if(k == KeyEvent.VK_F1) keys[F1] = b;
		if(k == KeyEvent.VK_0) keys[_0] = b;
		if(k == KeyEvent.VK_1) keys[_1] = b;
		if(k == KeyEvent.VK_2) keys[_2] = b;
		if(k == KeyEvent.VK_3) keys[_3] = b;
		if(k == KeyEvent.VK_4) keys[_4] = b;
		if(k == KeyEvent.VK_5) keys[_5] = b;
		if(k == KeyEvent.VK_6) keys[_6] = b;
		if(k == KeyEvent.VK_7) keys[_7] = b;
		if(k == KeyEvent.VK_8) keys[_8] = b;
		if(k == KeyEvent.VK_9) keys[_9] = b;
		if(k == KeyEvent.VK_COMMA) keys[COMMA] = b;
		if(k == KeyEvent.VK_COLON) keys[DOT] = b;
		if(k == 27) System.exit(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keySwitch(e.getKeyCode(), true);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keySwitch(e.getKeyCode(), false);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = MouseInfo.getPointerInfo().getLocation();
		Vars.mousePos.set((int) (p.getX() - main.getX()), (int) (p.getY() - main.getY()));
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = MouseInfo.getPointerInfo().getLocation();
		Vars.mousePos.set((int) (p.getX() - main.getX()), (int) (p.getY() - main.getY()));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

}
