package battleship;

import java.util.ArrayList;
import java.util.Scanner;

import jp.vdmtools.VDM.CGException;

public class CLInterface {

	public static void main(String[] args) {
		new CLInterface().run();
	}
	
	private ArrayList<Menu> menus;
	private Game game = null;
	private Scanner input;
	

	public CLInterface(){
		menus = new ArrayList<Menu>();
		menus.add(new NewGame());
		menus.add(new CreatePlayer());
		menus.add(new ChangePlayers());
		menus.add(new Exit());
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
		Menu(){
			name = "";
		}
	}
	
	private class NewGame extends Menu {
		void selectPlayers() throws CGException{
			if (game == null){
				String p1 = get("Create a new player with giving name: ");
				String p2 = get("Create a new player with giving name: ");
				game = new Game(p1,p2);				
			}
		}
		
		void startGame(){
			try{
				System.out.println("\n\n"+game.startGame());
			}
			catch (CGException e) {
				System.out.println("Couldn't not create game:\n"+e.getMessage());
			}
		}
		
		boolean invoke(){
			try {
				selectPlayers();
				startGame();
				placeShips();
			}
			catch (CGException e) {
				System.out.println("Couldn't not create game:\n"+e.getMessage());
			}
			return true;
		}
		NewGame(){
			name = "New Game";
		}
	}
	
	private class CreatePlayer extends Menu {
		boolean invoke(){
			if(game != null){
				String p = get("Create a new player with giving name: ");
				try{
					game.createPlayer(p);
				} catch (CGException e) {
					System.out.println("Player with that name already exists:\n"+e.getMessage());
				}
			} else System.out.println("Must start game before creating new other players");
			return true;
		}
		CreatePlayer(){
			name = "Create Player";
		}
	}
	
	private class ChangePlayers extends Menu {
		boolean invoke(){
			if(game != null){
				String p1 = get("Change player 1 to : ");
				String p2 = get("Change player 2 to : ");
				try {
					game.changePlayers(p1,p2);
				} catch (CGException e) {
					System.out.println("Couldn't not change players:\n"+e.getMessage());
				}
			} else System.out.println("Must start game before changing players");
			return true;
		}
		ChangePlayers(){
			name = "Change Players";
		}
	}
	
	private class Exit extends Menu {
		boolean invoke(){
			return false;
		}
		Exit(){
			name = "Exit";
		}
	}
}
