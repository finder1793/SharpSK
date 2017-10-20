package me.sharpjaws.sharpSK.Expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.Events.EvtTimerTick;

public class ExprEventTimeLeft extends SimpleExpression<Number> {

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
		if (!ScriptLoader.isCurrentEvent(EvtTimerTick.class)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "event-time[left]";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		try {
			EvtTimerTick a = ((EvtTimerTick) e);
			return new Number[] { a.getTimeLeft() };
		} catch (NullPointerException ex) {
			return new Number[] { 0 };
		}
	}

}
