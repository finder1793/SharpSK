package me.sharpjaws.sharpSK.hooks.LuckPerms;

import java.util.Optional;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;

public class EffLuckPermsDeleteGroup extends Effect {
	private Expression<String> group;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		group = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "[sharpsk] luckperms delete group %string%";
	}

	@Override
	protected void execute(Event e) {
		Optional<LuckPermsApi> api = LuckPerms.getApiSafe();
		for (Group g : api.get().getGroups()) {
			if (g.getName().equals(group.getSingle(e))) {
				api.get().getStorage().deleteGroup(g).thenAcceptAsync(wasSuccessful -> {
					if (!wasSuccessful) {
						return;
					}

					api.get().getStorage().loadAllGroups();

				}, api.get().getStorage().getAsyncExecutor());
				break;
			}
		}
	}

}
