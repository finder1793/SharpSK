package me.sharpjaws.sharpSK;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nullable;

import org.apache.commons.lang.time.StopWatch;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

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
	final Timer timer = new Timer(s.getSingle(e));
	final Long time = duration.getSingle(e).getMilliSeconds();
	timer.schedule(new TimerTask(){
		@Override
		public void run() {
			EvtTimerComplete evt = new EvtTimerComplete(s.getSingle(e));
			Bukkit.getServer().getPluginManager().callEvent(evt);
			timer.cancel();
			
		}
		
	},time,time);

	}
}
