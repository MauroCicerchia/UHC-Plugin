package com.mauro.uhcplugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class UHCChat {
	public static void say(String text, Player player) {
		player.sendMessage(ChatColor.BLUE + "[UHC]" + ChatColor.DARK_GRAY + " >> " + text);
	}
	
	public static void sayAll(String text) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			say(text, player);
		}
	}
	
	public static void sayPrivate(String text, Player sender) {
		sender.sendMessage(ChatColor.YELLOW + sender.getDisplayName() + ChatColor.GRAY + ": " + text);
	}
	
	public static void sayGlobal(String text, Player sender) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "GLOBAL " + ChatColor.RESET + "" + ChatColor.YELLOW + sender.getDisplayName() + ChatColor.WHITE + ": " + text);
		}
	}
	
	public static void playSound(Sound sound) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(player.getLocation(), sound, 10, 1);
		}
	}
	
	public static void titlePrivate(String text, ChatColor color, Player player) {
		player.sendTitle(color + "" + ChatColor.BOLD + text, "", 3, 15, 2);
	}
	
	public static void title(String text, ChatColor color) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			titlePrivate(text, color, player);
		}
	}
	
	public static void titleMainPrivate(String text, ChatColor color, Player player) {
		player.sendTitle(color + "" + ChatColor.BOLD + text, "", 3, 40, 10);
	}
	
	public static void titleMain(String text, ChatColor color) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			titleMainPrivate(text, color, player);
		}
	}
	
	public static void titleWinner(ChatColor color, Player winner) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendTitle(color + "" + ChatColor.BOLD + "GANADOR:", winner.getDisplayName(), 3, 40, 10);
		}
	}
	
	public static void winnerMessage(Player winner) {
		sayAll(ChatColor.WHITE + "¡El ganador es " + ChatColor.YELLOW + ChatColor.BOLD + winner.getName() + ChatColor.RESET + "!");
	}

	public static void fireworks(Player winner) {
		
		new BukkitRunnable()
		{		
			int i = 7;
		    @Override
		    public void run()
		    {
		        if(i > 0)
		        {
		        	//Spawn the Firework, get the FireworkMeta.
		            Firework fw = (Firework) winner.getWorld().spawnEntity(winner.getLocation(), EntityType.FIREWORK);
		            FireworkMeta fwm = fw.getFireworkMeta();
		           
		            //Our random generator
		            Random r = new Random();   
		 
		            //Get the type
		            int rType = r.nextInt(4) + 1;
		            Type type = Type.BALL;       
		            if (rType == 1) type = Type.BALL;
		            if (rType == 2) type = Type.BALL_LARGE;
		            if (rType == 3) type = Type.BURST;
		            if (rType == 4) type = Type.CREEPER;
		            if (rType == 5) type = Type.STAR;
		           
		            //Get our random colours   
		            int rColor1 = r.nextInt(17) + 1;
		            int rColor2 = r.nextInt(17) + 1;
		            Color color1 = getColor(rColor1);
		            Color color2 = getColor(rColor2);
		           
		            //Create our effect with this
		            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(color1).withFade(color2).with(type).trail(r.nextBoolean()).build();
		           
		            //Then apply the effect to the meta
		            fwm.addEffect(effect);
		           
		            //Generate some random power and set it
		            int rp = r.nextInt(2) + 1;
		            fwm.setPower(rp);
		           
		            //Then apply this to our rocket
		            fw.setFireworkMeta(fwm);    
		        	i--;
		        } else {
		        	this.cancel();
		        }
		    }
		}.runTaskTimer(Main.getPlugin(Main.class), 0, 20);	
	}
	
	private static Color getColor(int i) {
		switch(i) {
			case 1:
				return Color.AQUA;
			case 2:
				return Color.BLACK;
			case 3:
				return Color.BLUE;
			case 4:
				return Color.FUCHSIA;
			case 5:
				return Color.GRAY;
			case 6:
				return Color.GREEN;
			case 7:
				return Color.LIME;
			case 8:
				return Color.MAROON;
			case 9:
				return Color.NAVY;
			case 10:
				return Color.OLIVE;
			case 11:
				return Color.ORANGE;
			case 12:
				return Color.PURPLE;
			case 13:
				return Color.RED;
			case 14:
				return Color.SILVER;
			case 15:
				return Color.TEAL;
			case 16:
				return Color.WHITE;
			default:
				return Color.YELLOW;
		}
	}
}
