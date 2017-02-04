package me.sharpjaws.sharpSK;

import java.util.ArrayList;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.Threads.CTimerThread;

public class ExprAllTimers extends SimpleExpression<String>{

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {	
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		
		ArrayList<String> timers = new ArrayList<>();
		
		for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t instanceof CTimerThread) {
	        	timers.add(t.getName());
	        
	        }
	   }
		return timers.toArray(new String[timers.size()]);	
	}

}
