package me.sharpjaws.sharpSK.Effects;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.main;
import me.sharpjaws.sharpSK.Threads.CTickTimerThread;
import me.sharpjaws.sharpSK.Threads.CTimerThread;

@Name("Timer Pause")
@Description("Resumes a timer")
@Examples({ "command /resumetimer:", "trigger:", "\tresume timer \"test\" ", " ", })
@Since("1.5, 1.6.4")
public class EffTimerResume extends Effect {
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
		return "resume timer %string%";
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
			try {
					a.resumeTimer(a.getName());
			} catch (NullPointerException ex) {
					b.resumeTimer(b.getName());
			}
		} catch (NullPointerException ex) {
			main core = (main) Bukkit.getPluginManager().getPlugin("SharpSK");
			core.getLogger().warning(
					"Timer " + "\"" + timer.getSingle(e) + "\"" + " could not be resumed because it does not exist.");
		}

	}

}
