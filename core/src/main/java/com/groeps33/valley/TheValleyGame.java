package com.groeps33.valley;

import com.badlogic.gdx.Game;
import com.groeps33.valley.screens.GameScreen;

public class TheValleyGame extends Game {

	
	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
