package me.sharpjaws.sharpSK.hooks.LogBlock;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffQueueLog extends Effect{

	@Override
	public boolean init(Expression<?>[] expr, int mark, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "logblock queue block (break|place|replace) from %player% at %location% ";
	}

	@Override
	protected void execute(Event arg0) {

		
	}

}
