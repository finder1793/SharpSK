package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprTownyTownAtLocation extends SimpleExpression<String> {

	private Expression<Location> loc;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		loc = (Expression<Location>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [towny] town at %location%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		return new String[] { TownyUniverse.getTownName(loc.getSingle(e)) };
	}

	@Override
	public boolean isSingle() {
		return true;
	}

}
