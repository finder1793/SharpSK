package me.sharpjaws.sharpsk.hooks.MythicMobs;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffSpawnMM extends Effect {

	private Expression<String> mn;
	private Expression<Location> loc;
	private Expression<Number> level;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		mn = (Expression<String>) expr[0];
		loc = (Expression<Location>) expr[1];
		level = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "spawn [a] mythicmob %string% at %location% [with level %-number%]";
	}

	@Override
	protected void execute(Event e) {
		try {
			try {
				if (level != null) {
					MythicMobs.inst().getAPIHelper().spawnMythicMob(mn.getSingle(e), loc.getSingle(e),
							Math.round(level.getSingle(e).intValue()));
				} else {
					MythicMobs.inst().getAPIHelper().spawnMythicMob(mn.getSingle(e), loc.getSingle(e), 1);
				}
			} catch (NullPointerException ex) {
				return;
			}
		} catch (InvalidMobTypeException e1) {
			return;
		}
	}

}
