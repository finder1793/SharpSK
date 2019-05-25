package me.sharpjaws.sharpsk.hooks.MythicMobs;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.sharpjaws.sharpsk.hooks.MythicMobs.old.MythicMobsRegistryOld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class MythicMobsRegistry {

    public static void RegisterMythicMobs() {
        if (!Bukkit.getPluginManager().getPlugin("MythicMobs").getDescription().getVersion().matches("4.\\d.\\d.*")) {
            MythicMobsRegistryOld.RegisterMythicMobsOld();
        } else {
            Skript.registerEvent("Mythicmob death", SimpleEvent.class,
                    io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent.class, "([mythicmob|mm]) death");
            EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent.class,
                    Location.class,
                    new Getter<Location, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent>() {
                        @Override
                        @Nullable
                        public Location get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent e) {
                            return e.getEntity().getLocation();
                        }
                    }, 0);
            EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent.class,
                    String.class,
                    new Getter<String, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent>() {
                        @Override
                        @Nullable
                        public String get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent e) {
                            return e.getMobType().getInternalName();
                        }
                    }, 0);
            Skript.registerEvent("Mythicmob spawn", SimpleEvent.class,
                    io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent.class, "([mythicmob|mm]) spawn");
            EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent.class,
                    Entity.class,
                    new Getter<Entity, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent>() {
                        @Override
                        @Nullable
                        public Entity get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent e) {
                            return e.getEntity();
                        }
                    }, 0);
            EventValues.registerEventValue(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent.class,
                    String.class,
                    new Getter<String, io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent>() {
                        @Override
                        @Nullable
                        public String get(io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent e) {
                            return e.getMobType().getInternalName();
                        }
                    }, 0);
            Skript.registerExpression(ExprEvtMMDrops.class, ItemStack.class, ExpressionType.SIMPLE,
                    "[all] [event-]mmdrops");
            Skript.registerCondition(CondIsMythicMob.class, "%entity% is a mythicmob");
            Skript.registerCondition(CondNotMythicMob.class, "%entity% is not a mythicmob");
            Skript.registerEffect(EffSpawnMM.class,
                    "[sharpsk] spawn [a] mythicmob %string% at [the] %location% [with level %-number%]");

        }
    }
}
