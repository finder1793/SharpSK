package me.sharpjaws.sharpSK.hooks.mcMMO;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.gmail.nossr50.party.PartyManager;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprmcMMOPartyLevel extends SimpleExpression<Number> {
	private Expression<String> s;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
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
		return "[sharpsk] [mcmmo] level of party %string%";
	}

	@Override
	@Nullable
	protected Integer[] get(Event e) {
		return new Integer[] { PartyManager.getParty(s.getSingle(e)).getLevel() };
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			Number n = (Number) delta[0];
			Integer n2 = n.intValue();
			com.gmail.nossr50.party.PartyManager.getParty(s.getSingle(e)).setLevel(n2);
		}
		if (mode == Changer.ChangeMode.ADD) {
			Number n = (Number) delta[0];
			Integer n2 = n.intValue();
			com.gmail.nossr50.party.PartyManager.getParty(s.getSingle(e))
					.setLevel(PartyManager.getParty(s.getSingle(e)).getLevel() + n2);
		}
		if (mode == Changer.ChangeMode.REMOVE) {

			Number n = (Number) delta[0];
			int n2 = n.intValue();
			if ((int) n2 < PartyManager.getParty(s.getSingle(e)).getLevel()) {
				com.gmail.nossr50.party.PartyManager.getParty(s.getSingle(e))
						.setLevel(PartyManager.getParty(s.getSingle(e)).getLevel() - (int) n2);
			} else {
				com.gmail.nossr50.party.PartyManager.getParty(s.getSingle(e)).setLevel(0);
			}
		}
		if (mode == Changer.ChangeMode.RESET) {
			com.gmail.nossr50.party.PartyManager.getParty(s.getSingle(e)).setLevel(0);
		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(new Class[] { Number.class });
		if (mode == Changer.ChangeMode.ADD)
			return CollectionUtils.array(new Class[] { Number.class });
		if (mode == Changer.ChangeMode.RESET)
			return CollectionUtils.array(new Class[] { Number.class });
		if (mode == Changer.ChangeMode.REMOVE)
			return CollectionUtils.array(new Class[] { Number.class });
		return null;
	}
}
