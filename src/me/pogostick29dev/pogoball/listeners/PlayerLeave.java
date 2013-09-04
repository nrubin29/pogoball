package me.pogostick29dev.pogoball.listeners;

import me.pogostick29dev.pogoball.Arena;
import me.pogostick29dev.pogoball.ArenaManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Arena a = ArenaManager.getInstance().getArena(e.getPlayer());
		if (a == null) return;
		a.removePlayer(e.getPlayer(), false);
	}
}