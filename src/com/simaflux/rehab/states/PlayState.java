package com.simaflux.rehab.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.challenges.Challenge;
import com.simaflux.rehab.challenges.PythagorasJump;
import com.simaflux.rehab.challenges.PythagorasLake;
import com.simaflux.rehab.player.Player;
import com.simaflux.rehab.player.Splatter;
import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;

public class PlayState extends State {
	
	private Player player;
	
	private boolean moving, jumping;
	private float movedDist, horizontalSpeed;
	
	private Challenge challenge;
	
	private String answer;
	
	private Splatter splatter;
	
	private int flashTimer, deadTime;
	
	private int score;
	private boolean won;
	
	private int level;
	
	private boolean pythagoras, calculator;

	public PlayState(GameStateManager gsm, int level) {
		super(gsm);
		
		player = new Player();
		
		moving = true;
		jumping = false;
		
		movedDist = 0;
		
		score = 0;
		
		won = false;
		this.level = level;
		deadTime = 180;
		
		pythagoras = false;
		calculator = false;
		
		createChallenge();
	}
	
	private void createChallenge() {
		int r = (int) (Math.random() * 2);
//		int r = 0;
		switch(r) {
		case 0:
			challenge = new PythagorasLake();
			break;
		case 1:
			challenge = new PythagorasJump();
			break;
		}
		answer = "";
	}

	@Override
	public void update() {
		if(!won) {	
			if(moving) {
				movedDist += Vars.moveSpeed;
				
				if(challenge.updatePosition(Vars.moveSpeed)) createChallenge();
				
				if(challenge.getPos().x + challenge.getSize().x / 2 < Vars.WIDTH / 2 && !challenge.hasAnswered()) {
					moving = false;
				}
			}
			
			if(player.bloodTime()) {
				if(moving) splatter = new Splatter(player.getPos());
				moving = false;
				splatter.update();
				deadTime--;
				if(deadTime == 0) gsm.setState(new MenuState(gsm));
			} else {
				player.update(moving);
			}
			
			if(jumping) {
				movedDist += horizontalSpeed;
				
				challenge.updatePosition(horizontalSpeed);
				
				if(player.getPos().y == Vars.PLAYER_HEIGHT) {
					jumping = false;
					moving = true;
					if(score == 10) won = true;
				}
			}
		} else {
			if(player.fly()) {
				MenuState.unlocked[level + 1] = true;
				MenuState.unlockedNum++;
				gsm.setState(new MenuState(gsm));
			}
		}
		
		flashTimer++;
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("bg").render(g, (-movedDist / 3) % Vars.WIDTH, 0);
		Loader.getTexture("bg").render(g, (-movedDist / 3) % Vars.WIDTH + Vars.WIDTH, 0);
		Loader.getTexture("plank").render(g, -movedDist % Vars.WIDTH, Vars.PLAYER_HEIGHT);
		Loader.getTexture("plank").render(g, -movedDist % Vars.WIDTH + Vars.WIDTH, Vars.PLAYER_HEIGHT);
		
		challenge.render(g);
		
		if(player.bloodTime() && splatter != null) {
			splatter.render(g);  
			Loader.getTexture("user").render(g, player.getPos().x, player.getPos().y - 80, Math.PI / 2);
		} else if(won) {
			Loader.getTexture("jump").render(g, player.getPos().x, player.getPos().y - 128);
		} else {
			player.render(g, moving);
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Serif", Font.BOLD, 60));
		int answerwidth = g.getFontMetrics().stringWidth(answer);
		
		if(!moving && !challenge.hasAnswered() && !player.bloodTime()) {
			int width = g.getFontMetrics().stringWidth(challenge.getQuestion());
			g.drawString(challenge.getQuestion(), Vars.WIDTH / 2 - width / 2, Vars.PLAYER_HEIGHT + 250);
			g.fillRect(Vars.WIDTH / 2 - 75, Vars.PLAYER_HEIGHT + 270, 150, 70);
			g.setColor(Color.BLACK);
			int t = 60;
			if(flashTimer % t > 0 && flashTimer % t < t / 2) g.fillRect(Vars.WIDTH / 2 + answerwidth / 2, Vars.PLAYER_HEIGHT + 280, 5, 50);
			g.setFont(new Font("Serif", Font.BOLD, 50));
			g.drawString(answer, Vars.WIDTH / 2 - answerwidth / 2, Vars.PLAYER_HEIGHT + 320);
			
//			Loader.getTexture("calculator").render(g, 1450, 600, 300, 400);
		}
		
		g.setFont(new Font("Serif", Font.BOLD, 70));
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 20, 90);
		
		if(won) {
			g.setFont(new Font("Serif", Font.BOLD, 70));
			g.setColor(Color.BLACK);
			int w = g.getFontMetrics().stringWidth(MenuState.topics[level + 1] + " is unlocked");
			g.drawString(MenuState.topics[level + 1] + " is unlocked", Vars.WIDTH / 2 - w / 2, 800);
		}
		
		if(pythagoras) {
			Loader.getTexture("pythagoras").render(g, 1500, 50);
		}
		if(calculator) {
			Loader.getTexture("calculator").render(g, 1600, 700);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(!moving && !challenge.hasAnswered()) {
			if(k == Vars._0) answer += "0";
			if(k == Vars._1) answer += "1";
			if(k == Vars._2) answer += "2";
			if(k == Vars._3) answer += "3";
			if(k == Vars._4) answer += "4";
			if(k == Vars._5) answer += "5";
			if(k == Vars._6) answer += "6";
			if(k == Vars._7) answer += "7";
			if(k == Vars._8) answer += "8";
			if(k == Vars._9) answer += "9";
			if(k == Vars.DOT) answer += ".";
			if(k == Vars.COMMA) answer += ".";
			if(k == Vars.BACK) if(answer.length() > 0) answer = answer.substring(0, answer.length() - 1);
			
			if(k == Vars.SPACE || k == Vars.ENTER) {
				jumping = true;
				boolean c = challenge.answer(answer.equals("") ? -1 : Double.parseDouble(answer));
				
				if(!c) score++;
				
				switch(challenge.getName()) {
				case "PythagorasJump":
					player.jump(challenge.getPos().x + challenge.getSize().x - player.getPos().x, 
							(c ? challenge.getSize().y * 0.9f : challenge.getSize().y + Loader.getTexture("user").getHeight() / 2), 
							c);
					horizontalSpeed = player.getHorizontalSpeed();
					break;
				case "PythagorasLake":
					player.jump(c ? (challenge.getPos().x + challenge.getSize().x - player.getPos().x) / 3.0f : 
							(challenge.getPos().x + challenge.getSize().x - player.getPos().x) / 1.9f, 
							200, c);
					horizontalSpeed = player.getHorizontalSpeed();
					break;
				}
			}
		}
		
		if(k == Vars.N) pythagoras = !pythagoras;
		if(k == Vars.M) calculator = !calculator;
	}

}
