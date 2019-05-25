package me.sharpjaws.sharpsk.hooks.Slimefun;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Events.ResearchUnlockEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprSlimefunEvtResearch extends SimpleExpression<String> {

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
        return "event-research";
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
        return ScriptLoader.isCurrentEvent(ResearchUnlockEvent.class);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (e.getEventName().equals("ResearchUnlockEvent")) {
            return new String[] {
                    ((ResearchUnlockEvent) e).getResearch().getName().replaceAll(" ", "_").toLowerCase() };
        } else {
            return null;
        }
    }
}
