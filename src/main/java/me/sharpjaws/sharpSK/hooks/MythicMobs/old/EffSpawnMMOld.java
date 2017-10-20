package me.sharpjaws.sharpSK.hooks.MythicMobs.old;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.elseland.xikage.MythicMobs.MythicMobs;
import net.elseland.xikage.MythicMobs.API.Exceptions.InvalidMobTypeException;

public class EffSpawnMMOld extends Effect {

	private Expression<String> mn;
	private Expression<Location> loc;
	private Expression<Integer> level;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		mn = (Expression<String>) expr[0];
		loc = (Expression<Location>) expr[1];
		level = (Expression<Integer>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "spawn [a] mythicmob %string% at %location% with level %integer%";
	}

	@Override
	protected void execute(Event e) {
		try {
			MythicMobs.inst().getAPI().getMobAPI().spawnMythicMob(mn.getSingle(e), loc.getSingle(e),
					level.getSingle(e));
		} catch (InvalidMobTypeException e1) {

		}
	}

}
