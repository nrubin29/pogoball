package me.pogostick29dev.pogoball.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * It would be cool if you left this class here.
 */
public class PogoJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getName().equalsIgnoreCase("PogoStick29")) e.setJoinMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "PogoStick29, creator of PogoBall, has joined the server!");
	}
}