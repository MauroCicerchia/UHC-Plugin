package com.mauro.uhcplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class UHCScoreboard {

	static ScoreboardManager mng;
	static Scoreboard sb;
	
	public static void updateTime() {
		clearSidebar();
		setScore("  ", 																							5);	
		setScore(ChatColor.WHITE + "Episodio: " + ChatColor.GREEN + TimeManager.episode(), 						4);
		setScore(ChatColor.WHITE + "Siguiente en: " + ChatColor.RED + TimeManager.timeUntilNextEpisode(), 		3);
		setScore(" ", 																							2);
		setScore(ChatColor.WHITE + "Tiempo total: " + ChatColor.AQUA + TimeManager.totalTime(), 				1);		
	}

	public static void updateHealth(Player player) {
		player.setHealth(player.getHealth());
	}
	
	public static Scoreboard getScoreboard() {
		return sb;
	}
	
	public static void createScoreboard() {
		mng = Bukkit.getScoreboardManager();
		sb = mng.getNewScoreboard();
		
		Objective sidebar = sb.registerNewObjective("sidebar", "dummy", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "UHC Argentina T1");
		sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Objective health = sb.registerNewObjective("health", "health", "/ 20");
		health.setDisplaySlot(DisplaySlot.BELOW_NAME);
		
		Objective healthTab = sb.registerNewObjective("healthTab", "health", "", RenderType.HEARTS);
		healthTab.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		
		updateTime();
	}
	
	private static void setScore(String text, int score) {
		Objective sidebar = sb.getObjective("sidebar");
		
		sidebar.getScore(text).setScore(score);
	}
	
	private static void clearSidebar() {
		sb.getObjective("sidebar").unregister();
		Objective sidebar = sb.registerNewObjective("sidebar", "dummy", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "UHC Argentina T1");
		sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
}
