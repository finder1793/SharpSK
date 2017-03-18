package me.sharpjaws.sharpSK.hooks.LogBlock;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.diddiz.LogBlock.Actor;
import de.diddiz.LogBlock.BlockChange;
import de.diddiz.LogBlock.Consumer;
import de.diddiz.LogBlock.LogBlock;
import de.diddiz.LogBlock.QueryParams;
import de.diddiz.LogBlock.QueryParams.BlockChangeType;

public class EffQueueLog extends Effect{
	private Expression<Player> player;
	private Expression<Location> loc;
	private Expression<Block> previ;
	private Expression<Block> newi;
	private int mark;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int mark, Kleenean arg2, ParseResult result) {
		player = (Expression<Player>) expr[0];
		loc = (Expression<Location>) expr[1];
		previ = (Expression<Block>)expr[2];
		newi = (Expression<Block>)expr[3];
		mark = result.mark;
		return true;
		
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "logblock queue block (break|place|replace) from %player% at %location% [with [previous block %-itemstack% and new] block %-itemstack%] ";
	}

	@Override
	protected void execute(Event e) {
		
		LogBlock logblock = (LogBlock)Bukkit.getServer().getPluginManager().getPlugin("LogBlock");	
		Consumer con  = logblock.getConsumer();
		Actor a = Actor.actorFromEntity(player.getSingle(e));
		
	
if (mark == 1) {
con.queueBlockBreak(a, loc.getSingle(e).getBlock().getState());
}else if (mark == 2) {
	if (newi == null && previ == null) {
con.queueBlockPlace(a, loc.getSingle(e).getBlock().getState());
	}else {
con.queueBlockPlace(a, newi.getSingle(e).getState());	
	}
	
}else if (mark == 3){
	if (newi == null && previ == null) {
	QueryParams params = new QueryParams(logblock);
	params.setPlayer(player.getSingle(e).getName());
	params.world = loc.getSingle(e).getWorld();
	params.bct = BlockChangeType.BOTH;
	BlockState newstate = null;
	
	try {
		for (BlockChange bc : logblock.getBlockChanges(params)) {
			newstate = bc.getLocation().getBlock().getState();		
}
	} catch (SQLException e1) {
	
	}
	
con.queueBlockReplace(a, loc.getSingle(e).getBlock().getState(), newstate);
	
}else {
	con.queueBlockReplace(a, previ.getSingle(e).getState(), newi.getSingle(e).getState());
}
}	
	
	
	}

}
