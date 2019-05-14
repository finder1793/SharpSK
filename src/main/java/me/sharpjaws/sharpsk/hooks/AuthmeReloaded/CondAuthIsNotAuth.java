package me.sharpjaws.sharpsk.hooks.AuthmeReloaded;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.xephi.authme.api.NewAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

class CondAuthIsNotAuth extends Condition {
	private Expression<Player> p;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, SkriptParser.ParseResult arg3) {
		p = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "%player% is not authenticated";
	}

	@Override
	public boolean check(Event e) {
		boolean a = false;
		try {
			a = !NewAPI.getInstance().isAuthenticated(p.getSingle(e));
		} catch (NullPointerException ignored) {

		}
		return a;
	}
}