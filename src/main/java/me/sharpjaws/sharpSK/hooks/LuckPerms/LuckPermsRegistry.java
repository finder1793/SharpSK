package me.sharpjaws.sharpSK.hooks.LuckPerms;

import org.bukkit.event.Event;

import com.gamingmesh.jobs.api.JobsJoinEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import me.lucko.luckperms.api.event.group.GroupCreateEvent;

public class LuckPermsRegistry {

	public static void registerLuckPerms() {
		//Events

		
		
		//Effects
		
		Skript.registerEffect(EffLuckPermsSetPerm.class, "[sharpsk] luckperms set permission %string% to %boolean% for [player] %offlineplayer%");
		Skript.registerEffect(EffLuckPermsUnsetPerm.class, "[sharpsk] luckperms unset permission %string% for [player] %offlineplayer%");
		//Expressions
		
		
	}
}
