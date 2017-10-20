package me.sharpjaws.sharpSK.Slimefun;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;


import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;

public class ExprSlimefunAllResearches extends SimpleExpression<String> {

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] [(slimefun|sf)] all [(of|the)] researches";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		ArrayList<String> researches = new ArrayList<>();
		for (Research res : Research.list()) {
			researches.add(res.getName().replaceAll(" ", "_").toLowerCase());
		}
		return researches.toArray(new String[researches.size()]);
	}

}
