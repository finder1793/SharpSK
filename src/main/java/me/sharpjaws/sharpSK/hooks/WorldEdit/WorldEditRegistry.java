package me.sharpjaws.sharpSK.hooks.WorldEdit;

import org.bukkit.Bukkit;

import ch.njol.skript.Skript;

public class WorldEditRegistry {
	public static void registerWorldEdit() {
		 
	        if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
	        	//PirateSK merged features
	        	if (Bukkit.getPluginManager().getPlugin("PirateSK") != null){
	        	  Skript.registerEffect(EffPasteSchematic.class, new String[] { "sharpsk [worldedit] paste schematic %string% at %location% [exclude air %-boolean%] [rotate [by] %-number% [degrees]]" }); 
	        	}else{
	        		  Skript.registerEffect(EffPasteSchematic.class, new String[] { "[sharpsk] [worldedit] paste schematic %string% at %location% [exclude air %-boolean%] [rotate [by] %-number% [degrees]]" }); 
	        	}
	        	  //-------------------------
	        	Skript.registerEffect(EffSaveClipToSchematic.class, new String[] { "[sharpsk] [worldedit] save clipboard of %player% (to|as) [schem[atic]] %string%" }); 
	        	Skript.registerEffect(EffSaveSelectionToClipboard.class, new String[] { "[sharpsk] [worldedit] save [selection] p[oint][ ]1 %location% p[oint][ ]2 %location% [with origin %-location%] to clip[board] of [player] %player%" }); 
	        	
	        }
		
	}
}
