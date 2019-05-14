package me.sharpjaws.sharpSK.hooks.LightAPI;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import ru.beykerykt.lightapi.events.SetLightEvent;

import javax.annotation.Nullable;

public class ExprEvtLightLevel extends SimpleExpression<Number> {

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "event-lightlevel";
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
        return ScriptLoader.isCurrentEvent(SetLightEvent.class);
    }

	@Override
	@Nullable
	protected Number[] get(Event e) {
		if (e.getEventName().equals("SetLightEvent")) {
			return new Number[] { ((SetLightEvent) e).getLightLevel() };
		}

		return null;
	}

}
