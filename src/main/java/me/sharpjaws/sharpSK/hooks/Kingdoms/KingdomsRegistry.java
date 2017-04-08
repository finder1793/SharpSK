package me.sharpjaws.sharpSK.hooks.Kingdoms;

import org.kingdoms.events.KingdomCreateEvent;
import org.kingdoms.events.KingdomDeleteEvent;
import org.kingdoms.events.KingdomMemberJoinEvent;
import org.kingdoms.events.KingdomMemberLeaveEvent;
import org.kingdoms.events.KingdomPlayerLostEvent;
import org.kingdoms.events.KingdomPlayerWonEvent;
import org.kingdoms.events.KingdomResourcePointChangeEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;

public class KingdomsRegistry {

	public static void RegisterKingdoms() {
		//Kingdoms Events
		Skript.registerEvent("Kingdoms Kingdom Create" , SimpleEvent.class, KingdomCreateEvent.class, "[kingdoms] kingdom create[d]");
		Skript.registerEvent("Kingdoms Kingdom Delete" , SimpleEvent.class, KingdomDeleteEvent.class, "[kingdoms] kingdom delete[d]");
		Skript.registerEvent("Kingdoms Kingdom Member Join" , SimpleEvent.class, KingdomMemberJoinEvent.class, "[kingdoms] kingdom member join[ed]");
		Skript.registerEvent("Kingdoms Kingdom Member Leave" , SimpleEvent.class, KingdomMemberLeaveEvent.class, "[kingdoms] kingdom member leave[d]");
		Skript.registerEvent("Kingdoms Player Lose" , SimpleEvent.class, KingdomPlayerLostEvent.class, "[kingdoms] champion [player] (lose|defeat)");
		Skript.registerEvent("Kingdoms Player Win" , SimpleEvent.class, KingdomPlayerWonEvent.class, "[kingdoms] champion [player]  win");
		Skript.registerEvent("Kingdoms Resource Point Change" , SimpleEvent.class, KingdomResourcePointChangeEvent.class, "[kingdoms] [resource] point[s] change[d]");
		
		//Kingdoms Expressions
		
		
		//Kingdoms Effects:
		
	}
	
}
