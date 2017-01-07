package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;


import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondEventCancelled extends Condition {

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "event is cancelled";
	}
	

	public boolean checkEvent(Event e) throws EventException  {
		
		return false; 
		
		
	}
	@Override	
	public boolean check(Event e){  
		Boolean cancel = false;
	
		if (e instanceof Cancellable){
		cancel = ((Cancellable) e).isCancelled();
	}
	
		if (cancel == true){
	return true;
		}else{
			return false;
		}
	}	
}
