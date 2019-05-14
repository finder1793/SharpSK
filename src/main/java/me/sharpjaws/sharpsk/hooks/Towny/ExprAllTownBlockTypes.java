package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.object.TownBlockType;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

class ExprAllTownBlockTypes extends SimpleExpression<TownBlockType> {

	private boolean townblocktype;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends TownBlockType> getReturnType() {
		return TownBlockType.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			final ParseResult parseResult) {
		townblocktype = parseResult.mark == 1 || matchedPattern == 1;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "all [of|the] town[ ]blocktypes";
	}

	@Override
	@Nullable
	protected TownBlockType[] get(Event e) {
		ArrayList<TownBlockType> townblocktypes = new ArrayList<>();
		for (TownBlockType t : TownBlockType.values()) {
			if (t != null) {
				townblocktypes.add(t);
			}
		}
		return townblocktypes.toArray(new TownBlockType[0]);
	}

	@Override
	public boolean isLoopOf(final String s) {
		return townblocktype && (s.equalsIgnoreCase("townblocktype"));
	}
}
