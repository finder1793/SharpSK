package me.sharpjaws.sharpsk.hooks.GroupManager;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.data.User;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.Map.Entry;

public class EffGroupManagerAddSubGroupToPlayer extends Effect {
	private Expression<OfflinePlayer> player;
	private Expression<String> group;
	private Expression<World> world;
	GroupManager groupManager;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		player = (Expression<OfflinePlayer>) expr[0];
		group = (Expression<String>) expr[1];
		world = (Expression<World>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[sharpsk] (gman|group[ ]manager) add [sub] group %string% to %offlineplayer% [in [world] %-world%]";
	}

	@Override
	protected void execute(Event e) {

		final Plugin GMplugin = Bukkit.getPluginManager().getPlugin("GroupManager");
		GroupManager GM = (GroupManager) GMplugin;
		OverloadedWorldHolder handler = null;

		if (player == null) {
			return;
		}

        if (player.getSingle(e).isOnline()) {
			handler = GM.getWorldsHolder().getWorldDataByPlayerName(player.getSingle(e).getName());
		} else {
			handler = GM.getWorldsHolder().getDefaultWorld();
		}

		if (world != null) {
			handler = GM.getWorldsHolder().getWorldData(world.getSingle(e).getName());
		}
		for (Entry<String, User> a : handler.getUsers().entrySet()) {
			if (player.getSingle(e).getUniqueId().toString().equals(a.getValue().getUUID().toString())) {
				a.getValue().addSubGroup(new Group(group.getSingle(e)));
				a.getValue().setLastName(player.getSingle(e).getName());
				break;
			}
		}
		GM.getWorldsHolder().saveChanges();
		GM.getWorldsHolder().reloadAll();

	}

}
