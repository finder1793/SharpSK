package me.sharpjaws.sharpSK.Conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;

import javax.annotation.Nullable;

public class CondEventNotCancelled extends Condition {

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "event is not cancelled";
	}

	public boolean checkEvent(Event e) throws EventException {

		return false;

	}

	@Override
	public boolean check(Event e) {
		boolean cancel = false;

		if (e instanceof Cancellable) {
			cancel = ((Cancellable) e).isCancelled();
		}

		return !cancel;
	}
}
