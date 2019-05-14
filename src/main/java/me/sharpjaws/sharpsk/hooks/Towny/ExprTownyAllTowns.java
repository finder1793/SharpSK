package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

class ExprTownyAllTowns extends SimpleExpression<String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[towny] (all|the) towns";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {

		ArrayList<String> narr = new ArrayList<>();
		for (Town a1 : TownyUniverse.getDataSource().getTowns()) {

			narr.add(a1.getName());
		}

		return narr.toArray(new String[0]);

	}

	@Override
	public boolean isSingle() {
		return false;
	}

}
