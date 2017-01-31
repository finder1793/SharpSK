package me.sharpjaws.sharpSK.WorldEdit;

import org.bukkit.Bukkit;

import ch.njol.skript.Skript;

public class WorldEditRegistry {
	public static void registerWorldEdit() {
		 
	        if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
	            Skript.registerEffect(EffPasteSchematic.class,
	                    "paste schem[atic] %string% at %location% [(ignor(e|ing)|without|[with] no) air]",
	                    "paste schem[atic] %string% at %location% with air");
	            Skript.registerEffect(EffSaveSchematic.class, "save blocks between %location% and %location% to [schem[atic]] [file] %string%");
	        }
		
	}

}
