package me.sharpjaws.sharpSK.hooks.AuthmeReloaded;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import fr.xephi.authme.events.CustomEvent;

public class AuthRegisterListener implements Listener {

	public  AuthRegisterListener(Plugin main){
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public final void onAuthRegister(CustomEvent e){
	}
}

