package me.sharpjaws.sharpsk.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpsk.threads.CTickTimerThread;
import me.sharpjaws.sharpsk.threads.CTimerThread;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Timer Pause")
@Description("Pauses a timer")
@Examples({"command /pausetimer:", "trigger:", "\tpause timer \"test\" ", " ",})
@Since("1.5, 1.6.4")
public class EffTimerPause extends Effect {
    private Expression<String> timer;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult paramParseResult) {
        timer = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "pause timer %string%";
    }

    @Override
    protected void execute(final Event e) {
        CTimerThread a = null;
        CTickTimerThread b = null;
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t instanceof CTimerThread) {
                if (((CTimerThread) t).instance().getName().equals(timer.getSingle(e))) {
                    a = ((CTimerThread) t).instance();
                    break;

                }
            }
            if (t instanceof CTickTimerThread) {
                if (((CTickTimerThread) t).instance().getName().equals(timer.getSingle(e))) {
                    b = ((CTickTimerThread) t).instance();
                    break;

                }
            }

        }
        try {
            a.pauseTimer(a.getName());
        } catch (NullPointerException ex) {
            b.pauseTimer(b.getName());

        }
    }
}
