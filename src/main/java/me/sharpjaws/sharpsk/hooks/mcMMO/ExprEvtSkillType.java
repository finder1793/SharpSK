package me.sharpjaws.sharpsk.hooks.mcMMO;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

class ExprEvtSkillType extends SimpleExpression<SkillType> {

	@Override
	public Class<? extends SkillType> getReturnType() {
		return SkillType.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "event-skilltype";
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
        return ScriptLoader.isCurrentEvent(McMMOPlayerLevelUpEvent.class)
                || ScriptLoader.isCurrentEvent(McMMOPlayerXpGainEvent.class);
    }

	@Override
	@Nullable
	protected SkillType[] get(Event e) {
		if (e.getEventName().equals("McMMOPlayerLevelUpEvent")) {
			return new SkillType[] { ((McMMOPlayerLevelUpEvent) e).getSkill() };
		} else if (e.getEventName().equals("McMMOPlayerXpGainEvent")) {
			return new SkillType[] { ((McMMOPlayerXpGainEvent) e).getSkill() };
		}
		return null;
	}

}
