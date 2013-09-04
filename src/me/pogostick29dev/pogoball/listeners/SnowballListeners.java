package me.pogostick29dev.pogoball.listeners;

import me.pogostick29dev.pogoball.Arena;
import me.pogostick29dev.pogoball.ArenaManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SnowballListeners implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR) && !(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (ArenaManager.getInstance().getArena(e.getPlayer()) == null) return;
		if (e.getItem().getType() == Material.SNOW_BALL) {
			e.setCancelled(true);
			e.setUseItemInHand(Result.DENY);
			e.getPlayer().launchProjectile(Snowball.class);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
			Player p = (Player) e.getEntity();
			Snowball s = (Snowball) e.getDamager();
			
			if (!(s.getShooter() instanceof Player)) return;
			Player shooter = (Player) s.getShooter();
			
			if (ArenaManager.getInstance().getArena(p) == null && ArenaManager.getInstance().getArena(shooter) == null) return;
			Arena a = ArenaManager.getInstance().getArena(p);
			if (a.getTeam(p) == a.getTeam(shooter)) return;
			p.damage(5.0);
		}
	}
	
	@EventHandler
	public void onPlayerRegen(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (ArenaManager.getInstance().getArena(p) == null) return;
			e.setAmount(0.0);
			e.setCancelled(true);
		}
	}
}