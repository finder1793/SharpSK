package me.sharpjaws.sharpsk.hooks.LuckPerms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.DataMutateResult;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;

public class EffLuckPermsUnsetPerm extends Effect {
	private Expression<OfflinePlayer> offplayer;
	private Expression<String> perm;
	int mark;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult parse) {
		perm = (Expression<String>) expr[0];
		offplayer = (Expression<OfflinePlayer>) expr[1];
		mark = parse.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "[sharpsk] luckperms unset (-1¦transient perm[ission]|1¦perm[ission]) %string% for [player] %offlineplayer%";
	}

	@Override
	protected void execute(Event e) {
		if (offplayer.getSingle(e) == null) {
			return;
		}
		Optional<LuckPermsApi> api = LuckPerms.getApiSafe();
		Consumer<User> action = new Consumer<User>() {

			@Override
			public void accept(User t) {
				if (mark == -1) {
					for (Node nperm : t.getTransientPermissions()) {
						if (nperm.getKey().equals(perm.getSingle(e))) {
							DataMutateResult result = t.unsetTransientPermissionUnchecked(nperm);
							if (result != DataMutateResult.SUCCESS) {
								return;
							}
							break;
						}
					}
				} else {
					for (Node nperm : t.getPermanentPermissionNodes()) {
						if (nperm.getKey().equals(perm.getSingle(e))) {
							DataMutateResult result = t.unsetPermissionUnchecked(nperm);
							if (result != DataMutateResult.SUCCESS) {

								return;
							}
							break;
						}
					}
				}

				api.get().getStorage().saveUser(t).thenAcceptAsync(wasSuccessful -> {
					if (!wasSuccessful) {
						return;
					}

					t.refreshPermissions();

				}, api.get().getStorage().getAsyncExecutor());
			}

        };

		if (offplayer.getSingle(e).isOnline()) {
			User user = api.get().getUser(offplayer.getSingle(e).getUniqueId());
			if (user != null) {
				action.accept(user);
			}

		} else {
			api.get().getStorage().loadUser(offplayer.getSingle(e).getUniqueId()).thenAcceptAsync(wasSuccessful -> {
				if (!wasSuccessful) {
					return;
				}

				User loadedUser = api.get().getUser(offplayer.getSingle(e).getUniqueId());
				if (loadedUser == null) {
					return;
				}

				action.accept(loadedUser);
				api.get().cleanupUser(loadedUser);
			}, api.get().getStorage().getSyncExecutor());

		}
	}

}
