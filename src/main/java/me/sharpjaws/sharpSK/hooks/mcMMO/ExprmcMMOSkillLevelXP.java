package me.sharpjaws.sharpSK.hooks.mcMMO;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprmcMMOSkillLevelXP extends SimpleExpression<Number> {
	private Expression<Player> p;
	private Expression<SkillType> s;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<SkillType>) expr[0];
		p = (Expression<Player>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [mcmmo] %skilltype% [e]xp[erience] of %player%";
	}

	@Override
	@Nullable
	protected Integer[] get(Event e) {
		return new Integer[] {ExperienceAPI.getXP(p.getSingle(e), s.getSingle(e).toString()) };
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) {
			
			Number level = (Number)delta[0];
			ExperienceAPI.setXP(p.getSingle(e), s.getSingle(e).toString(),level.intValue());
		}
	if (mode == Changer.ChangeMode.ADD) {
			
			Number level = (Number)delta[0];
			ExperienceAPI.addRawXP(p.getSingle(e), s.getSingle(e).toString(),level.intValue(),"Command");
		}
	if (mode == Changer.ChangeMode.REMOVE) {
		
		Number level = (Number)delta[0];
		if (!(ExperienceAPI.getXP(p.getSingle(e), s.getSingle(e).toString()) <= 0)){
		ExperienceAPI.removeXP(p.getSingle(e), s.getSingle(e).toString(), +level.intValue());
		}else{
			ExperienceAPI.setXP(p.getSingle(e), s.getSingle(e).toString(), 0);	
		}
		
		}
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(new Class[] { Number.class });
		if (mode == Changer.ChangeMode.ADD)
			return CollectionUtils.array(new Class[] { Number.class });
		if (mode == Changer.ChangeMode.REMOVE)
			return CollectionUtils.array(new Class[] { Number.class });
		return null;
	}
}
