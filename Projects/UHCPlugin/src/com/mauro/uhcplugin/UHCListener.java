package com.mauro.uhcplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class UHCListener implements Listener {

	Plugin plugin;
	UHCMatch match;
	
	//Constructor
	public UHCListener(Main plugin, UHCMatch match) {
		this.plugin = plugin;
		this.match = match;
	}
	
	//EventHandler
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {	
		Player player = event.getPlayer();
		if(match.isInProgress())
			player.setScoreboard(UHCScoreboard.getScoreboard());
	}
	
	@EventHandler
	public void onDamageTaken(EntityDamageEvent event) {	
		if (event.getEntity() instanceof Player) {
			UHCScoreboard.updateHealth((Player) event.getEntity());
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		String message = event.getMessage();
		Player sender = event.getPlayer();
		
		if(message.startsWith("!")) {
			UHCChat.sayGlobal(message.substring(1), sender);
		} else {
			UHCChat.sayPrivate(message, sender);
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		
		if(match.isInProgress()) {
			
			match.playerDied(player);
			
			UHCChat.playSound(Sound.BLOCK_CONDUIT_DEACTIVATE);
			
			WorldManager.placeHead(player);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		event.setRespawnLocation(new Location(Bukkit.getWorld("world"), 0, 78, 0));
		if(match.isInProgress()) {
			event.getPlayer().setGameMode(GameMode.SPECTATOR);
			UHCChat.titleMainPrivate("Muerto", ChatColor.DARK_RED, event.getPlayer());
		}		
	}
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent event) {
		if(event.getEntity().getType() == EntityType.GHAST) {
			for(ItemStack item : event.getDrops()) {
				if(item.getType() == Material.GHAST_TEAR) {
					ItemStack gold = new ItemStack(Material.GOLD_INGOT, item.getAmount());
					event.getDrops().remove(item);
					event.getDrops().add(gold);
				}
			}
		}
	}
	
}
