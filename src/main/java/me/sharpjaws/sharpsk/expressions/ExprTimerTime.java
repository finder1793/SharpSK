package me.sharpjaws.sharpsk.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.sharpjaws.sharpsk.threads.CTickTimerThread;
import me.sharpjaws.sharpsk.threads.CTimerThread;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprTimerTime extends SimpleExpression<Integer> {
    private Expression<String> timer;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        timer = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "time of timer %string%";
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        int a = 0;
        int time = 0;

        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t instanceof CTimerThread) {
                if (t.getName().contains(timer.getSingle(e))) {
                    a = ((CTimerThread) t).getTime();
                }
            } else if (t instanceof CTickTimerThread) {
                if (t.getName().contains(timer.getSingle(e))) {
                    a = ((CTickTimerThread) t).getTime();

                }
            }
        }

        time = a;
        return new Integer[]{time};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                if (t instanceof CTimerThread) {
                    if (t.getName().contains(timer.getSingle(e))) {
                        ((CTimerThread) t).setTime((int) (((Timespan) delta[0]).getTicks_i() / 20));

                    }

                } else if (t instanceof CTickTimerThread) {
                    if (t.getName().contains(timer.getSingle(e))) {
                        ((CTickTimerThread) t).setTime((int) ((Timespan) delta[0]).getTicks_i());

                    }
                }
            }
        }

        if (mode == Changer.ChangeMode.ADD) {
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                if (t instanceof CTimerThread) {
                    if (t.getName().contains(timer.getSingle(e))) {
                        ((CTimerThread) t).addTime((int) (((Timespan) delta[0]).getTicks_i() / 20));
                    }

                } else if (t instanceof CTickTimerThread) {
                    if (t.getName().contains(timer.getSingle(e))) {

                        Timespan ti = ((Timespan) delta[0]);
                        ((CTickTimerThread) t).addTime((int) ti.getTicks_i());

                    }

                }
            }
        }
        if (mode == Changer.ChangeMode.REMOVE) {
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                if (t instanceof CTimerThread) {
                    if (t.getName().contains(timer.getSingle(e))) {
                        ((CTimerThread) t).removeTime((int) (((Timespan) delta[0]).getTicks_i() / 20));

                    }
                } else if (t instanceof CTickTimerThread) {
                    if (t.getName().contains(timer.getSingle(e))) {
                        ((CTickTimerThread) t).removeTime((int) ((Timespan) delta[0]).getTicks_i());

                    }
                }
            }
        }

    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET)
            return CollectionUtils.array(Timespan.class);
        if (mode == Changer.ChangeMode.ADD)
            return CollectionUtils.array(Timespan.class);
        if (mode == Changer.ChangeMode.REMOVE)
            return CollectionUtils.array(Timespan.class);
        return null;
    }

}
