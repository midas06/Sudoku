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
		
//		g.addSingleValueByCoord(1, 1, 0);
//		g.addSingleValueByCoord(6, 2, 0);
//		g.addSingleValueByCoord(5, 4, 0);
//		
//		g.addSingleValueByCoord(6, 4, 1);
//		g.addSingleValueByCoord(1, 5, 1);
//		
//		g.addSingleValueByCoord(6, 5, 2);
//		
//		g.addSingleValueByCoord(3, 0, 3);
//		
//		g.addSingleValueByCoord(5, 0, 4);
//		g.addSingleValueByCoord(3, 1, 4);
//		
//		g.addSingleValueByCoord(4, 1, 5);
//		g.addSingleValueByCoord(5, 3, 5);
//		g.addSingleValueByCoord(2, 4, 5);
	}
	
	protected void setMap2() {
		g.setMaxValue(81);
		
		Cell theCell;
		theCell = g.getCellByCoord(1, 0);
		g.addSingleValue(1, theCell);
		theCell = g.getCellByCoord(2, 0);
		g.addSingleValue(6, theCell);
		theCell = g.getCellByCoord(3, 0);
		g.addSingleValue(5, theCell);
		theCell = g.getCellByCoord(4, 0);
		g.addSingleValue(4, theCell);
		theCell = g.getCellByCoord(5, 0);
		g.addSingleValue(3, theCell);
		theCell = g.getCellByCoord(6, 0);
		g.addSingleValue(2, theCell);
		theCell = g.getCellByCoord(7, 0);
		g.addSingleValue(0, theCell);
		theCell = g.getCellByCoord(8, 0);
		g.addSingleValue(7, theCell);
		theCell = g.getCellByCoord(0, 0);
		//g.addSingleValue(5, theCell);
		int[] arr = new int[3];
		arr[0] = 3;
		arr[1] = 2;
		arr[2] = 0;
		g.addMultipleValues(arr, theCell);
		
		
		theCell = g.getCellByCoord(3, 1);
		g.addSingleValue(4,theCell);
//		g.addSingleValueByCoord(6, 4, 1);
//		g.addSingleValueByCoord(3, 5, 1);
		
//		g.addSingleValueByCoord(6, 5, 2);
		theCell = g.getCellByCoord(0, 3);
		g.addSingleValue(3, theCell);
		
//		g.addSingleValueByCoord(5, 0, 4);
//		g.addSingleValueByCoord(3, 1, 4);
//		
//		g.addSingleValueByCoord(4, 1, 5);
//		g.addSingleValueByCoord(5, 3, 5);
//		g.addSingleValueByCoord(2, 4, 5);
		
		theCell = g.getCellBySquare(3, 3);
		g.addSingleValue(6, theCell);
		
		g.finaliseInitialPuzzle();
		
	}
	
	
	
	
}
