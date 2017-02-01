 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
 
 public class ExprGetPoint2 extends ch.njol.skript.lang.util.SimpleExpression<Location>
 {
   private Expression<?> region;
   private Expression<?> world;
   
   public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
     this.region = expressions[0];
     this.world = expressions[1];
     return true;
   }
   
   protected Location[] get(Event event) {
     String name = (String)this.region.getSingle(event);
     World world = (World)this.world.getSingle(event);
     
     RegionManager regionManager = WGBukkit.getRegionManager(world);
     if (!regionManager.hasRegion(name)) {
       Skript.error("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
       return null;
     }
     
     double x = regionManager.getRegion(name).getMaximumPoint().getX();
     double y = regionManager.getRegion(name).getMaximumPoint().getY();
     double z = regionManager.getRegion(name).getMaximumPoint().getZ();
     
     Location pos1 = new Location(world, x, y, z);
     
     return new Location[] { pos1 };
   }
   
   public boolean isSingle() {
     return true;
   }
   
   public Class<? extends Location> getReturnType() {
     return Location.class;
   }
   
   public String toString(Event event, boolean b) {
     return "pos2 of wg region \"" + this.region.getSingle(event) + "\" in world \"" + ((World)this.world.getSingle(event)).getName() + "\"";
   }
   
   public Class<?>[] acceptChange(Changer.ChangeMode mode) {
     return null;
   }
 }


