package me.pogostick29dev.pogoball;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class ArenaManager {

	public enum Team { RED, BLUE; }
	
	private ArenaManager() { }
	
	private static ArenaManager instance = new ArenaManager();
	
	public static ArenaManager getInstance() {
		return instance;
	}
	
	private ArrayList<Arena> arenas = new ArrayList<Arena>();
	
	public void setup() {
		try {
			for (int i : SettingsManager.getInstance().<List<Integer>>get("ids")) {
				arenas.add(new Arena(i));
			}
		}
		catch (Exception ignored) { }
	}
	
	public ArrayList<Arena> getArenas() {
		return arenas;
	}
	
	public Arena getArena(int id) {
		for (Arena a : arenas) {
			if (a.getID() == id) return a;
		}
		return null;
	}
	
	public Arena getArena(Player p) {
		for (Arena a : arenas) {
			if (a.containsPlayer(p)) return a;
		}
		return null;
	}
}