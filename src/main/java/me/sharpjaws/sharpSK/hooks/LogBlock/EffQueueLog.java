package me.sharpjaws.sharpSK.hooks.LogBlock;

import java.sql.SQLException;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.diddiz.LogBlock.BlockChange;
import de.diddiz.LogBlock.Consumer;
import de.diddiz.LogBlock.LogBlock;
import de.diddiz.LogBlock.QueryParams;
import de.diddiz.LogBlock.QueryParams.BlockChangeType;

public class EffQueueLog extends Effect{
	private Expression<Player> player;
	private Expression<Location> loc;
	private Expression<ItemStack> previ;
	private Expression<ItemStack> newi;
	private int markr;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int pat, Kleenean arg2, ParseResult result) {
		player = (Expression<Player>) expr[0];
		loc = (Expression<Location>) expr[1];
		previ = (Expression<ItemStack>)expr[2];
		newi = (Expression<ItemStack>)expr[3];
		markr = result.mark;
		return true;
		
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "logblock queue block (break|place|replace) from %player% at %location% [with [previous block %-itemstack% and new] block %-itemstack%] ";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		
		LogBlock logblock = (LogBlock)Bukkit.getServer().getPluginManager().getPlugin("LogBlock");	
		Consumer con  = logblock.getConsumer();
		String a = player.getSingle(e).getName();
	
if (markr == 1) {
con.queueBlockBreak(a, loc.getSingle(e), loc.getSingle(e).getBlock().getTypeId(),loc.getSingle(e).getBlock().getData());
}else if (markr == 2) {
con.queueBlockPlace(a, loc.getSingle(e), loc.getSingle(e).getBlock().getTypeId(),loc.getSingle(e).getBlock().getData());
}else if (markr == 3) {
	if (previ != null & newi != null) {
		con.queueBlockReplace(a, loc.getSingle(e),previ.getSingle(e).getType().getId(),previ.getSingle(e).getData().getData(), newi.getSingle(e).getTypeId(), newi.getSingle(e).getData().getData());
	}else {
		
		QueryParams params = new QueryParams(logblock);
		params.setPlayer(player.getSingle(e).getName());
		params.bct = BlockChangeType.BOTH;
		params.limit = -1;
		params.world = loc.getSingle(e).getWorld();
		params.needDate = true;
		params.needType = true;
		params.needData = true;
		params.needPlayer = true;
		int oldstate= 0;
		byte oldstate2 = 0;
		try {
		    for (BlockChange bc : logblock.getBlockChanges(params)) {
		    oldstate =  bc.type;
		    oldstate2 =  bc.data;
		    break;
		    }
		} catch (SQLException ex) {

		}
		con.queueBlockReplace(a, loc.getSingle(e), oldstate, oldstate2, loc.getSingle(e).getBlock().getTypeId(), loc.getSingle(e).getBlock().getData());
	
	}
	}
	
	}

}
