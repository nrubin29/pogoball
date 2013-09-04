package me.pogostick29dev.pogoball.listeners;

import me.pogostick29dev.pogoball.ArenaManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (ArenaManager.getInstance().getArena(e.getPlayer()) != null) e.setCancelled(true);
	}
}