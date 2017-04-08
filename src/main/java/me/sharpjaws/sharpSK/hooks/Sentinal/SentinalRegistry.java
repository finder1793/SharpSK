package me.sharpjaws.sharpSK.hooks.Sentinal;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.mcmonkey.sentinel.events.SentinelAttackEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class SentinalRegistry {

	public static void RegisterSentinal() {
		Skript.registerEvent("Sentinel Target Event", SimpleEvent.class, SentinelAttackEvent.class, "[sentinel] target [event]");
		EventValues.registerEventValue(SentinelAttackEvent.class, Entity.class,
				new Getter<Entity,SentinelAttackEvent>() {
					@Override
					@Nullable
					public Entity get( SentinelAttackEvent e) {	
						Entity en = e.getNPC().getNavigator().getEntityTarget().getTarget();
						return en;
					}
				}, 0);
		EventValues.registerEventValue(SentinelAttackEvent.class, String.class,
				new Getter<String,SentinelAttackEvent>() {
					@Override
					@Nullable
					public String get( SentinelAttackEvent e) {	
						String st = e.getNPC().getName();
						return st;
					}
				}, 0);
		
	}
	
}
