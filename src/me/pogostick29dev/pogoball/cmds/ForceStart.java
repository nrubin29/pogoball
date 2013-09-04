package me.pogostick29dev.pogoball.cmds;

import org.bukkit.entity.Player;

import me.pogostick29dev.pogoball.Arena;
import me.pogostick29dev.pogoball.ArenaManager;
import me.pogostick29dev.pogoball.MessageManager;

public class ForceStart extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			MessageManager.getInstance().severe(p, "You must specify an arena ID.");
			return;
		}
		
		int id = -1;
		
		try { id = Integer.parseInt(args[0]); }
		catch (Exception e) {
			MessageManager.getInstance().severe(p, args[0] + " is not a valid number!");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(id);
		
		if (a == null) {
			MessageManager.getInstance().severe(p, "There is no arena with ID " + id + "!");
			return;
		}
		
		if (a.isStarted()) {
			MessageManager.getInstance().severe(p, "Arena " + id + " has already started!");
			return;
		}
		
		a.start();
		MessageManager.getInstance().good(p, "Force started Arena " + a.getID() + "!");
	}
	
	public String name() {
		return "forcestart";
	}
	
	public String info() {
		return "Force start an arena.";
	}
	
	public String[] aliases() {
		return new String[] { "fstart", "start" };
	}
}