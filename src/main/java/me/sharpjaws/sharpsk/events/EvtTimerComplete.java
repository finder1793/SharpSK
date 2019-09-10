package me.sharpjaws.sharpsk.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtTimerComplete extends Event implements Cancellable {
    private static final HandlerList h = new HandlerList();
    private final String timer;
    private boolean cancelled;

    public EvtTimerComplete(String timer) {
        cancelled = false;
        this.timer = timer;
    }

    public static HandlerList getHandlerList() {
        return h;
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

    public String getTimer() {
        return timer;
    }
}
