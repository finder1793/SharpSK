 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import java.util.Iterator;
import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import ch.njol.skript.classes.Changer;
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
	     
	     String rg = null;
	     
	     ApplicableRegionSet set = WGBukkit.getRegionManager(loc.getWorld()).getApplicableRegions(loc);
	     LinkedList<String> parentNames = new LinkedList();
	     LinkedList<String> regions = new LinkedList();
	     Iterator localIterator = set.iterator();
	     ProtectedRegion parent; for (Iterator local; localIterator.hasNext(); parent = null)
	     {
	       ProtectedRegion region = (ProtectedRegion)localIterator.next();
	       String id = region.getId();
	       regions.add(id);
	       parent = region.getParent();
	       parentNames.add(parent.getId());
	       parent = parent.getParent();
	       continue;
	     }
	     
	     for (String name : parentNames) {
	       regions.remove(name);
	     }
	     
	     rg = regions.toString();
	     
	     rg = rg.replace("[", "");
	     rg = rg.replace("]", "");
	     
	     return new String[] { rg };
	   }
	   
	   public boolean isSingle() {
	     return true;
	   }
	   
	   public Class<? extends String> getReturnType() {
	     return String.class;
	   }
	   
	   public String toString(Event event, boolean b) {
	     return "wg region at" + this.loc.getSingle(event).toString();
	   }
	   
	   public Class<?>[] acceptChange(Changer.ChangeMode mode) {
	     return null;
	   }
	 }


