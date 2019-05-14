package me.sharpjaws.sharpsk.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import javax.annotation.Nullable;

public class ExprEventWorld extends SimpleExpression<World> {

	@Override
	public Class<? extends World> getReturnType() {
		return World.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[the] [(-1¦past|1¦future) state of] [event-]world";
	}

	private int mark;

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		if (!ScriptLoader.isCurrentEvent(PlayerChangedWorldEvent.class)) {
			return false;
		} else {
			mark = result.mark;
			return true;
		}
	}

	@Override
	@Nullable
	protected World[] get(Event e) {
		if (e.getEventName().equals("PlayerChangedWorldEvent")) {
			if (mark == 0) {
				return new World[] { ((PlayerChangedWorldEvent) e).getPlayer().getWorld() };
			} else if (mark == 1) {
				return new World[] { ((PlayerChangedWorldEvent) e).getPlayer().getWorld() };
			} else if (mark == -1) {
				return new World[] { ((PlayerChangedWorldEvent) e).getFrom() };
			}
		}
		return null;
	}

}
