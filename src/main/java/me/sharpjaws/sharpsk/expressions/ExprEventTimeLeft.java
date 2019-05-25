package me.sharpjaws.sharpsk.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpsk.events.EvtTimerTick;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

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
        return ScriptLoader.isCurrentEvent(EvtTimerTick.class);
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
