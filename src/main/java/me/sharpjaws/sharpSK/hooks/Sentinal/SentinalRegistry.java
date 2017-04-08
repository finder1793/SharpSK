package me.sharpjaws.sharpSK.hooks.Sentinal;

import org.mcmonkey.sentinel.events.SentinelAttackEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;

public class SentinalRegistry {

	public static void RegisterSentinal() {
		Skript.registerEvent("Sentinel Target Event", SimpleEvent.class, SentinelAttackEvent.class, "[sentinel] target [event]");
		
	}
	
}
