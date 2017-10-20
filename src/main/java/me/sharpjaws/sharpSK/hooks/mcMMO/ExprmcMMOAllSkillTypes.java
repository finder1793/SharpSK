package me.sharpjaws.sharpSK.hooks.mcMMO;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.nossr50.datatypes.skills.SkillType;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprmcMMOAllSkillTypes extends SimpleExpression<SkillType> {

	boolean skilltype;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends SkillType> getReturnType() {
		return SkillType.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			final ParseResult parseResult) {
		skilltype = parseResult.mark == 1 || matchedPattern == 1;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "all [of|the] skill[]types";
	}

	@Override
	@Nullable
	protected SkillType[] get(Event e) {
		ArrayList<SkillType> skilltypes = new ArrayList<>();
		for (SkillType p : SkillType.values()) {
			if (p != null) {
				skilltypes.add(p);
			}
		}
		return skilltypes.toArray(new SkillType[skilltypes.size()]);
	}

	@Override
	public boolean isLoopOf(final String s) {
		return skilltype && (s.equalsIgnoreCase("skilltype"));
	}
}
