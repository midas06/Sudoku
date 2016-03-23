package Extras;
import Model.*;

public class Maps {
	GameImpl g;
	
	public Maps(GameImpl g) {
		this.g= g;
	}
	
	public void setMap(int newMap) {
		switch(newMap) {
		case 1:
			this.setMap1();
			break;
		case 2:
			this.setMap2();
			break;
		default:
			break;
		}
	}
	
	
	protected void setMap1() {
		g.setMaxValue(36);
		
		g.setValueByCoord(1, 1, 0);
		g.setValueByCoord(6, 2, 0);
		g.setValueByCoord(5, 4, 0);
		
		g.setValueByCoord(6, 4, 1);
		g.setValueByCoord(1, 5, 1);
		
		g.setValueByCoord(6, 5, 2);
		
		g.setValueByCoord(3, 0, 3);
		
		g.setValueByCoord(5, 0, 4);
		g.setValueByCoord(3, 1, 4);
		
		g.setValueByCoord(4, 1, 5);
		g.setValueByCoord(5, 3, 5);
		g.setValueByCoord(2, 4, 5);
	}
	
	protected void setMap2() {
		g.setMaxValue(81);
		
		g.setValueByCoord(1, 1, 0);
		g.setValueByCoord(6, 2, 0);
		g.setValueByCoord(5, 4, 0);
		
		g.setValueByCoord(6, 4, 1);
		g.setValueByCoord(1, 5, 1);
		
		g.setValueByCoord(6, 5, 2);
		
		g.setValueByCoord(3, 0, 3);
		
		g.setValueByCoord(5, 0, 4);
		g.setValueByCoord(3, 1, 4);
		
		g.setValueByCoord(4, 1, 5);
		g.setValueByCoord(5, 3, 5);
		g.setValueByCoord(2, 4, 5);
	}
	
	
	
	
}
