package battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import jp.vdmtools.VDM.CGException;

public class CLInterface {

	public static void main(String[] args) {
		CLInterface cli = new CLInterface();
		cli.run();
	}
	
	private ArrayList<Menu> menus;
	private Game game = null;
	private Scanner input;
	private HashMap<String,Object> ships;
	private HashMap<String,Object> dirs;

	public CLInterface(){
		ships = new HashMap<String,Object> ();
		ships.put("carrier",new quotes.Carrier());
		ships.put("battleship", new quotes.Battleship());
		ships.put("cruiser", new quotes.Cruiser());
		ships.put("submarine", new quotes.Submarine());
		ships.put("destroyer",new quotes.Destroyer());
		
		dirs = new HashMap<String,Object>();
		dirs.put("left", new quotes.Left());
		dirs.put("right", new quotes.Right());
		dirs.put("up", new quotes.Up());
		dirs.put("down", new quotes.Down());
		
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
		System.out.print(message);
		return input.nextLine();
	}
	
	private Object strToShip(String ship) throws Exception{
		
		Iterator<Entry<String, Object>> it = ships.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> pair = (Entry<String,Object>)it.next();
			String othShip = pair.getKey();
			if(ship.equalsIgnoreCase(othShip.substring(0, Math.min(ship.length(), othShip.length()))))
				return pair.getValue();
		}
		throw new Exception("ship not found");
	}
	
	private Character strToCol(String col) throws Exception{
		if (col.length() != 1) throw new Exception("col not valid");
		if (col.compareToIgnoreCase("A") >= 0 && col.compareToIgnoreCase("J") <= 0)
			return col.toUpperCase().charAt(0);
		throw new Exception("character invalid");
	}
	
	private Number strToLine(String line) throws Exception{
		Integer lineInt = Integer.parseInt(line);
		if (lineInt >= 1 && lineInt <= 10)
			return lineInt;
		throw new Exception("line invalid");
	}
	
	private Object strToDir(String dir) throws Exception{
		
		Iterator<Entry<String, Object>> it = dirs.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> pair = (Entry<String,Object>)it.next();
			String othDir = pair.getKey();
			if(dir.equalsIgnoreCase(othDir.substring(0, Math.min(dir.length(), othDir.length()))))
				return pair.getValue();
		}
		throw new Exception("direction not found");
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
				System.out.println("\n\nCouldn't not create game:\n"+e.getMessage());
			}
		}
		
		void placeShips(){
			String out = "";
			Object ship = null;
			Character col = null;
			Number line = null;
			Object dir = null;
			while(!out.contains("All ships placed")){
				try{
					ship = strToShip(get("Place a ship (Carrier, Battleship, Cruiser, Submarine, Destroyer) providing at least three letters: "));
					col = strToCol(get("Set column index ('A'-'J'): "));
					line = strToLine(get("Set line index (1-10): "));
					dir = strToDir(get("Set direction and oriention of ship (left,right,up,down) providing at least one letter: "));
					
					out = game.shipPlacement(ship, col, line, dir);
					System.out.println("\n\n\n\n\n\n\n\n"+ out + "\n\n");
				}
				catch (Exception e){
					System.out.println("\nInvalid input");
				}
			}
		}
		
		void guessShip(){
			String out = "";
			String col = "";
			String line = "";
			while(!out.contains("Victory")){
				try{
					col = get("Set column index ('A'-'J'): ");
					line = get("Set line index (1-10): ");
					
					out = game.guessShipPosition(strToCol(col), strToLine(line));
					System.out.println("\n\n\n\n\n\n\n\n"+ out + "\n\n");
				}
				catch (Exception e){
					System.out.println("\nInvalid input");
				}
			}
		}
		
		boolean invoke(){
			try {
				selectPlayers();
				startGame();
				placeShips();
				System.out.println(game.startRounds());
				guessShip();
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
