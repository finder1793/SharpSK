package me.sharpjaws.sharpSK;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class EvtTimerTick extends Event{
	  private static final HandlerList h = new HandlerList();

	    private String timer;
	    private int timeleft;
	    private int timertype;
	    	
	    public EvtTimerTick(String timer, int timeleft,int timertype) {
	        this.timer = timer;
	        this.timeleft = timeleft;
	        this.timertype = timertype;
	    }
	    @Override
	    public HandlerList getHandlers() {
	        return h;
	    }
	    public static HandlerList getHandlerList() {
	        return h;
	    }
	    public int getTimerType(){
	    	return timertype;
	    }
	    public String getTimer(){
	    	return timer;
	    }
	    
	    public int getTimeLeft(){
	    	return timeleft;
	    }
	}

