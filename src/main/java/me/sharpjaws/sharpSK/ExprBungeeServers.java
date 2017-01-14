package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.utils.Bungee.Messenger;

public class ExprBungeeServers extends SimpleExpression<String> {
	private Expression<String> s;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "bungee player count of %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		return new String[] {Messenger.getPlayerCount(s.getSingle(e))};
	}
}
