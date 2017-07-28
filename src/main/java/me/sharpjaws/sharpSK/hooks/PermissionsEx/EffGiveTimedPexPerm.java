package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EffGiveTimedPexPerm extends Effect {
	private Expression<String> perm;
	private Expression<OfflinePlayer> offplayers;
	private Expression<Timespan> time;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		perm = (Expression<String>) expr[0];
		offplayers = (Expression<OfflinePlayer>) expr[1];
		time = (Expression<Timespan>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "pex add timed perm[ission] %string% to %offlineplayers% (duration|for) %timespan%";
	}

	@Override
	protected void execute(Event e) {
		
	if (time.getSingle(e).getTicks_i()/20 > 0){
	for (OfflinePlayer op : offplayers.getAll(e)){
		PermissionUser permuser = PermissionsEx.getPermissionManager().getUser(op.getUniqueId());
		System.out.println("TRIGGER");
		permuser.addTimedPermission(perm.getSingle(e), permuser.getPlayer().getWorld().getName(), (int)time.getSingle(e).getTicks_i()/20);
	}
	}else{
		return;
	}
	}
}
