package me.sharpjaws.sharpSK.hooks.CoreProtect;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

public class EffCoreLog extends Effect{
	private Expression<Location> l;
	private Expression<OfflinePlayer> pl;
	private int mark;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult result) {
		l = (Expression<Location>) expr[0];
		pl = (Expression<OfflinePlayer>) expr[1];
		mark = result.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[(coreprotect|cp)] log block at %location%";
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(final Event e) {	
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CoreProtect");
		CoreProtectAPI api = ((CoreProtect)plugin).getAPI();
		 		 if (mark == 0){
		 		api.logRemoval(pl.getSingle(e).getName(), l.getSingle(e), l.getSingle(e).getBlock().getType(), l.getSingle(e).getBlock().getData());
		     	}
		 		 else if (mark == 1){
		 		api.logPlacement(pl.getSingle(e).getName(), l.getSingle(e), l.getSingle(e).getBlock().getType(), l.getSingle(e).getBlock().getData());	 
		 			
		 		 }
		 		 else if (mark == 2){
		 		api.logInteraction(pl.getSingle(e).getName(), l.getSingle(e));
		 		 
		 		 }
	}
		 			 
}


