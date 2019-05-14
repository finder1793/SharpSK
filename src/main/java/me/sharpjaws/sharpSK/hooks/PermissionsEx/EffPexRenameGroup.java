package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import javax.annotation.Nullable;

public class EffPexRenameGroup extends Effect {
	private Expression<String> s;
	private Expression<String> s2;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		s2 = (Expression<String>) expr[1];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "pex rename group %string% to %string%";
	}

	@Override
	protected void execute(Event e) {
		PermissionsEx.getPermissionManager().getGroup(s.getSingle(e)).getName().replaceAll("\\^([0-9]+)",
				s2.getSingle(e));

	}
}
