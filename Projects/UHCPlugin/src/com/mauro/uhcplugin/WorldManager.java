package com.mauro.uhcplugin;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

public class WorldManager {
	
	static World world = Bukkit.getWorld("world");
	static World nether = Bukkit.getWorld("world_nether");
	static World end = Bukkit.getWorld("world_the_end");
	static int offset = 5;
	
	public static void setWorldBorder(int radius) {
		WorldBorder border = world.getWorldBorder();
		border.setSize(radius * 2);
		border.setCenter(0, 0);
	}
	
	public static void resetWorldBorder() {
		WorldBorder border = world.getWorldBorder();
		border.reset();
	}
	
	public static void tpPlayersToCorners() {
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(calculateCorner(1, 1));
		locations.add(calculateCorner(-1, 1));
		locations.add(calculateCorner(-1, -1));
		locations.add(calculateCorner(1, -1));
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			Location location = locations.get(new Random().nextInt(locations.size()));
			
			while(!world.getChunkAt(location).isLoaded())
				world.loadChunk(world.getChunkAt(location));
			
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
				
				// Values of change in distance (make it relative)
		        int dx = Integer.signum((int) location.getX());				//WTF!!!
		        int dz = Integer.signum((int) location.getZ());
		        
		        // Set yaw
		        if (dx != 0) {
		            // Set yaw start value based on dx
		            if (dx < 0) {
		            	UHCChat.sayAll("Looking towards 2 PI");
		                location.setYaw((float) (2 * Math.PI));
		            } else {
		                location.setYaw((float) (Math.PI));
		                UHCChat.sayAll("Looking towards PI");
		            }
		            //location.setYaw((float) location.getYaw() - (float) Math.atan(dz / dx));
		        } else if (dz < 0) {
		            location.setYaw((float) (1.5 * Math.PI));
		            UHCChat.sayAll("Looking towards 1.5 PI");
		        } else {
		        	location.setYaw((float) (0.5 * Math.PI));
		        	UHCChat.sayAll("Looking towards 0.5 PI");
		        }

		        // Set values, convert to degrees (invert the yaw since Bukkit uses a different yaw dimension format)
		        location.setYaw(location.getYaw() * 180f / (float) Math.PI);
		        
		        player.teleport(location);
			}, 5);
			
			locations.remove(location);
		}
		
		return;
	}
	
	public static void setWorldState() {
		world.setTime(0);
		world.setStorm(false);
		world.setDifficulty(Difficulty.HARD);
		nether.setDifficulty(Difficulty.HARD);
		end.setDifficulty(Difficulty.HARD);
		world.setGameRule(GameRule.NATURAL_REGENERATION, false);
		nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
		end.setGameRule(GameRule.NATURAL_REGENERATION, false);
	}
	
	public static void resetWorldState() {
		world.setGameRule(GameRule.NATURAL_REGENERATION, true);
		nether.setGameRule(GameRule.NATURAL_REGENERATION, true);
		end.setGameRule(GameRule.NATURAL_REGENERATION, true);
	}

	private static Location calculateCorner(int i, int j) {
		WorldBorder border = world.getWorldBorder();
		double radius = border.getSize() / 2;
		int x = (int) (radius - offset) * i;
		int z = (int) (radius - offset) * j;
		return new Location(world, x, world.getHighestBlockYAt(x, z), z);
	}

	public static void placeHead(Player player) {
		Location l = player.getLocation();
		
		Block b = l.getBlock();
		b.setType(Material.OAK_FENCE);
		l.setY(l.getY() + 1);
		b = l.getBlock();
		b.setType(Material.PLAYER_HEAD);
		
		Skull skull = (Skull) b.getState();
		skull.setOwningPlayer(player);
		
		skull.update();
	}

	public static boolean allChunksLoaded() {
		boolean output = true;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			output = output && p.getLocation().getChunk().isLoaded();
		}
		
		return output;
	}
}
