package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EffGiveTimedPexPerm extends Effect {
	private Expression<String> string;
	private Expression<Player> player;
	private Expression<Timespan> time;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		string = (Expression<String>) expr[0];
		player = (Expression<Player>) expr[1];
		time = (Expression<Timespan>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return " pex add timed permission %string% to %player% duration %timespan%";
	}

	@Override
	protected void execute(Event e) {
		@SuppressWarnings("deprecation")
		Integer test = Integer.valueOf(time.getSingle(e).getTicks());
		PermissionUser permUser = PermissionsEx.getUser(player.getSingle(e));
		String world = player.getSingle(e).getWorld().getName().toString();
		permUser.addTimedPermission(string.getSingle(e), world, test.intValue() / 20);
		if (test.intValue() / 20 == 0) {
			PermissionsEx.getUser(player.getSingle(e)).removePermission(string.getSingle(e));
		}
	}
}
