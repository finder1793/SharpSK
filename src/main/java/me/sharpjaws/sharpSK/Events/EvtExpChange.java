package me.sharpjaws.sharpSK.Events;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class EvtExpChange extends Event implements Cancellable{
	  private static final HandlerList h = new HandlerList();
	    private boolean cancelled = false;
	    private Player pl;
	    private Number exp;

	    public  EvtExpChange(Player p , Number xp) {
	        cancelled = false;
	        this.pl =  p;
			this.exp = xp;			
	    }
	    public Player getPlayer() {
	        return pl;
	    }
	    public Number getExp() {
	        return exp;
	    }    
	    public boolean isCancelled() {
	        return cancelled;
	    }
	    public void setCancelled(boolean b) {
	        cancelled = b;
	    }
	    @Override
	    public HandlerList getHandlers() {
	        return h;
	    }
	    public static HandlerList getHandlerList() {
	        return h;
	    }
	}

