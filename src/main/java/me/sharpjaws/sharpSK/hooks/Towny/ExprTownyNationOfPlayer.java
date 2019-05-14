package me.sharpjaws.sharpSK.hooks.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprTownyNationOfPlayer extends SimpleExpression<String> {

	private Expression<OfflinePlayer> resident;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		resident = (Expression<OfflinePlayer>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [towny] nation of %player%";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {

		String natname = null;
		Resident res;
		try {
			res = TownyUniverse.getDataSource().getResident(resident.getSingle(e).getName());
			natname = res.getTown().getNation().getName();

		} catch (NotRegisteredException ex) {
			ex.printStackTrace();
		}

		return new String[] { natname };
	}

	@Override
	public boolean isSingle() {
		return true;
	}

}
