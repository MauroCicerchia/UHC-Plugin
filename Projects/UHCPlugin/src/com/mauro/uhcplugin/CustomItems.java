package com.mauro.uhcplugin;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;


public class CustomItems implements Listener {
	
	static Plugin plugin = Main.getPlugin(Main.class);
	
	public static void initializeCustomItems() {
		
		ItemStack glisteringMelon = new ItemStack(Material.GLISTERING_MELON_SLICE);
		
		removeRecipe(plugin.getServer().getRecipesFor(glisteringMelon).get(0));
		
		ShapelessRecipe melonRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "glistering_melon"), glisteringMelon);
		melonRecipe.addIngredient(Material.MELON_SLICE);
		melonRecipe.addIngredient(Material.GOLD_BLOCK);
		plugin.getServer().addRecipe(melonRecipe);
		
		ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE);
		
		ShapedRecipe goldenAppleRecipe = new ShapedRecipe(new NamespacedKey(plugin, "golden_apple") ,goldenApple);
		goldenAppleRecipe.shape("ggg", 
								"ghg", 
								"ggg");
		goldenAppleRecipe.setIngredient('g', Material.GOLD_INGOT);
		goldenAppleRecipe.setIngredient('h', Material.PLAYER_HEAD);
		plugin.getServer().addRecipe(goldenAppleRecipe);
	}
	
	private static void removeRecipe(Recipe recipe){
		Iterator<Recipe> iterator = plugin.getServer().recipeIterator();
	    while(iterator.hasNext()){
	        Recipe r = iterator.next();
	       
	        if(r != null && r.getResult().getType() == Material.GLISTERING_MELON_SLICE){
	            iterator.remove();
	        }
	       
	    }
	}
}