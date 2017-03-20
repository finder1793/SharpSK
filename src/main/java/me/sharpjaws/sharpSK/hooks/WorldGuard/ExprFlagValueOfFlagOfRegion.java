 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
 
 
 public class ExprFlagValueOfFlagOfRegion
   extends SimpleExpression<String>
 {
   private Expression<String> flag;
   private Expression<String> region;
   private Expression<?> world;
   
   
   @SuppressWarnings("unchecked")
public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
	 this.flag = (Expression<String>) expressions[0];
	 this.region = (Expression<String>) expressions[1];
     this.world = expressions[2];
     return true;
   }
   
   protected String[] get(Event event) {
	   World world = (World)this.world.getSingle(event);
	   if (world == null) {
		   for (RegionManager a : WGBukkit.getPlugin().getRegionContainer().getLoaded()) {
			 for  (Entry<String, ProtectedRegion> b :a.getRegions().entrySet()) {
				if ( b.getKey().equals(region.getSingle(event))) {
					world = Bukkit.getWorld(a.getName());
					break;
				}
				 
			 }
		   }
	   }
	   
  
     RegionManager rm = WGBukkit.getRegionManager(world);
    ProtectedRegion pregion = rm.getRegion(region.getSingle(event));
    String value = null;
    
    for (Entry<Flag<?>,Object> ra : pregion.getFlags().entrySet()){
    	if (ra.getKey().getName().equals(this.flag.getSingle(event))){
    	value = ra.getValue().toString();
    		break;
    		
    	}else {
    	return new String[] {};
    	}
    }
     

     return  new String[] {value};
   }
   
   public boolean isSingle() {
     return true;
   }
   
   public Class<? extends String> getReturnType() {
     return String.class;
   }
   
   public String toString(Event event, boolean b) {
     return "[(wg|worldguard)] flag value of flag %string% of wg region %string% in [world] %world%";
   }
   
   public Class<?>[] acceptChange(Changer.ChangeMode mode) {
     return null;
   }
 }


