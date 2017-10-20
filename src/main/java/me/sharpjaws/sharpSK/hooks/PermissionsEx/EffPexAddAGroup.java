package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EffPexAddAGroup extends Effect {
	private Expression<String> s;
	private Expression<Boolean> b;
	private Expression<String> prefix;
	private Expression<String> suffix;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		b = (Expression<Boolean>) expr[1];
		prefix = (Expression<String>) expr[2];
		suffix = (Expression<String>) expr[3];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "pex create group %string% default %boolean%";
	}

	@Override
	protected void execute(Event e) {
		List<String> l = new ArrayList<String>();
		PermissionGroup group = PermissionsEx.getPermissionManager().getGroup(s.getSingle(e));
		PermissionsEx.getPermissionManager().getGroup(s.getSingle(e)).setPermissions(l);
		try {
			PermissionsEx.getPermissionManager().getGroup(s.getSingle(e)).setPrefix(prefix.getSingle(e), null);
		} catch (NullPointerException ex) {
			PermissionsEx.getPermissionManager().getGroup(s.getSingle(e)).setPrefix("", null);
		}
		try {
			PermissionsEx.getPermissionManager().getGroup(s.getSingle(e))
					.setSuffix(" " + suffix.getSingle(e).toString().replaceAll("('|\")", "\\") + " ", null);
		} catch (NullPointerException ex) {
			PermissionsEx.getPermissionManager().getGroup(s.getSingle(e)).setSuffix("", null);
		}

		PermissionsEx.getPermissionManager().getGroup(s.getSingle(e)).setDefault(b.getSingle(e), null);
		l.add("[]");

	}
}
