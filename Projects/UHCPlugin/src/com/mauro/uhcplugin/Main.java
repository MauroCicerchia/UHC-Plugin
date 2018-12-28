package com.mauro.uhcplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	UHCMatch match = new UHCMatch();
	
	@Override
	public void onEnable() {
		getLogger().info("UHC plugin has been enabled.");
		PluginManager pm = getServer().getPluginManager();
		UHCListener listener = new UHCListener(this, match);
		pm.registerEvents(listener, this);
		UHCScoreboard.createScoreboard();
		CustomItems.initializeCustomItems();
		Bukkit.getWorld("world").setSpawnLocation(0, 78, 0);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("UHC plugin has been disabled.");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (sender instanceof Player) {
			String cmdTxt = cmd.getName();
			
			switch(cmdTxt) {
			
				case "start":
					if(!match.isInProgress())
						this.startGame(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
					else
						player.sendMessage("La partida ya esta en curso.");
					return true;
				
				case "restart":
					if(match.isInProgress()) {
						this.endGame();
						this.startGame(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
					} else
						player.sendMessage("La partida no esta en curso.");
					return true;
					
				case "end":
					if(match.isInProgress())
						this.endGame();
					else
						player.sendMessage("La partida no esta en curso.");
					return true;
					
				case "restore":			
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName() == args[0])
							UHCChat.sayAll("E");
							match.setForMatch(p);
					}
					return true;
				
				default:
					player.sendMessage("Comando no reconocido.");
					return true;
			}
			
		}
		
		player.sendMessage("Comando no reconocido.");
		return true;
		
	}
	
	public void startGame(int minutes, int radius) {
		match.startGame(radius, minutes, this);
	}
	
	public void endGame() {
		match.endGame();
	}
}
