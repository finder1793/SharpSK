package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.datatypes.skills.SkillType;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

class ExprmcMMOAllSkillTypes extends SimpleExpression<SkillType> {

	private boolean skilltype;

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
		return skilltypes.toArray(new SkillType[0]);
	}

	@Override
	public boolean isLoopOf(final String s) {
		return skilltype && (s.equalsIgnoreCase("skilltype"));
	}
}
