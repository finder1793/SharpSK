package me.sharpjaws.sharpSK.WorldEdit;

import org.bukkit.Bukkit;

import ch.njol.skript.Skript;

public class WorldEditRegistry {
	public static void registerWorldEdit() {
		 
	        if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
	        	//PirateSK merged features
	        	if (Bukkit.getPluginManager().getPlugin("PirateSK") == null){
	        	  Skript.registerEffect(EffPasteSchematic.class, new String[] { "[sharpsk] paste schematic %string% at %location%" }); 
	        	}
	        	  //-------------------------
	        }
		
	}

}
