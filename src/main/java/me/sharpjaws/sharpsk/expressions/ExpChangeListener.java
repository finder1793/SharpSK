package me.sharpjaws.sharpsk.expressions;

import me.sharpjaws.sharpsk.events.EvtExpChange;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.plugin.Plugin;

public class ExpChangeListener implements Listener {

    public ExpChangeListener(Plugin main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public final void onExpChange(PlayerExpChangeEvent e) {

        EvtExpChange ExpChangeEvent = new EvtExpChange(e.getPlayer(), e.getAmount());
        Bukkit.getServer().getPluginManager().callEvent(ExpChangeEvent);
        if (ExpChangeEvent.isCancelled()) {
            ExpChangeEvent.setCancelled(true);
            e.setAmount(0);
        }
    }
}