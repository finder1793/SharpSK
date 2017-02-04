package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.Threads.CTimerThread;

public class EffTimerExtend extends Effect {
	private Expression<String> timer;
	private Expression<Timespan> time;
	int task;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		timer = (Expression<String>) expr[0];
		time = (Expression<Timespan>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "extend timer %string% by %timespan%";
	}

	@Override
	protected void execute(final Event e) {

		for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t instanceof CTimerThread) {
	        	if (t.getName().contains(timer.getSingle(e))){
	        		((CTimerThread) t).addTime(time.getSingle(e).getTicks()/20);
	        		
	        	}
	
	        }	
		}
	}
	
		
}

