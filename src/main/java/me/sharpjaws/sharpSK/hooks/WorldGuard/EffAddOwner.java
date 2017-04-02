 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
 
 public class EffAddOwner extends Effect
 {
   private Expression<?> players;
   private Expression<?> name;
   private Expression<?> world;
   private int mark = 0;
   
   public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult Result)
   {
    this.players = exprs[0];
    this.name = exprs[1];
    this.world = exprs[2];
     mark = Result.mark;
    return true;
   }
   
   protected void execute(Event event) {
	   String name = (String)this.name.getSingle(event);
	   World world = (World)this.world.getSingle(event);
	   if (world == null) {
		   for (RegionManager a : WGBukkit.getPlugin().getRegionContainer().getLoaded()) {
			 for  (Entry<String, ProtectedRegion> b :a.getRegions().entrySet()) {
				if ( b.getKey().equals(name)) {
					world = Bukkit.getWorld(a.getName());
					break;
				}
				 
			 }
		   }

	   }
    RegionManager regionManager = WGBukkit.getRegionManager(world);

    if (!regionManager.hasRegion(name)) {
      Skript.error("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
      return;
     }
     
    DefaultDomain owners = regionManager.getRegion(name).getOwners();
     Object[] arrayOfObject;
    int j = (arrayOfObject = this.players.getArray(event)).length; for (int i = 0 ; i < j; i++) { Object o = arrayOfObject[i];
      if ((o instanceof Player)) {
    	   
   	   if (mark == -1) {
        owners.addPlayer(((Player)o).getName());
   	   }else if (mark == 1) {
   		 owners.addPlayer(((Player)o).getUniqueId());	   
   	   }else if (mark == 0) {
   		  owners.addPlayer(((Player)o).getName());	
   	   }
      } else if ((o instanceof OfflinePlayer)) {
    	   if (mark == -1) {
    	         owners.addPlayer(((OfflinePlayer)o).getName());
    	    	   }else if (mark == 1) {
    	    		   owners.addPlayer(((OfflinePlayer)o).getUniqueId());	   
    	    	   }else if (mark == 0) {
    	    		  owners.addPlayer(((OfflinePlayer)o).getName());	
    	    	   }
      }else {
          owners.addPlayer(o.toString());
        }
     }
     
    regionManager.getRegion(name).setOwners(owners);
     try {
      regionManager.save();
     } catch (Exception e) {
      e.printStackTrace();
     }
   }
   
   public String toString(Event e, boolean debug) {
    return getClass().getName();
   }
 }


