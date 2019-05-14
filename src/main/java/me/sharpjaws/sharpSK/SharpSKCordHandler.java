package me.sharpjaws.sharpSK;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class SharpSKCordHandler extends SharpSK implements PluginMessageListener {
	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "SharpSKCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "SharpSKCord", this);
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
		if (arg0.equals("SharpSKCord")) {

		} else {
			return;
		}

	}

}
