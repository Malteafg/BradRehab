package com.simaflux.rehab.utils;

import java.util.HashMap;

public class Loader {
	
	/*
	 * TEXTURE LOADING
	 */	
	public static void loadAllTextures() {
		loadTexture("bg", "environment");
		loadTexture("plank", "environment");
		loadTexture("hole", "environment");
		loadTexture("lock", "environment");
		loadTexture("calculator", "environment");
		
		loadTexture("user", "player");
		loadTexture("run1", "player");
		loadTexture("run2", "player");

		loadTexture("disco1", "player");
		loadTexture("dancemove2", "player");
		
		loadTexture("jump", "player");
	}
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	private static void loadTexture(String name, String folder) {
		try {
			textures.put(name, new Texture("/" + folder + "/" + name + ".png"));
		} catch(IllegalArgumentException e) {
        	e.printStackTrace();
            System.err.println("Failed to load: " + name);
        }
	}
	
	public static Texture getTexture(String name) {
		return textures.get(name);
	}

}
