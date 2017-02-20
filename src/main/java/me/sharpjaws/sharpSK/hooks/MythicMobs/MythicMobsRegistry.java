package me.sharpjaws.sharpSK.hooks.MythicMobs;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.sharpjaws.sharpSK.hooks.MythicMobs.old.CondIsMythicMobOld;
import me.sharpjaws.sharpSK.hooks.MythicMobs.old.CondNotMythicMobOld;
import me.sharpjaws.sharpSK.hooks.MythicMobs.old.EffSpawnMMOld;
import me.sharpjaws.sharpSK.hooks.MythicMobs.old.ExprEvtMMDropsOld;

public class MythicMobsRegistry {

	public static void RegisterMythicMobs(){
		if (!Bukkit.getPluginManager().getPlugin("MythicMobs").getDescription().getVersion().contains("4.0") && !Bukkit.getPluginManager().getPlugin("MythicMobs").getDescription().getVersion().contains("4.1")) {
		Skript.registerEvent("Mythicmob death", SimpleEvent.class, net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent.class,"([mythicmob|mm]) death");
		EventValues.registerEventValue( net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent.class, Location.class,
				new Getter<Location, net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent>() {
					@Override
					@Nullable
					public Location get(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent e) {
						Location l = e.getEntity().getLocation();
						return l;
					}
				}, 0);
		EventValues.registerEventValue(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent.class, String.class,
				new Getter<String,net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent>() {
					@Override
					@Nullable
					public String get(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent e) {
						String l = e.getMobType().getInternalName();
						return l;
					}
				}, 0);
		Skript.registerEvent("Mythicmob spawn", SimpleEvent.class, net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent.class,"([mythicmob|mm]) spawn");
		EventValues.registerEventValue(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent.class, Entity.class,
				new Getter<Entity, net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent>() {
					@Override
					@Nullable
					public Entity get(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent e) {
						Entity en = e.getEntity();
						return en;
					}
				}, 0);
		EventValues.registerEventValue(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent.class, String.class,
				new Getter<String, net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent>() {
					@Override
					@Nullable
					public String get(net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobSpawnEvent e) {
						String name = e.getMobType().getInternalName();
						return name;
					}
				}, 0);
		Skript.registerExpression(ExprEvtMMDropsOld.class, ItemStack.class, ExpressionType.SIMPLE, "[all] [event-]mmdrops");
		Skript.registerCondition(CondIsMythicMobOld.class, "%entity% is a mythicmob");
		Skript.registerCondition(CondNotMythicMobOld.class, "%entity% is not a mythicmob");
		Skript.registerEffect(EffSpawnMMOld.class, "[sharpsk] spawn [a] mythicmob %string% at [the] %location% with level %integer%");
	}else{
		Skript.registerEvent("Mythicmob death", SimpleEvent.class, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent.class,"([mythicmob|mm]) death");
		EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent.class, Location.class,
				new Getter<Location, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent>() {
					@Override
					@Nullable
					public Location get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent e) {
						Location l = e.getEntity().getLocation();
						return l;
					}
				}, 0);
		EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent.class, String.class,
				new Getter<String, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent>() {
					@Override
					@Nullable
					public String get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent e) {
						String l = e.getMobType().getInternalName();
						return l;
					}
				}, 0);
		Skript.registerEvent("Mythicmob spawn", SimpleEvent.class, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent.class,"([mythicmob|mm]) spawn");
		EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent.class, Entity.class,
				new Getter<Entity, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent>() {
					@Override
					@Nullable
					public Entity get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent e) {
						Entity en = e.getEntity();
						return en;
					}
				}, 0);
		EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent.class, String.class,
				new Getter<String, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent>() {
					@Override
					@Nullable
					public String get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent e) {
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
}
