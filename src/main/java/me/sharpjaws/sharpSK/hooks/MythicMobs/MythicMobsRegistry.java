package me.sharpjaws.sharpSK.hooks.MythicMobs;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent;
import net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent;
public class MythicMobsRegistry {

	public static void RegisterMythicMobs(){
		Skript.registerEvent("Mythicmob death", SimpleEvent.class, MythicMobDeathEvent.class,"([mythicmob|mm]) death");
		EventValues.registerEventValue(MythicMobDeathEvent.class, Location.class,
				new Getter<Location, MythicMobDeathEvent>() {
					@Override
					@Nullable
					public Location get(MythicMobDeathEvent e) {
						Location l = e.getEntity().getLocation();
						return l;
					}
				}, 0);
		EventValues.registerEventValue(MythicMobDeathEvent.class, String.class,
				new Getter<String, MythicMobDeathEvent>() {
					@Override
					@Nullable
					public String get(MythicMobDeathEvent e) {
						String l = e.MobName;
						return l;
					}
				}, 0);
		Skript.registerEvent("Mythicmob spawn", SimpleEvent.class, MythicMobSpawnEvent.class,"([mythicmob|mm]) spawn");
		EventValues.registerEventValue(MythicMobSpawnEvent.class, Entity.class,
				new Getter<Entity, MythicMobSpawnEvent>() {
					@Override
					@Nullable
					public Entity get(MythicMobSpawnEvent e) {
						Entity en = e.getEntity();
						return en;
					}
				}, 0);
		EventValues.registerEventValue(MythicMobSpawnEvent.class, String.class,
				new Getter<String, MythicMobSpawnEvent>() {
					@Override
					@Nullable
					public String get(MythicMobSpawnEvent e) {
						String name = e.getMobType().getInternalName();
						return name;
					}
				}, 0);
		Skript.registerExpression(ExprEvtMMDrops.class, ItemStack.class, ExpressionType.SIMPLE, "[all] [event-]mmdrops");
		Skript.registerCondition(CondIsMythicMob.class, "%entity% is a mythicmob");
		Skript.registerCondition(CondNotMythicMob.class, "%entity% is not a mythicmob");
		Skript.registerEffect(EffSpawnMM.class, "[sharpsk] spawn [a] mythicmob %string% at [the] %location% with level %integer%");
	}
	
}
