package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EffPayment extends Effect {
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		player = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "make %player% leave all jobs";
	}

	@Override
	protected void execute(Event e) {
		PermissionsEx.getPermissionManager().clearUserCache(player.getSingle(e));
	}
}
