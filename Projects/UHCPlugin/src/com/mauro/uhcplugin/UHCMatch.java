package com.mauro.uhcplugin;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class UHCMatch {

	ArrayList<Player> players = new ArrayList<Player>();
	boolean isInProgress = false;
	
	public void startGame(int radius, int minutes, Plugin plugin) {
		
		isInProgress = true;
		
		//Set players
		players.clear();
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			players.add(player);
			setForMatch(player);
			
			player.setScoreboard(UHCScoreboard.getScoreboard());		
			UHCScoreboard.updateHealth(player);
		}
		
		/*if(players.size() == 1) {
			checkWinner();
			return;
		}*/
		
		//Set za warudo
		WorldManager.setWorldBorder(radius);
		WorldManager.setWorldState();
		
		//TP Players
		WorldManager.tpPlayersToCorners();
		
		//Starts the timer
		TimeManager.startTimer(plugin, minutes);
	}

	public void setForMatch(Player player) {
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setStatistic(Statistic.TIME_SINCE_REST, 0);
		player.getInventory().clear();
		Iterator<Advancement> advancements = Bukkit.advancementIterator();
		while(advancements.hasNext()) {
			Advancement current = advancements.next();
			AdvancementProgress progress = player.getAdvancementProgress(current);
			for(String criteria : progress.getAwardedCriteria()) {
				progress.revokeCriteria(criteria);
			}
		}
		player.setExp(0);
		player.setLevel(0);
		player.setGameMode(GameMode.SURVIVAL);
	}

	public void playerDied(Player player) {
		players.remove(player);
		
		checkWinner();
	}
	
	private void checkWinner() {
		if(players.size() == 1) {
			Player winner = players.get(0);
			
			UHCChat.winnerMessage(winner);
			UHCChat.titleWinner(ChatColor.YELLOW, winner);
			UHCChat.playSound(Sound.UI_TOAST_CHALLENGE_COMPLETE);
			UHCChat.fireworks(winner);
			
			endGame();
		}
	}

	public boolean isInProgress() {
		return isInProgress;
	}

	public void endGame() {
		
		isInProgress = false;
		
		players.clear();
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			player.setGameMode(GameMode.SURVIVAL);
		}
			
		//Stops the timer
		TimeManager.endGame();
		
		//Reset the World Border
		WorldManager.resetWorldBorder();
		
		//Reset za warudo
		WorldManager.resetWorldState();
		
		
	}
}
