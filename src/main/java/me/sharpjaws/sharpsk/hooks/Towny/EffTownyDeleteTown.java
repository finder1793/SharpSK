package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.sharpjaws.sharpsk.SharpSK;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffTownyDeleteTown extends Effect {
	private Expression<String> s;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "[towny] delete town %string%";
	}

	@Override
	protected void execute(Event e) {
		SharpSK core = SharpSK.instance;

		try {
			TownyUniverse.getDataSource().removeTown(TownyUniverse.getDataSource().getTown(s.getSingle(e)));
		} catch (NotRegisteredException e1) {
			core.getLogger().warning("Could not delete town: " + "\"" + s.getSingle(e) + "\"" + " Town does not exist");
			return;
		}

	}
}
