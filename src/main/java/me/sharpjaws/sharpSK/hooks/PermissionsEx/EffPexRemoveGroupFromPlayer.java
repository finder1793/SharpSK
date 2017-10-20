package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EffPexRemoveGroupFromPlayer extends Effect {
	private Expression<String> s;
	private Expression<OfflinePlayer> p;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		p = (Expression<OfflinePlayer>) expr[1];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "pex remove group %string% from %offlineplayers%";
	}

	@Override
	protected void execute(Event e) {
		for (OfflinePlayer op : p.getAll(e)) {
			PermissionUser permuser = PermissionsEx.getPermissionManager().getUser(op.getUniqueId());
			permuser.removeGroup(s.getSingle(e));
		}

	}
}
