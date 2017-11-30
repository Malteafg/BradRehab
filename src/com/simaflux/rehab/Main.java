package com.simaflux.rehab;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.simaflux.rehab.states.GameStateManager;
import com.simaflux.rehab.utils.InputHandler;
import com.simaflux.rehab.utils.Vars;


public class Main extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 6611620322604602709L;
	
	private Thread thread;
	private boolean running;

	private BufferStrategy bs;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	private JFrame window;
	
	private InputHandler input;
	
	private boolean displayCounter;
	private int updates, frames;
	
	public Main() {
		setPreferredSize(new Dimension(Vars.WIDTH, Vars.HEIGHT));
		
		window = new JFrame();
		input = new InputHandler(this);
		
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		
		gsm = new GameStateManager();
		
		displayCounter = false;
	}
	
	public synchronized void start() {
		requestFocus();
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int u = 0;
		int f = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				gameUpdate();
				u++;
				delta--;
			}
			
			gameRender();
			f++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				updates = u;
				frames = f;
				
				u = 0;
				f = 0;
			}
		}
		
	}

	private void gameUpdate() {
		gsm.update();
		input.update();
	}
	
	private void gameRender() {
		bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		
		// RENDERING HINTS
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		
		// SCREEN CLEARING
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, Vars.WIDTH, Vars.HEIGHT);
		
		// DRAWING
		gsm.render(g);

		if(displayCounter) {
			g.setColor(new Color(50, 255, 50, 150));
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("UPS: " + updates + "  FPS: " + frames, 20, 40);
		}
		
		// SHOW GRAPHICS AND DISPOSE
		bs.show();
		g.dispose();
	}
	
	public void keyPressed(int k) {
		gsm.keyPressed(k);
		
		if(k == Vars.F1) displayCounter = !displayCounter;
	}

	public static void main(String[] args) {
		Main main = new Main();
		
		main.window.setResizable(false);
		main.window.setTitle("Innovation");
		main.window.add(main);
		main.window.setUndecorated(false);
		main.window.pack();
		main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.window.setLocationRelativeTo(null);
		main.window.setVisible(true);
		
		main.start();
	}
	
	public int getX() {
		return window.getX();
	}
	
	public int getY() {
		return window.getY();
	}
	
}
