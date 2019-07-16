package com.mauro.uhcplugin;

import org.bukkit.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;


public class TimeManager {
	
	static int episodeTime;
	
	static int timeElapsed = 0;
	static int nextEpisode = episodeTime;
	static int episodeNumber = 0;
	
	static boolean isActive = false;
	
	static int timerTaskID;
	
	public static void startTimer(Plugin plugin, int minutes) {
		
		
		isActive = true;
		
		episodeTime = minutes * 60;
		timeElapsed = 0;
		nextEpisode = episodeTime;
		episodeNumber = 0;
		
		//Update Timer
		UHCScoreboard.updateTime();
		
		//Countdown
		UHCChat.title("3", ChatColor.GREEN);
		UHCChat.playSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
		waitSeconds(1);
		UHCChat.title("2", ChatColor.GREEN);
		UHCChat.playSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
		waitSeconds(1);
		UHCChat.title("1", ChatColor.GREEN);
		UHCChat.playSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
		waitSeconds(1);
    	UHCChat.titleMain("Comienza UHC", ChatColor.LIGHT_PURPLE);
		UHCChat.playSound(Sound.UI_TOAST_CHALLENGE_COMPLETE);
		
		//Start episode 1
		nextEpisode(plugin);
		
		//Timer
		timerTaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                timeElapsed++;
                UHCScoreboard.updateTime();
                if(timeElapsed % episodeTime == 0) {
                	nextEpisode(plugin);
                }
            }
        }, 20, 20);
	}

	public static String episode() {
		return Integer.toString(episodeNumber);
	}
	
	public static String timeUntilNextEpisode() {
		return getFormatedTime(nextEpisode - timeElapsed);
	}

	public static String totalTime() {
		return getFormatedTime(timeElapsed);
	}
	
	private static void nextEpisode(Plugin plugin) {
		nextEpisode = timeElapsed + episodeTime;
		episodeNumber++;		
			
		UHCChat.sayAll(ChatColor.GREEN + "Comienza el episodio " + ChatColor.RED + "" + ChatColor.BOLD + episodeNumber);
		UHCChat.playSound(Sound.ENTITY_PLAYER_LEVELUP);
		
		switch(episodeNumber) {
		
			case 4: UHCChat.sayAll(ChatColor.RED + "Ha terminado el pacto de caballeros."); break;
			
			case 9: UHCChat.sayAll(ChatColor.RED + "¡Debes estar en 0,0 al finalizar este episodio!"); break;
			
			case 11: endGame(); return;
		}
	}
	
	public static void endGame() {
		
		isActive = false;
		
		episodeNumber = 0;
		timeElapsed = 0;
		nextEpisode = 0;
		
		UHCScoreboard.updateTime();
		
		Bukkit.getServer().getScheduler().cancelTask(timerTaskID);
		UHCChat.sayAll(ChatColor.AQUA + "Fin de la Partida");
	}
	
	private static String getFormatedTime(int seconds) {
	    int minutes = seconds / 60;
	    int hours = minutes / 60;

	    seconds -= minutes * 60;
	    minutes -= hours * 60;

	    return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}
	
	public static boolean isActive() {
		return isActive;
	}
	
	public static void waitSeconds(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
