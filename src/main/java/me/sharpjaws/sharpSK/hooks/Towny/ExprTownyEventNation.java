package me.sharpjaws.sharpSK.hooks.Towny;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.event.DeleteNationEvent;
import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.event.RenameNationEvent;
import com.palmergames.bukkit.towny.event.RenameTownEvent;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTownyEventNation extends SimpleExpression<String> {

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "event-nation";
	}


	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		if (!ScriptLoader.isCurrentEvent(RenameNationEvent.class) && !ScriptLoader.isCurrentEvent(DeleteNationEvent.class)&& !ScriptLoader.isCurrentEvent(NewNationEvent.class)) {
			return false ;		
		}
		return true;
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
	if (e.getEventName().equals("RenameNationEvent")){	
		return new String[]{((RenameNationEvent) e).getNation().getName()};	
	}else if (e.getEventName().equals("DeleteTownEvent")){
		return new String[]{((DeleteNationEvent) e).getNationName()};
		
	}else if (e.getEventName().equals("NewTownEvent")){
		return new String[]{((NewNationEvent) e).getNation().getName()};
		
	}
		return null; 
	}

}
