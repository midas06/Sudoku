package Controller;

import Model.GameImpl;
import View.*;
import Extras.*;

public class Controller {
	protected GameImpl game;
	protected View view;
	protected Maps maps;
	
	public Controller(View newView) {
		this.view = newView;
		this.game = new GameImpl();
		this.maps = new Maps(this.game);
	}
	
	public void init() {
		this.setMap();
	}
	
	public void setMap() {
		int i = Integer.valueOf(this.view.setStr("Choose a puzzle:"));
		this.maps.setMap(i);
		this.view.display(this.game.exportMap());
	}
	
	
}
