package me.sharpjaws.sharpSK.hooks.mcMMO;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.nossr50.datatypes.skills.AbilityType;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprmcMMOAllAbilityTypes extends SimpleExpression<AbilityType> {

	boolean abilitytype;

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
		return skilltypes.toArray(new AbilityType[skilltypes.size()]);
	}

	@Override
	public boolean isLoopOf(final String s) {
		return abilitytype && (s.equalsIgnoreCase("abilitytype"));
	}
}
