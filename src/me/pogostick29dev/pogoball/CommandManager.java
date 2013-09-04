package me.pogostick29dev.pogoball;

import java.util.ArrayList;
import java.util.Arrays;

import me.pogostick29dev.pogoball.cmds.Create;
import me.pogostick29dev.pogoball.cmds.Delete;
import me.pogostick29dev.pogoball.cmds.ForceStart;
import me.pogostick29dev.pogoball.cmds.ForceStop;
import me.pogostick29dev.pogoball.cmds.Join;
import me.pogostick29dev.pogoball.cmds.Leave;
import me.pogostick29dev.pogoball.cmds.Reload;
import me.pogostick29dev.pogoball.cmds.SetLocation;
import me.pogostick29dev.pogoball.cmds.SubCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

	private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
	
	public void setup() {
		commands.add(new Create());
		commands.add(new Delete());
		commands.add(new ForceStart());
		commands.add(new ForceStop());
		commands.add(new Join());
		commands.add(new Leave());
		commands.add(new Reload());
		commands.add(new SetLocation());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (!(sender instanceof Player)) {
			MessageManager.getInstance().severe(sender, "Only players can use PogoBall!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("pogoball")) {
			if (args.length == 0) {
				for (SubCommand c : commands) {
					MessageManager.getInstance().info(p, "/pogoball " + c.name() + " (" + aliases(c) + ")" + " - " + c.info());
				}
				return true;
			}
			
			SubCommand target = get(args[0]);
			
			if (target == null) {
				MessageManager.getInstance().severe(p, "/pogoball " + args[0] + " is not a valid subcommand!");
				return true;
			}
			
			ArrayList<String> a = new ArrayList<String>();
			a.addAll(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			try {
				target.onCommand(p, args);
			}
			
			catch (Exception e) {
				MessageManager.getInstance().severe(p, "An error has occured: " + e.getCause());
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	private String aliases(SubCommand cmd) {
		String fin = "";
		
		for (String a : cmd.aliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	private SubCommand get(String name) {
		for (SubCommand cmd : commands) {
			if (cmd.name().equalsIgnoreCase(name)) return cmd;
			for (String alias : cmd.aliases()) if (name.equalsIgnoreCase(alias)) return cmd;
		}
		return null;
	}
}