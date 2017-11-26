package me.sharpjaws.sharpSK.hooks.mcMMO;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.nossr50.party.PartyManager;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprmcMMOAllPartyMembers extends SimpleExpression<String> {
	private Expression<String> s;

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
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[mcmmo] [(the|all)] members of party %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		ArrayList<String> members = new ArrayList<>();
		for (Entry<UUID, String> user : PartyManager.getParty(s.getSingle(e)).getMembers().entrySet()) {
			members.add(user.getValue());
		}
		return members.toArray(new String[members.size()]);

	}

}
