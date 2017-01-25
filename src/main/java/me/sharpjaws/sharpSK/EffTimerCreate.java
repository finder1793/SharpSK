package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.Threads.CTimerThread;

public class EffTimerCreate extends Effect {
	private Expression<String> s;
	private Expression<Timespan> duration;
	int task;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		duration = (Expression<Timespan>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "timer create %string% duration %timespan%";
	}

	@Override
	protected void execute(final Event e) {

	CTimerThread th = new CTimerThread(s.getSingle(e),duration.getSingle(e).getTicks()/20);
	th.start();
	
		
	}
}
