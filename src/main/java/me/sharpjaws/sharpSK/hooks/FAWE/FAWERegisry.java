package me.sharpjaws.sharpSK.hooks.FAWE;

import ch.njol.skript.Skript;

public class FAWERegisry {
	public static void registerFAWE(){
		Skript.registerEffect(EffFAWEPasteSchematic.class, "[sharpsk] (fawe|fastasyncworldedit) paste schematic %string% at %location% [exclude air %-boolean%]");
	}

}
