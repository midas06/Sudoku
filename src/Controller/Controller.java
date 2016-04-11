package Controller;

import Model.Cell;
import Model.GameImpl;
import View.*;
import Extras.Maps;

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
		this.select();
	}
	
	public void select() {
		
		String theOption = this.view.setStr("Press 1 to select a cell by Row.\nPress 2 to select a cell by Column.\nPress 3 to select a cell by Square.");
		String theCell;
		
		try {
			int i = Integer.parseInt(theOption);
			if (i == 1 || i == 2 || i == 3) {
				theCell = this.view.setStr("Make a selection from 1 - " + this.game.getMaxDimension());
				int option = Integer.parseInt(theCell);
				option = option - 1;
				this.selectCell(i, option);
			} else {
				this.view.display("Please enter a valid option.");
				this.select();
			}
			
			
		} catch (NumberFormatException e) {
			this.view.display("Please enter a valid option.");
			this.select();
		}		
	}
	
	protected void selectCell(int selection, int theIndex) {
		Cell theCell;		
		
		String s = this.view.setStr("Choose the cell");
		if (this.strToIntChecker(s)) {
			int i = Integer.parseInt(s);
			i = i - 1;
			switch (selection) {
			case 1: 
				theCell = game.getCellByCoord(i, theIndex);
				this.setValue(theCell);
				break;
			case 2:
				theCell = game.getCellByCoord(theIndex, i);
				this.setValue(theCell);
				break;
			case 3:
				theCell = game.getCellBySquare(theIndex, i);
				this.setValue(theCell);
				break;
			}
		} else {
			this.view.display("Not a valid input");
			this.selectCell(selection, theIndex);
		}		
				
	}
	
	protected void setValue(Cell theCell) {
		String s = this.view.setStr("Add a value");
		if (this.strToIntChecker(s)) {
			int newValue = Integer.parseInt(s);
			game.setSingleValue(newValue, theCell);
			this.view.display(this.game.exportMap());
			this.undo();
			//this.select();
		} else {
			this.view.display("Not a valid input");
			this.setValue(theCell);
		}
		
	}
	
	protected boolean strToIntChecker(String input) {
		try {
			int i = Integer.parseInt(input);
			if (i <= 0 || i > game.getMaxDimension()) {
				return false;
			} else {
				return true;
			}
			
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	protected int strToIntParser(String input) {
		return Integer.parseInt(input);
	}
	
	
	protected void undo() {
		this.game.undo();
		this.view.display(this.game.exportMap());
	}
	
	
}
