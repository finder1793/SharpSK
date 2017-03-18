 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.event.Event;

import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
 
 
 public class ExprAllMembers
   extends SimpleExpression<String>
 {
   private Expression<String> region;
   private Expression<?> world;
   
   
   @SuppressWarnings("unchecked")
public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
	 this.region = (Expression<String>) expressions[0];
     this.world = expressions[1];
     return true;
   }
   
   protected String[] get(Event event) {
     World world = (World)this.world.getSingle(event);
     
     RegionManager rm = WGBukkit.getRegionManager(world);
    ProtectedRegion pregion = rm.getRegion(region.getSingle(event));
     List<String> list = new ArrayList<String>(pregion.getMembers().getPlayers());
     
     String[] s = new String[list.size()];
     return (String[])list.toArray(s);
   }
   
   public boolean isSingle() {
     return false;
   }
   
   public Class<? extends String> getReturnType() {
     return String.class;
   }
   
   public String toString(Event event, boolean b) {
     return "[(wg|worldguard)] [all] members of wg region %string% in %world%";
   }
   
   public Class<?>[] acceptChange(Changer.ChangeMode mode) {
     return null;
   }
 }


