package me.sharpjaws.sharpsk.hooks.AuthmeReloaded;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtRegister extends Event {
    private static final HandlerList h = new HandlerList();
    private boolean cancelled = false;
    private final Player pl;
    private Number exp;

    public EvtRegister(Player p) {
        cancelled = false;
        this.pl = p;
    }

    public Player getPlayer() {
        return pl;
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
