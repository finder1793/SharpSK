package me.sharpjaws.sharpSK.hooks.WorldGuard;

import javax.annotation.Nullable;

import org.bukkit.event.Event;


import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprRegionsOfPlayer extends SimpleExpression<String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return false;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		return null;
	}

}
