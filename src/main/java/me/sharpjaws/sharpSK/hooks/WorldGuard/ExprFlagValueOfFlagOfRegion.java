 package me.sharpjaws.sharpSK.hooks.WorldGuard;
 
 import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
 
 
 public class ExprFlagValueOfFlagOfRegion extends SimpleExpression<String> {
	 private Expression<String> flag;
	  private Expression<String> region;
	  private Expression<World> world;
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expression, int arg1, Kleenean arg2, ParseResult arg3) {
		 flag = (Expression<String>) expression[0];
		 region = (Expression<String>) expression[1];
		 world = (Expression<World>) expression[2];
		return true;
		
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		 
		WorldGuardPlugin wg =  (WorldGuardPlugin)Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");	   
		RegionManager set = wg.getRegionManager(world.getSingle(e));
		ProtectedRegion pr = null;
		
		String finalv = null;
		
		for (Entry<String, ProtectedRegion> a : set.getRegions().entrySet()) {
			if (a.getKey().equals(region.getSingle(e))){
				pr = a.getValue();
		}
			try {
		for ( Entry<Flag<?>,Object> b : pr.getFlags().entrySet()) {
			
		if (b.getKey().getName().equalsIgnoreCase(flag.getSingle(e))) {
			if (b.getValue() == StateFlag.State.ALLOW) {
				finalv = "ALLOW";
			}else if (b.getValue() == StateFlag.State.DENY){
				finalv = "DENY";
			}else {
				return new String[] {};
			}
			}
		}
		
		}catch (NullPointerException ex) {
			return new String[] {};
		}
			
	}
		return new String[] {finalv};
	}
	
 }
 
	 
 
