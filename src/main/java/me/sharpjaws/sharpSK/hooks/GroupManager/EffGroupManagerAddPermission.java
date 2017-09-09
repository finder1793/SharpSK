package me.sharpjaws.sharpSK.hooks.GroupManager;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffGroupManagerAddPermission extends Effect{
	private Expression<OfflinePlayer> player;
	private Expression<String> perm;
	GroupManager groupManager;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		perm = (Expression<String>) expr[1];
		player = (Expression<OfflinePlayer>) expr[2];
		return false;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[sharpsk] (gm|group[ ]manager add perm[ission] %string% to [player] %offlineplayer%";
	}

	@Override
	protected void execute(Event e) {
		
		if (player.getSingle(e).isOnline()){
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(player.getSingle(e).getPlayer());	
		if (handler == null)
		{
			return;
		}
		handler.getUser(player.getSingle(e).getName()).addPermission(perm.getSingle(e));
		}else{
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldDataByPlayerName(player.getSingle(e).getName());
		if (handler == null)
		{
			return;
		}
		handler.getUser(player.getSingle(e).getName()).addPermission(perm.getSingle(e));
		}
	
		
		
	}

}
