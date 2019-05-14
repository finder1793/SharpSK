package me.sharpjaws.sharpSK.Conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class CondNotPlayerStandingOn extends Condition {
	private Expression<Entity> entity;
	private Expression<ItemStack> block;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
		entity = (Expression<Entity>) expr[0];
		block = (Expression<ItemStack>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "%entity% is not standing on %itemstack%";
	}

	@Override
	public boolean check(Event e) {
		Block mb = entity.getSingle(e).getLocation().getBlock().getRelative(BlockFace.DOWN);
		ItemStack block2 = block.getSingle(e);

        return (block.getSingle(e).getType().isBlock()) && (mb.getType() == block2.getType());
    }

}
