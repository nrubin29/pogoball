package me.pogostick29dev.pogoball;

import me.pogostick29dev.pogoball.listeners.ArmorRemove;
import me.pogostick29dev.pogoball.listeners.BlockBreak;
import me.pogostick29dev.pogoball.listeners.LobbySign;
import me.pogostick29dev.pogoball.listeners.PlayerDamage;
import me.pogostick29dev.pogoball.listeners.PlayerDeath;
import me.pogostick29dev.pogoball.listeners.PlayerLeave;
import me.pogostick29dev.pogoball.listeners.PlayerLoseHunger;
import me.pogostick29dev.pogoball.listeners.PogoJoin;
import me.pogostick29dev.pogoball.listeners.SnowballListeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

public class PogoBall extends JavaPlugin {

	public void onEnable() {
		SettingsManager.getInstance().setup(this);
		
		ArenaManager.getInstance().setup();
		
		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("pogoball").setExecutor(cm);
		
		Bukkit.getServer().getPluginManager().registerEvents(new ArmorRemove(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LobbySign(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDamage(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLoseHunger(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PogoJoin(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SnowballListeners(), this);
	}
	
	public void onDisable() {
		for (Arena a : ArenaManager.getInstance().getArenas()) {
			a.stop(null);
		}
	}
}