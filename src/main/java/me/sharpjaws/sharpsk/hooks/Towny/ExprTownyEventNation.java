package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.event.*;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

class ExprTownyEventNation extends SimpleExpression<String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "event-nation";
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
        return ScriptLoader.isCurrentEvent(RenameNationEvent.class)
                || ScriptLoader.isCurrentEvent(DeleteNationEvent.class)
                || ScriptLoader.isCurrentEvent(NewNationEvent.class)
                || ScriptLoader.isCurrentEvent(NationAddTownEvent.class)
                || ScriptLoader.isCurrentEvent(NationRemoveTownEvent.class);
    }

	@Override
	@Nullable
	protected String[] get(Event e) {
        switch (e.getEventName()) {
            case "RenameNationEvent":
                return new String[]{((RenameNationEvent) e).getNation().getName()};
            case "DeleteNationEvent":
                return new String[]{((DeleteNationEvent) e).getNationName()};
            case "NewNationEvent":
                return new String[]{((NewNationEvent) e).getNation().getName()};
            case "NationAddTownEvent":
                return new String[]{((NationAddTownEvent) e).getNation().getName()};
            case "NationRemoveTownEvent":
                return new String[]{((NationRemoveTownEvent) e).getNation().getName()};
        }
		return null;
	}

}
