package me.sharpjaws.sharpsk.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtTimerTick extends Event {
    private static final HandlerList h = new HandlerList();

    private final String timer;
    private final int timeleft;
    private final int timertype;

    public EvtTimerTick(String timer, int timeleft, int timertype) {
        this.timer = timer;
        this.timeleft = timeleft;
        this.timertype = timertype;
    }

    public static HandlerList getHandlerList() {
        return h;
    }

    @Override
    public HandlerList getHandlers() {
        return h;
    }

    public int getTimerType() {
        return timertype;
    }

    public String getTimer() {
        return timer;
    }

    public int getTimeLeft() {
        return timeleft;
    }
}
