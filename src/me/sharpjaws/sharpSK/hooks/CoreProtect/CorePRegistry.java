package me.sharpjaws.sharpSK.hooks.CoreProtect;

import org.bukkit.event.player.PlayerChangedWorldEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import net.coreprotect.CoreProtect;

public class CorePRegistry {

	public static void registerCoreP(){
		Skript.registerEffect(EffRollback.class, "[(coreprotect|cp)] (rollback|revert|undo) [(0¦removal|1¦placement|2¦interaction)] changes [made by %-offlineplayers%] at [the] %location% in radius %number% [back] to %timespan% [ago] [and] [exclude %-itemstacks%]");
		Skript.registerEffect(EffRestore.class, "[(coreprotect|cp)] restore [(0¦removal|1¦placement|2¦interaction)] [rollback] changes [made by %-offlineplayers%] at [the] %location% in radius %number% from %timespan% [ago] [and] [exclude %-itemstacks%]");
	}
	
}