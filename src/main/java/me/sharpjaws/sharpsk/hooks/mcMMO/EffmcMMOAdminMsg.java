package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.api.ChatAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

class EffmcMMOAdminMsg extends Effect {
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
		return "[mcmmo] send %string% to admin chat";
	}

	@Override
	protected void execute(Event e) {
		ChatAPI.sendAdminChat(Bukkit.getPluginManager().getPlugin("SharpSK"), s2.getSingle(e), s.getSingle(e));
	}
}
