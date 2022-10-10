package me.sharpjaws.sharpsk.hooks.AuthmeReloaded;

import fr.xephi.authme.events.CustomEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class AuthRegisterListener implements Listener {

    public AuthRegisterListener(Plugin main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public final void onAuthRegister(CustomEvent e) {
    }
}
