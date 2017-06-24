package me.sharpjaws.sharpSK.hooks.CoreProtect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Door;
import org.bukkit.plugin.Plugin;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.coreprotect.CoreProtectAPI.ParseResult;

public class EffCoreRestore extends Effect{
	private Expression<Location> l;
	private Expression<Number> n;
	private Expression<Timespan> times;
	private Expression<ItemStack> exblocks;
	private Expression<OfflinePlayer> players;
	private int mark;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult result) {
		players = (Expression<OfflinePlayer>) expr[0];
		l = (Expression<Location>) expr[1];
		n = (Expression<Number>) expr[2];
		times = (Expression<Timespan>) expr[3];
		exblocks = (Expression<ItemStack>) expr[4];
		mark = result.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[(coreprotect|cp)] (rollback|restore|revert) changes at [the] %location% in radius %integer% [back] to %timespan% [ago] [exclude %-blocks%]";
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(final Event e) {	
		 final List<String> users = new ArrayList<String>();
	 		if (users != null && players != null){
	 		for (OfflinePlayer b : players.getAll(e)){
	 			
	 			users.add(b.getName());
	 		}
	 		}
		  final List<Object> exclude = new ArrayList<Object>();
	 		if (exblocks != null){
	 		for (ItemStack b : exblocks.getAll(e)){
	 			
	 			exclude.add(b.getType());
	 		}
	 		}
	 		
		
		Runnable RestoreRun = new Runnable(){
		     public void run(){
		    	 Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CoreProtect");
		 		CoreProtectAPI api = ((CoreProtect)plugin).getAPI();
		 		
		 		
		 		 List<String[]> lookup = null;	
		 				lookup = api.performRestore((int)times.getSingle(e).getTicks()/20, users, null, null,exclude,Arrays.asList(mark), n.getSingle(e).intValue(),l.getSingle(e));	
		 				if (lookup!=null){
		 				    for (String[] value : lookup){
		 		      ParseResult result = api.parseResult(value);
		 		      int x = result.getX();
		 		      int y = result.getY();
		 		      int z = result.getZ();
		 				    }			
		 			}	 			
		     }
		     
		   };   
		  
		   Thread RestoreThread = new Thread(RestoreRun);
		   	RestoreThread.start();
		   
		   
	}
}


