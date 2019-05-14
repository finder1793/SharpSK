package me.sharpjaws.sharpSK.hooks.uCars;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.useful.uCarsAPI.uCarsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ConduCarsIsInCar extends Condition {
	private Expression<Player> player;
	@SuppressWarnings("unused")
	private Expression<ItemStack> block;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
		player = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "%player% is in a car";
	}

	@Override
	public boolean check(Event e) {
		return uCarsAPI.getAPI().checkInCar(player.getSingle(e));
	}
}
