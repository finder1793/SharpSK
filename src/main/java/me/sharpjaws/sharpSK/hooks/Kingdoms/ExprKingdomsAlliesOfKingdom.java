package me.sharpjaws.sharpSK.hooks.Kingdoms;
import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.kingdoms.manager.game.GameManagement;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprKingdomsAlliesOfKingdom extends SimpleExpression<String> {
	private Expression<String> kingdom;
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		kingdom = (Expression<String>) expr[0];
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [kingdoms] allies of kingdom %string%";
	}


	@Override
	@Nullable
	protected String[] get(Event e) {
		
		
		
		ArrayList<String> narr = new ArrayList<String>(); 
		for (String en : GameManagement.getKingdomManager().getOfflineKingdom(kingdom.getSingle(e)).getAlliesList() ) {
		
			 narr.add(en);
		 }
		
		 
		 return narr.toArray(new String[narr.size()]);
		
			}

	@Override
	public boolean isSingle() {
		return false;
	}

	

}


