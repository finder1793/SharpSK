package me.sharpjaws.sharpsk.hooks.LuckPerms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

class ExprLuckPermsAllPermissionsOfPlayer extends SimpleExpression<String> {
	private Expression<Player> pl;
	private int mark;

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
		pl = (Expression<Player>) expr[0];
		mark = result.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] (luckperms|lp) [(all|the)] (-1¦transient perm[ission]s|1¦perm[ission]s) of %player%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		ArrayList<String> perms = new ArrayList<>();
		if (pl.getSingle(e) == null) {
			return new String[perms.size()];
		}
		Consumer<User> action = t -> {

			if (mark == -1) {
				for (Node n : t.getTransientPermissions()) {
					perms.add(n.getPermission());
				}
			} else {
				for (Node n : t.getPermanentPermissionNodes()) {
					perms.add(n.getPermission());
				}
			}
		};
		Optional<LuckPermsApi> api = LuckPerms.getApiSafe();
		User user = api.get().getUser(pl.getSingle(e).getUniqueId());
		if (user != null) {
			action.accept(user);
		}

		return perms.toArray(new String[0]);
	}

}
