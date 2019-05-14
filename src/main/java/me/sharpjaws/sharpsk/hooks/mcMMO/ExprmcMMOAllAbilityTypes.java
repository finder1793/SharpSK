package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.datatypes.skills.AbilityType;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

class ExprmcMMOAllAbilityTypes extends SimpleExpression<AbilityType> {

	private boolean abilitytype;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends AbilityType> getReturnType() {
		return AbilityType.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			final ParseResult parseResult) {
		abilitytype = parseResult.mark == 1 || matchedPattern == 1;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "all [of|the] ability[]types";
	}

	@Override
	@Nullable
	protected AbilityType[] get(Event e) {
		ArrayList<AbilityType> skilltypes = new ArrayList<>();
		for (AbilityType p : AbilityType.values()) {
			if (p != null) {
				skilltypes.add(p);
			}
		}
		return skilltypes.toArray(new AbilityType[0]);
	}

	@Override
	public boolean isLoopOf(final String s) {
		return abilitytype && (s.equalsIgnoreCase("abilitytype"));
	}
}
