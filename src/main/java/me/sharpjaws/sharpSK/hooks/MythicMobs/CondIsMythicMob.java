package me.sharpjaws.sharpSK.hooks.MythicMobs;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class CondIsMythicMob extends Condition {
	private Expression<Entity> mythicmob;
	@SuppressWarnings("unused")
	private Expression<Location> loc;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
		mythicmob = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "%entity% is a mythicmob";
	}

	@Override
	public boolean check(Event e) {
		Boolean result = Boolean.FALSE;
		try {
			result = MythicMobs.inst().getAPIHelper().isMythicMob(mythicmob.getSingle(e).getUniqueId());
		} catch (NullPointerException ex) {
			return result;
		}
		return result;
	}
}
