package me.sharpjaws.sharpSK.hooks.LuckPerms;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.exceptions.ObjectAlreadyHasException;


public class EffLuckPermsSetPerm extends Effect{
private Expression<OfflinePlayer> offplayer;
private Expression<String> perm;
private Expression<Boolean> bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		offplayer = (Expression<OfflinePlayer>) expr[0];
		perm = (Expression<String>) expr[1];
		bool = (Expression<Boolean>)expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "[sharpsk] [luckperms] set permission %string% to %boolean% for [player] %player%";
	}

	@Override
	protected void execute(Event e) {
	try {
		LuckPermsApi api = LuckPerms.getApi();
		Node permission = LuckPerms.getApi().getNodeFactory().newBuilder(perm.getSingle(e)).setValue(bool.getSingle(e)).build();
		api.getUser(offplayer.getSingle(e).getUniqueId()).setPermission(permission);
	} catch (ObjectAlreadyHasException e1) {
	return;
	}
		
	}

}
