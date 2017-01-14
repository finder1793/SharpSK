package me.sharpjaws.sharpSK.hooks.Multiverse;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.onarandombox.MultiverseCore.MultiverseCore;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprUnloadedWorlds extends SimpleExpression<String>{

	
	@Override
	public Class<? extends String> getReturnType() {	
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		MultiverseCore mv = null;		
		mv = MultiverseCore.getPlugin(MultiverseCore.class).getCore();
		
		return mv.getMVWorldManager().getUnloadedWorlds().toArray(new String[mv.getMVWorldManager().getUnloadedWorlds().size()]);
	}

}
