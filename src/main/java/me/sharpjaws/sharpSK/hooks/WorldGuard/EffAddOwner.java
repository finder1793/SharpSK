 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;

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
   
   public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
    this.players = exprs[0];
    this.name = exprs[0];
    this.world = exprs[0];
     
    return true;
   }
   
   protected void execute(Event event) {
    String name = (String)this.name.getSingle(event);
    World world = (World)this.world.getSingle(event);
     
    RegionManager regionManager = WGBukkit.getRegionManager(world);
    if (!regionManager.hasRegion(name)) {
      Skript.error("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
      return;
     }
     
    DefaultDomain owners = regionManager.getRegion(name).getOwners();
     Object[] arrayOfObject;
    int j = (arrayOfObject = this.players.getArray(event)).length; for (int i = 0 ; i < j; i++) { Object o = arrayOfObject[i];
      if ((o instanceof Player)) {
        owners.addPlayer(((Player)o).getName());
      } else if ((o instanceof OfflinePlayer)) {
        owners.addPlayer(((OfflinePlayer)o).getName());
       } else {
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


