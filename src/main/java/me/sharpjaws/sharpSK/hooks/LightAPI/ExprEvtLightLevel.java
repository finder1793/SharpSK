package me.sharpjaws.sharpSK.hooks.LightAPI;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ru.beykerykt.lightapi.events.DeleteLightEvent;
import ru.beykerykt.lightapi.events.SetLightEvent;

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
		if (!ScriptLoader.isCurrentEvent(SetLightEvent.class)) {
			return false ;		
		}
		return true;
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		if (e.getEventName().equals("SetLightEvent")) {
			return new Number[]{((SetLightEvent) e).getLightLevel()} ;
		}
		
		return null; 
	}

}
