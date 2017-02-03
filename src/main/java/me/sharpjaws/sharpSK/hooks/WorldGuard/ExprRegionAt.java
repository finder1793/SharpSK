 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
 
 public class ExprRegionAt extends SimpleExpression<String>
 {
   private Expression<?> loc;
   
   public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
     this.loc = expressions[0];
     return true;
   }
   
   protected String[] get(Event event) {
	     Location loc = (Location)this.loc.getSingle(event);
	     String a = null;
	     WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	     RegionManager regionManager = wg.getRegionManager(loc.getWorld());
	     
	    for (ProtectedRegion reg : regionManager.getApplicableRegions(loc)){
	    	if (reg.contains((int)loc.getBlockX(),(int)loc.getBlockY(),(int)loc.getBlockZ())){
	    		a = reg.getId();
	    	}
	    }
	     return new String[]{a};
	   }
	   
	   public boolean isSingle() {
	     return false;
	   }
	   
	   public Class<? extends String> getReturnType() {
	     return String.class;
	   }
	   
	   public String toString(Event event, boolean b) {
	     return "wg region at" + this.loc.getSingle(event).toString();
	   }
	   
	
	   
	 }


