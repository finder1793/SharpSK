package me.sharpjaws.sharpSK.hooks.LogBlock;

import ch.njol.skript.Skript;

public class LogBlockRegistry {

	public static void registerLogBlock() {
		Skript.registerEffect(EffQueueLog.class, "logblock queue block (break|place|replace) from %player% at %location% ");
	}
	
}
