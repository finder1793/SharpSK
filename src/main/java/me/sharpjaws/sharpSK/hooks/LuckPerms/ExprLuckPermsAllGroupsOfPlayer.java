package me.sharpjaws.sharpSK.hooks.LuckPerms;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;

public class ExprLuckPermsAllGroupsOfPlayer extends SimpleExpression<String> {
	private Expression<Player> pl;
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
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "[sharpsk] (luckperms|lp) [(all|the)] groups";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
	
		ArrayList<String> groups = new ArrayList<>();
		Optional<LuckPermsApi> api = LuckPerms.getApiSafe();
		if(pl.getSingle(e) == null) {return new String[groups.size()];}
		Consumer<User> action = new Consumer<User>(){
			@Override
			public void accept(User t) {

				for (Group g : api.get().getGroups()) {
					if (t.isInGroup(g)) {
						groups.add(g.getName());
					}
				}
			}

		};


		User user = api.get().getUser(pl.getSingle(e).getUniqueId());
		if (user != null) {
			action.accept(user);

		}
		return groups.toArray(new String[groups.size()]);
	}


}
