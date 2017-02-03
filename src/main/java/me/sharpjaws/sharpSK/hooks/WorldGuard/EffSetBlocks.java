 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
 
 public class EffSetBlocks extends Effect
 {
   private Expression<?> name;
   private Expression<?> world;
   private Expression<?> block;
   
   public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
   {
     this.name = expressions[0];
     this.world = expressions[1];
     this.block = expressions[2];
     
     return true;
   }
   
   protected void execute(Event event) {
     String name = (String)this.name.getSingle(event);
     World world = (World)this.world.getSingle(event);
     ItemStack block = (ItemStack)this.block.getSingle(event);
     
 
     RegionManager regionManager = WGBukkit.getRegionManager(world);
     if (!regionManager.hasRegion(name)) {
       Skript.error("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
       return;
     }
     
     Vector v1 = regionManager.getRegion(name).getMaximumPoint();
     Vector v2 = regionManager.getRegion(name).getMinimumPoint();
     Region region = new CuboidRegion(v1, v2);
     BaseBlock b = new BaseBlock(block.getTypeId(), block.getData().getData());
     
     EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), 262144);
     try {
       es.setBlocks(region, b);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
   public String toString(Event e, boolean debug)
   {
     return getClass().getName();
   }
 }


