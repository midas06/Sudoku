package View;
import java.util.Scanner;

public class ConsoleView implements View {
Scanner scanner;
	
	public ConsoleView() {
		scanner = new Scanner(System.in);
	}
	
	public void stop() {
		scanner.close();
	}
	
	public <T> void display(T message) {
		System.out.println(message);
	}
	
	public <T> String setStr(T message) {
		System.out.println(message);
		return scanner.nextLine();
	}
	
	
}
