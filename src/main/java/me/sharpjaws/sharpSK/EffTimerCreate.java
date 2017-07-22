package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.Threads.CTickTimerThread;
import me.sharpjaws.sharpSK.Threads.CTimerThread;

public class EffTimerCreate extends Effect {
	private Expression<String> s;
	private Expression<Timespan> duration;
	private Expression<Boolean> active;
	private Expression<Timespan> interval;
	int task;
	int mark;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult result) {
		s = (Expression<String>) expr[0];
		duration = (Expression<Timespan>) expr[1];
		active = (Expression<Boolean>) expr[2];
		interval = (Expression<Timespan>) expr[3];
		this.mark = result.mark;
		return true;
	}
	

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "timer create %string% duration %timespan%";
	}

	@Override
	protected void execute(final Event e) {
		String timer = s.getSingle(e).toLowerCase();
		for (Thread t : Thread.getAllStackTraces().keySet()) {
			
		
	        if (t instanceof CTimerThread && t.isAlive()) {
	        	System.out.println(t.getName()+" THREADID: "+ t.getId());
	        	CTimerThread ti = (CTimerThread)t;
	        	if (ti.getName().equals(timer)){	
	        		main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
	        		core.getLogger().warning("Timer "+"\"" +s.getSingle(e)+"\"" + " could not be created because a timer already exists with that name.");
	        		return;
	        	}
	        }else if (t instanceof CTickTimerThread && t.isAlive()) {
	        	System.out.println(t.getName()+" THREADID: "+ t.getId());
	        	CTickTimerThread ti = (CTickTimerThread)t;
	        	if (ti.getName().equals(timer)){	
	        		main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
	        		core.getLogger().warning("Timer "+"\"" +s.getSingle(e)+"\"" + " could not be created because a timer already exists with that name.");
	        		return;  
	        	}
	        }
		}
	        

	if (mark == -1){		
	if (active == null)	{
		
		if (interval != null){
			CTimerThread th = new CTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i()/20, false,(int)interval.getSingle(e).getTicks_i()/20);
			th.instance().start();
				}else{
			CTimerThread th = new CTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i()/20, false,0);	
			th.instance().start();
				}
	}else {
	if (active.getSingle(e) == false){
		
		if (interval != null){
	CTimerThread th = new CTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i()/20, false,(int)interval.getSingle(e).getTicks_i()/20);
	th.instance().start();
		}else{
	CTimerThread th = new CTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i()/20, false,0);	
	th.instance().start();
		}
	
	}else if (active.getSingle(e) == true){
		if (interval != null){
			CTimerThread th = new CTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i()/20, true,(int)interval.getSingle(e).getTicks_i()/20);
			th.instance().start();
				}else{
			CTimerThread th = new CTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i()/20, true,0);	
			th.instance().start();
				}
	}
}
		
	}else if(mark == 1){
		if (active == null)	{
			if (interval != null){
			CTickTimerThread th = new CTickTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i(), false,(int)interval.getSingle(e).getTicks_i());
			th.instance().start();
			}else{
				CTickTimerThread th = new CTickTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i(), false,0);
				th.instance().start();	
			}
		}else {
		if (active.getSingle(e) == false){
			if (interval != null){
				CTickTimerThread th = new CTickTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i(), false,(int)interval.getSingle(e).getTicks_i());
				th.instance().start();
				}else{
					CTickTimerThread th = new CTickTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i(), false,0);
					th.instance().start();	
				}
		}else if (active.getSingle(e) == true){
			if (interval != null){
				CTickTimerThread th = new CTickTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i(), true,(int)interval.getSingle(e).getTicks_i());
				th.instance().start();
				}else{
					CTickTimerThread th = new CTickTimerThread(s.getSingle(e),(int)duration.getSingle(e).getTicks_i(), true,0);
					th.instance().start();	
				}
		}
	}
	}
	


		
		
	}	
}
