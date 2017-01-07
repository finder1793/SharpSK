package me.sharpjaws.sharpSK.utils.Bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import sun.misc.Cache;

public class Messenger implements PluginMessageListener {
	public Plugin plugin;
	public Cache cache;
	public Messenger(Plugin p) {
		p.getServer().getMessenger().registerOutgoingPluginChannel(p, "BungeeCord");
		p.getServer().getMessenger().registerIncomingPluginChannel(p, "BungeeCord", this);

		this.plugin = p;
		this.cache = new Cache();
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		String subchannel = in.readUTF();

		if (!channel.equals("BungeeCord")) {
			return;
		}
		if (subchannel.equals("PlayerCount")) {
			 String aps = in.readUTF();
			 
			 
		}
	}

	public static String getPlayerCount(String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerCount");
			out.writeUTF(server);
			return server;
			
	}
	
	public List<Player> getOnlinePlayers()
	  {
	    List<Player> players = new ArrayList();
	    for (World w : Bukkit.getWorlds()) {
	      for (Player e : w.getPlayers()) {
	        players.add(e);
	      }
	    }
	    return players;
	  }
	public void sendAnonymous(byte[] message)
	  {
	    if (getOnlinePlayers().size() < 1) {
	      return;
	    }
	    ((Player)getOnlinePlayers().get(0)).sendPluginMessage(this.plugin, "BungeeCord", message);
	  }
}
