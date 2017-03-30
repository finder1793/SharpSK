package me.sharpjaws.sharpSK.hooks.LightAPI;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ru.beykerykt.lightapi.events.DeleteLightEvent;
import ru.beykerykt.lightapi.events.SetLightEvent;

public class LightAPIRegistry {

	public static void registerLightAPI(){		
			
		Skript.registerEvent("Light Create", SimpleEvent.class, SetLightEvent.class,
					"[on] light create[d]");
			EventValues.registerEventValue( SetLightEvent.class, Location.class, new Getter<Location, SetLightEvent>() {
	            @Nullable
	            @Override
	            public Location get(SetLightEvent e) {
	            	Location loc = new Location(e.getWorld(), e.getX(), e.getY(), e.getZ());
	                return loc;
	            }
	        }, 0);
			EventValues.registerEventValue( SetLightEvent.class, World.class, new Getter<World, SetLightEvent>() {
	            @Nullable
	            @Override
	            public World get(SetLightEvent e) {
	                return e.getWorld();
	            }
	        }, 0);
			
			Skript.registerEvent("Light Remove", SimpleEvent.class, DeleteLightEvent.class, "[on] light (remove|delete)[d]");
			EventValues.registerEventValue( DeleteLightEvent.class, Location.class, new Getter<Location, DeleteLightEvent>() {
	            @Nullable
	            @Override
	            public Location get(DeleteLightEvent e) {
	            	Location loc = new Location(e.getWorld(), e.getX(), e.getY(), e.getZ());
	                return loc;
	            }
	        }, 0);
			EventValues.registerEventValue( DeleteLightEvent.class, World.class, new Getter<World, DeleteLightEvent>() {
	            @Nullable
	            @Override
	            public World get(DeleteLightEvent e) {
	                return e.getWorld();
	            }
	        }, 0);
	
			Skript.registerExpression(ExprEvtLightLevel.class, Number.class, ExpressionType.SIMPLE, "event-lightlevel");
			Skript.registerEffect(EffCreateLight.class, "[(sharpsk|lightapi)] (create|make) [a] [fake] light [source] at [the] %location% [with] [light[ ]level] %integer% [using] [async] %boolean%");
			Skript.registerEffect(EffDeleteLight.class, "[(sharpsk|lightapi)] (delete|remove) light [source] at %location% [async] %boolean%");

	}
}

	

