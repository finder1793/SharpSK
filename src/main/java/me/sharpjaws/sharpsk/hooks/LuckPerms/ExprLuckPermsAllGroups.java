package me.sharpjaws.sharpsk.hooks.LuckPerms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;

public class ExprLuckPermsAllGroups extends SimpleExpression<String> {

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult result) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] (luckperms|lp) [all] groups";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		Optional<LuckPermsApi> api = LuckPerms.getApiSafe();
		ArrayList<String> groups = new ArrayList<>();

		for (Group g : api.get().getGroups()) {
			groups.add(g.getName());
		}
		return groups.toArray(new String[groups.size()]);

	}

}
