package me.pogostick29dev.pogoball;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager {

	private MessageManager() { }
	
	private static MessageManager instance = new MessageManager();
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	private String prefix = ChatColor.GREEN + "[" + ChatColor.AQUA + "PogoBall" + ChatColor.GREEN + "] ";
	
	public void info(CommandSender s, String msg) {
		msg(s, ChatColor.YELLOW, msg);
	}
	
	public void severe(CommandSender s, String msg) {
		msg(s, ChatColor.RED, msg);
	}
	
	public void good(CommandSender s, String msg) {
		msg(s, ChatColor.GREEN, msg);
	}
	
	private void msg(CommandSender s, ChatColor color, String msg) {
		s.sendMessage(prefix + color + msg);
	}
}