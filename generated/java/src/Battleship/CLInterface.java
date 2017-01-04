package Battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class CLInterface {

	public static void main(String[] args) {
		new CLInterface().run();
	}
	
	private ArrayList<Menu> menus;
	private Game game = null;
	private Scanner input;
	

	public CLInterface(){
		menus = new ArrayList<Menu>();
		menus.add(new NewGame("New Game"));
		input = new Scanner(System.in);
	}
	
	public void run(){
		boolean in = true;
		String message = new String("Select an valid option\n\n");
		for(int i=0; i < menus.size(); i++)
			message += Integer.toString(i) + ": " + menus.get(i).name + "\n";
		message += "\n";
		while(in){
			String indStr = get(message);
			try{
				int ind = Integer.parseInt(indStr);
				System.out.println("\n");
				if(ind >= 0 && ind < menus.size())
					in = menus.get(ind).invoke();
				else System.out.println("Selection not valid");
			} catch (NumberFormatException e){
				System.out.println("Selection not valid");
			}
			System.out.println("\n");
		}
	}
	
	private String get(String message){
		System.out.println(message);
		return input.nextLine();
	}
	
	private abstract class Menu {
		protected String name;
		abstract boolean invoke();
		Menu(String nameArg){
			name = nameArg;
		}
	}
	
	private class NewGame extends Menu {
		void selectPlayers(){
			if (game == null){
				String p1 = get("Create a new player with giving name: ");
				String p2 = get("Create a new player with giving name: ");
				game = new Game(p1,p2);
			}
		}
		
		boolean invoke(){
			selectPlayers();
			return true;
		}
		NewGame(String nameArg){
			super(nameArg);
		}
	}
}
